package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.HomeAutomationController;
import model.HomeAutomationModel;
import model.Light;
/**
 * Light tab for the GUI. Will contain a list of lights, Colorwheel, on/off button, and a brightness slider for the user to
 * control the light they select.  
 * @author team11
 *
 */
public class LightsPanel extends JPanel {

	private JRadioButton offButton;
	private JRadioButton onButton;
	CWheel colorWheel;
	private ButtonGroup powerButtonGroup;
	private JLabel labelOnOff;
	private JSlider brightnessSlider;
	public DefaultListModel<String> listModel;
	public JList<String> listScrollPane;
	private JScrollPane scrollPane;
	public JLabel brand;
	private JLabel lblBright;
	private JLabel lblDim;
	public List<Light> listOfLights;
	public static JButton makeWhite = null;
	private JButton btnAddDevice;
	HomeAutomationModel model;
	
	/**
	 * Will be called to add this panel to the frame in HomeAutomationView.java.
	 * @param model
	 */
	public void LightsPanel(HomeAutomationModel model) {

		
		setBackground(Color.white);
	

		// on/off radio button
		onButton = new JRadioButton("");
		onButton.setBackground(Color.GREEN);
		onButton.setBounds(448, 20, 28, 23);
		onButton.setActionCommand("lPowerON");
		add(onButton);
		
		
		makeWhite = new JButton("w");
		makeWhite.setBounds(256, 140, 50, 50);
		makeWhite.setForeground(Color.WHITE);
		makeWhite.setOpaque(false);
		makeWhite.setContentAreaFilled(false);
		makeWhite.setBorderPainted(false);
		makeWhite.setActionCommand("white");
		this.add(makeWhite);
		

		offButton = new JRadioButton("");
		offButton.setBackground(Color.RED);
		offButton.setBounds(475, 20, 28, 23);
		offButton.setActionCommand("lPowerOFF");
		add(offButton);

		// group so that only one can be selected at a time
		powerButtonGroup = new ButtonGroup();
		powerButtonGroup.add(onButton);
		powerButtonGroup.add(offButton);
		
		labelOnOff = new JLabel("On/Off");
		labelOnOff.setBounds(455, 44, 61, 16);
		add(labelOnOff);

		// slider
		brightnessSlider = new JSlider();
		brightnessSlider.setOrientation(SwingConstants.VERTICAL);
		brightnessSlider.setForeground(Color.BLACK);
		brightnessSlider.setBounds(465, 72, 25, 260);
		add(brightnessSlider);
		
		
		
		//method call to create list in jScrollPane
		setListModel(model);
		

		lblBright = new JLabel("Bright");
		lblBright.setBounds(494, 85, 61, 16);
		add(lblBright);

		lblDim = new JLabel("Dim");
		lblDim.setBounds(494, 299, 61, 16);
		add(lblDim);
		
        // Color Wheel
		colorWheel = new CWheel();
        colorWheel.setBounds(171,55,242,260);
        add(colorWheel);
        colorWheel.setValue((float)1.0);
 
    	// add button
		btnAddDevice = new JButton("Add Device");
		btnAddDevice.setBounds(10, 320, 117, 29);
		this.add(btnAddDevice);

		//Need to move the Buttons Around
		btnAddDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					display(model);
					
				}
			} );

	}
	
	/**
	 * Sets the list of lights inside of a JscrollPane on the Panel
	 * @param model
	 */
	public void setListModel(HomeAutomationModel model){
	
		listOfLights = model.getLights();
		listModel = new DefaultListModel<>();
		for(int i = 0; i< listOfLights.size(); i++){
			String s = listOfLights.get(i).getLocation();
			listModel.addElement(s);
		}
		
		listScrollPane = new JList<>();
		listScrollPane.setModel(listModel);
		
		listScrollPane.setBounds(6, 44, 132, 271);
		add(listScrollPane);
		scrollPane = new JScrollPane(listScrollPane);
		scrollPane.setBounds(6, 44, 132, 271);
		add(scrollPane);		
	}
	/**
	 * Register listeners for the lightsPanel
	 * @param controller
	 */
	public void register(HomeAutomationController controller){
		brightnessSlider.addChangeListener(controller);
		onButton.addActionListener(controller);
		offButton.addActionListener(controller);
		colorWheel.addChangeListener(controller);
		makeWhite.addActionListener(controller);		
	}
	/**
	 * Will display a dialog for adding lights. 
	 * @param model
	 */
	public void display(HomeAutomationModel model) {
        String[] items = {"Lifx", "OtherDevice1", "OtherDevice2", "OtherDevice3"};
        JComboBox combo = new JComboBox(items);
        JTextField field1 = new JTextField("");
        JTextField field2 = new JTextField("");
     
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(combo);
        panel.add(new JLabel("Location:"));
        panel.add(field1);
        panel.add(new JLabel("ModelNum:"));
        panel.add(field2);
       
        int result = JOptionPane.showConfirmDialog(null, panel, "Add device",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        
        if (result == 0){
        	model.addDevice((String)combo.getSelectedItem(), field1.getText(), field2.getText());
        	for(int i = listOfLights.size()-1; i< listOfLights.size(); i++){
    			String s = listOfLights.get(i).getLocation();
    			listModel.addElement(s);
    		}
       
        }
        
        if (result == 2){
    		for(Light light : model.getLights()){
    			System.out.println(light);
    		}
    		System.out.println("Done printing");
        }
       
    }

}
