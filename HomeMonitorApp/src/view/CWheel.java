package view;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The color wheel allows you to get a numeric value from a circular
 * interface. A color wheel picture is put behind the controls meaning
 * that the image "physicswheel.png" is required. The numerical value returned
 * is between 0-360. It can be controlled by dragging the arm as well as 
 * via the left and right arrow keys 
 * @author team11
 *
 */

public class CWheel extends JComponent 
{
	private static final long serialVersionUID = 1L;

	// The initial starting point of the marker with 0 being east
	private final static float START = 270;

	// The amount of the circle that should be available to the marker
	private final static float LENGTH = 360;

	// The size of the color wheel. More editing is needed then just changing this number
	// however if you wish to change the size
	private int size = 200;
	
	private final static float PI = (float) 3.1415;
	
	// Convert Degrees to radians, used for arc length
	private final static float START_ANG = (START/360)*PI*2;
	private final static float LENGTH_ANG = (LENGTH/360)*PI*2;
	
	// Used to convert radians to degrees
	private final static float RADTODEGEE = 180 / PI; 

	// Color of the ring along the inside of the dial
	private final static Color DEFAULT_FOCUS_COLOR = new Color(0x8080ff);

	// Determine marker movement speed when dragType is set to SIMPLE.
	private float DRAG_SPEED = 0.01F;

	// Determines how much the marker moves per mouse click and arrow press.
	// Larger values makes the marker move further.
	private float CLICK_SPEED = 0.01F;

	private int middle;

	// Simple or Round
	private int dragType = ROUND;
	public final static int SIMPLE = 1;
	public final static int ROUND  = 2;

