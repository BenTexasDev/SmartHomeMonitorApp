package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Menu bar will show the help info.
 * @author team11
 */
public class MenuBar extends JFrame implements MenuListener, ActionListener{
	
	private JMenuBar menubar;
	private LightsHelp lh;
	private ConnectionHelp connectDoc;
	private JMenuItem menuItem1;
	private TempHelp tempPop;
	
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenu menu;
	public MenuBar(){
		menubar = new JMenuBar();
		menu = new JMenu("Help"); 
		menuItem1 = new JMenuItem("Connecting"); 
		menuItem2 = new JMenuItem("Lights"); 
		menuItem3 = new JMenuItem("Temperature");
		lh = new LightsHelp();
		connectDoc = new ConnectionHelp();
		tempPop = new TempHelp();
		// add the MenuItems to the Menu 
		menu.add(menuItem1);
		menu.add(menuItem2); 
		menu.add(menuItem3); 
		
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
		
		menubar.add(menu); 
		menubar.setBounds(10,10,35,35);
		menubar.setBackground(null);
	}
	public JMenuBar getMenubar(){
		return this.menubar;
	}
	public JMenu getMenu(){
		return this.menu;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(menuItem1)){
			connectDoc.display();
		}else if(e.getSource().equals(menuItem2)){
			lh.display();
		}else if(e.getSource().equals(menuItem3)){
			tempPop.display();
		}
		
	}
	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
