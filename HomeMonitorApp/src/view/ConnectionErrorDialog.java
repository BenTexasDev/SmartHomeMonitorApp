package view;



import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.*;
/**
 * Dialog that will pop up if user types wrong username or password. 
 * @author team11
 *
 */
public class ConnectionErrorDialog {
	
	/**
	 * void method opens a messegeDialog and displays it as ErrorDialog*/
	public static void display() {
     
        JPanel panel = new JPanel(new GridLayout(0, 1));
      ;
        JOptionPane.showMessageDialog(panel,
        	    "Wrong User or Password.",
        	    "Connection error.",
        	    JOptionPane.ERROR_MESSAGE);
        
    }
	  
}