	// Set the antialiasing to get the right look!
	private final static RenderingHints AALIAS = 
		new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
									 RenderingHints.VALUE_ANTIALIAS_ON);

	private ChangeEvent changeEvent = null;
	private EventListenerList listenerList = new EventListenerList();

	private Arc2D hitArc = new Arc2D.Float(Arc2D.PIE);

	private float ang = (float) START_ANG;
	private float val;
	private int dragpos = -1;
	private float startVal;
	private Color focusColor;
	private double lastAng;

	public CWheel(){  
		focusColor = DEFAULT_FOCUS_COLOR;

		hitArc.setAngleStart(START);

		addMouseListener(new MouseAdapter() {
					
					public void mousePressed(MouseEvent me) {
							dragpos = me.getX() + me.getY();
							startVal = val;

							// Fix last angle
							int xpos = middle - me.getX();
							int ypos = middle - me.getY();
							lastAng = Math.atan2(xpos, ypos);

							requestFocus();
					}
					
					public void mouseClicked(MouseEvent me) {;
						hitArc.setAngleExtent(-(LENGTH + 10));
						if  (hitArc.contains(me.getX(), me.getY())) {  // Check if the click is within the circle        
							hitArc.setAngleExtent(RADTODEGEE * (ang-START_ANG)-10); // Convert radian to degree
							if  (hitArc.contains(me.getX(), me.getY())) { // Check if the click is within the circle
									decValue(); 
							} else incValue();      
						}
					}
				});

		// Let the user control the knob with the mouse
		addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent me) {
							if ( dragType == SIMPLE) {
								float f = DRAG_SPEED * (float)
										((me.getX() + me.getY()) - dragpos);
								setValue(startVal + f);


							} else if ( dragType == ROUND) {
								int xMouse = me.getX();
								int yMouse = me.getY();
								
								// How far away from the circle the mouse can go and still register
								int leeway = 50;
								
								// Make sure the mouse is within the circle space
								if (xMouse <= size+leeway && yMouse <= size+leeway){
									// Measure relative the middle of the button
									int xpos = middle - xMouse;
									int ypos = middle - yMouse;
									//System.out.println("x: " + xpos + " y:" + ypos);
									// Find the theta from polar coordinates
								
									double ang = Math.atan2(xpos, ypos) ;
									double diff = lastAng - ang;
									//System.out.println("diff: " + (getValue() + (diff / LENGTH_ANG)));
									//System.out.println("ang: " + ang);
									setValue((float) (getValue() + (diff / LENGTH_ANG)));
	
									lastAng = ang;
									
								}
							}
					}

					public void mouseMoved(MouseEvent me) {
					}
				});

		// Let the user control the knob with the keyboard
		addKeyListener(new KeyListener() {

					public void keyTyped(KeyEvent e) {}
					public void keyReleased(KeyEvent e) {} 
					public void keyPressed(KeyEvent e) { 
							int k = e.getKeyCode();
							if (k == KeyEvent.VK_RIGHT)
								incValue();
							else if (k == KeyEvent.VK_LEFT)
								decValue();
					}
				});

		// Handle focus so that the knob gets the correct focus highlighting.
		addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e) {
							repaint();
					}
					public void focusLost(FocusEvent e) {
							repaint();
					}
				});
	}
	/**
	 * Allows you to set the drag type
	 * SIMPLE - Control the values via dragging the mouse up and down
	 * ROUND - Allows you to intuitively drag the arm by moving it in circular
	 * 		   motions
	 * @param type
	 */
	public void setDragType(int type) {
		dragType = type;
	}
	
	/**
	 * Returns the current drag type of the wheel
	 * @return the current drag type of the wheel
	 */
	public int getDragType() {
		return dragType;
	}

	 /**
	  * Extended from JCompenent set whether or not this Container 
	  * is the root of a focus traversal cycle, can it be focused
	  * Set whether or not you would like to be able to focus
	  * @return tell JComponent that you want it to be able to be focused
	  */
	public boolean setFocusCycleRoot() {
		return true;
	}

	/**
	 * Extends JCompenent tests if this component can receive focus.
	 */
	public boolean isFocusable() {
		return true;
	}

	/**
	 * Used in increasing arrow clicks and mouse presses to determine new value
	 */
	private void incValue() {
		setValue(val + CLICK_SPEED);
	}

	/**
	 * Used in decreasing arrow clicks and mouse presses to determine new value
	 */
	private void decValue() {
		setValue(val - CLICK_SPEED);
	}

	/**
	 * Get the current value return is a float
	 * @return current value
	 */
	public float getValue() {
		return val;
	}

	/**
	 * Used to set the value that the color wheel is corresponding to as well
	 * as get the angle that the arm should be pointing.
	 * This is also the part of the code that makes sure the arm doesn't
	 * exceed one cycle and determines where the arm should be pointing based
	 * on the current value. 
	 * @param value you want to set
	 */
	public void setValue(float val) {
		if (val < 0) val = 1;
		if (val > 1) val = 0;
		this.val = val;
		ang = START_ANG - (float) LENGTH_ANG * val;
		repaint();
		fireChangeEvent();
	}


	/**
	 * Add the listener to the color wheel.
	 * @param cl
	 */
	public void addChangeListener(ChangeListener cl) {
		listenerList.add(ChangeListener.class, cl);
	}

	/**
	 * Remove a listener from the color wheel
	 * @param cl
	 */
	public void removeChangeListener(ChangeListener cl) {
		listenerList.remove(ChangeListener.class, cl);            
	}

	/**
	 * Create the listeners
	 */
	protected void fireChangeEvent() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
				if (listeners[i] == ChangeListener.class) {
					// Lazily create the event:
					if (changeEvent == null)
							changeEvent = new ChangeEvent(this);
					((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
				}
		}
	}


	/**
	 * Used to create the painting of the color wheel, it brings the color
	 * wheel to life where before it was just numbers.
	 */
	public void paint(Graphics g) {
		size = 200;
		middle = 10 + size/2;

		if (g instanceof Graphics2D) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setBackground(getParent().getBackground());
				g2d.addRenderingHints(AALIAS);
				
				// Outline thickness
				g2d.setStroke(new BasicStroke(5));
				// For the size of the "mouse click" area
				hitArc.setFrame(4, 4, size+12, size+12);
		}

		// Set the color of the outline circle
		g.setColor(Color.black);
		
		// Paint focus if in focus
		/*
		if (hasFocus()) {
				g.setColor(focusColor);
		} else {
				g.setColor(Color.white);
		}
		*/
		g.setColor(Color.white);
		
		
		
		//Paints a purple circle behind
		//fillOval(int x,int y, int width, int height)
		g.fillOval(10, 10, size, size);
				
				
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/physicswheel.png"));
			g.drawImage(image, 8, 8, size+5, size+5, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		// Set the color of the outline circle
		g.setColor(Color.black);
		
		//Draw a black outline around the purple circle
		g.drawArc(10, 10, size, size, 360, 360);

		
		// Center circle
		int innerCircleSize = 50;
		int innerCircleXY = middle - innerCircleSize/2; 
		g.fillOval(innerCircleXY, innerCircleXY, innerCircleSize, innerCircleSize);
		
	

		int x = 10 + size/2 + (int)(size/2 * Math.cos(ang));
		
		
		int y = 10 + size/2 - (int)(size/2 * Math.sin(ang));
		//g.drawLine(10 + size/2, 10 + size/2, x, y);
		//g.setColor(Color.gray);
		int s2 = (int) Math.max(size / 6, 6);
		g.drawOval(10 + s2, 10 + s2, size - s2*2, size - s2*2);
		g.setColor(Color.black);
		
		int dx = (int)(2 * Math.sin(ang));
		int dy = (int)(2 * Math.cos(ang));
		
		g.drawLine(10 + dx + size/2, 10 + dy + size/2, x, y);
		g.drawLine(10 - dx + size/2, 10 - dy + size/2, x, y);
	}
}
