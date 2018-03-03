package view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.HomeAutomationController;
import model.HomeAutomationModel;
import model.User;


/**
 * UserPanel holds the connect/disconnect buttons, as well as the user name and password entry.
 * It displays the user tab in our GUI in which includes our title
 * @author team11
 *
 */
public class UserPanel extends JPanel{
	
	//connection status
	final static int DISCONNECTED = 0;
	final static int BEGIN_CONNECT = 1;
	final static int CONNECTED = 2;
	public static JTextArea chatText = null;
	public static JTextField chatLine = null;
	public static JButton connectButton = null;
	public static JButton disconnectButton = null;
	public static int connectionStatus = DISCONNECTED;
	public static JFrame mainFrame = null;
	JMenuItem menuItem; 
	String helpText; 
	private JPasswordField passwordField;
	private JTextField userTextField;
	private JLabel lblUserName;
	private JLabel passwordLabel;
	
	/**private instance of USER*/
	private User user;
	
	public void User_Panel(HomeAutomationModel model) {
		this.user = model.getUser();
		
	
		//This is where the banner is added
		//I read the picture from my desktop
		//If we add a source folder called images, we could call "Images/header.png"
		ImageIcon image = new ImageIcon("Images/header.png");
		JLabel header = new JLabel("",image,JLabel.CENTER);
		JPanel banner = new JPanel(new BorderLayout());
		banner.setBackground(null);
		banner.add(header);
		banner.setBounds(60,15,425,60);
		this.add(banner);
		
		
		setBackground(Color.white);
		
		// user name component
		userTextField = new JTextField();
		userTextField.setBounds(185, 80, 193, 28);
		this.add(userTextField);
		userTextField.setColumns(10);
		
		
		// user name label
		lblUserName = new JLabel("User Name");
		lblUserName.setBounds(100, 65, 78, 55);//(Position left right, position up/down,height,width);
		this.add(lblUserName);
		
	
		// password component
		passwordField = new JPasswordField();
		passwordField.setBounds(185, 125, 193, 28);
		this.add(passwordField);
		
		
		// label for password
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(105, 130, 75, 25);
		// panel.add(lblNewLabel_2);
		this.add(passwordLabel);
		
		/**
		 * Send user and password to be compared*/
	//	user = new User(userTextField.getText(),passwordField.toString());
		/**
		 * */
		// connect and disconnect buttons
		connectButton = new JButton("Connect");
		connectButton.setBounds(185, 180, 95, 75);
		connectButton.setBackground(null);
		connectButton.setForeground(Color.GREEN);
		connectButton.setActionCommand("connectButton");
	

		this.add(connectButton);

		disconnectButton = new JButton("Disconnect");
		disconnectButton.setBounds(285, 180, 95, 75);
		disconnectButton.setBackground(null);
		disconnectButton.setForeground(Color.RED);
		disconnectButton.setActionCommand("disconnectButton");
		disconnectButton.setEnabled(false);
		this.add(disconnectButton);

		
	}
	
	/**
	 * @return connectButton to the class Controller*/
	public JButton getConnectButton() {
		return connectButton;
	}
	
	/**
	 * @return disconnectButton to the class controller*/
	public JButton getDisconnectButton() {
		return disconnectButton;
	}

	/**
	 * Register method adds to acctionListener
	 * and had 2 Anonymous classes for keyListener for
	 * the JpasswordFiled and for JTextField
	 * @param HomeAutomationController*/
	public void register(HomeAutomationController controller){
		connectButton.addActionListener(controller);
		disconnectButton.addActionListener(controller);
		
		userTextField.addKeyListener(new KeyAdapter(){
			/**@param KeyEvent
			 * */
			public void keyReleased(KeyEvent e){
				char ch = e.getKeyChar();
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					user.deleteCharFromTempUser();
				}else{
					user.setTempUser(ch);
				}
			}
			/**
			 * @param KeyEvent */
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					user.deleteCharFromTempUser();
				}
			}
			});

		passwordField.addKeyListener(new KeyAdapter(){
			/**
			 * @param keyevent*/
			public void keyReleased(KeyEvent e) {
				char ch = e.getKeyChar();
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					user.deleteCharFromTempPass();
				}
				else{
					user.setTempPass(ch);
				}
				
			}
			/**
			 * @param keyevent*/
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					user.deleteCharFromTempPass();
				}
			}
		});
	}

}
