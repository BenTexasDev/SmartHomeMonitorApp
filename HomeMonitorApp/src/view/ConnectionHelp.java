package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
import java.awt.*;

/**
 * Class that shows a help dialog for the temperature panel.
 * Will read from a text file containing the info and display it. 
 * @author team11
 *
 */
public class ConnectionHelp extends JFrame{
	
	private static BufferedInputStream buff;
	private static JTextArea txt;
	private InputStream inStream;
	private JScrollPane scrollp;
	private JPanel panel;
	private StringBuffer bufStr;
	
	
	public ConnectionHelp(){
		super("Connection Support Documentation");
	}
	
	/**
	 * Displays the dialog. 
	 */
	public void display(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		openFile();
		txt = new JTextArea(30,30);
        txt.setLineWrap(true);
        txt.setFont(new Font("sanSerif",Font.PLAIN,13));
        txt.setText(bufStr.toString());
        txt.setEditable(false);
		panel.add(new JLabel("Connection Support"), BorderLayout.NORTH);
		scrollp = new JScrollPane(txt);
		panel.add(scrollp, BorderLayout.CENTER);
		buff = null;
		JOptionPane.showConfirmDialog(null, panel,"", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		
	}
	/**
	 * Reads from the help connection file
	 */
	private void openFile(){
		inStream = null;
		try {
			inStream = new FileInputStream("HFile_Connecting.txt");
			 buff = new BufferedInputStream(inStream);
			bufStr = new StringBuffer();
			 while(buff.available() >0){
				char c = (char)buff.read();
				bufStr.append(c);
			}
		} catch (IOException e) {
			System.err.println("Error Opening FIle");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
