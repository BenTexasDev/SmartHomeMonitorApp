package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import controller.HomeAutomationController;
import model.HomeAutomationModel;


/**
 * The view for the project, it brings all the views together into 
 * one file. Allows you to connect to views easily by only needing to
 * reference one view 
 * @author team11
 *
 */
public class HomeAutomationView extends JFrame{
	
	private JTabbedPane tabbedPane;
	private UserPanel user_panel = new UserPanel();
	private LightsPanel lights_Panel = new LightsPanel();
	private TempPanel temp_Panel = new TempPanel();
	JMenuItem menuItem; 
	private MenuBar menuBar; 
	
	
	
	
	/**
	 * Get the light panel so you can edit it
	 * @return light panel
	 */
	public LightsPanel getLights_Panel() {
		return lights_Panel;
	}

	
	/**
	 * Get the user panel so you can edit it
	 * @return user panel
	 */
	public UserPanel getUser_panel() {
		return user_panel;
	}


	/**
	 * Set what -THE- user panel is
	 * @param user panel
	 */
	public void setUser_panel(UserPanel user_panel) {
		this.user_panel = user_panel;
	}



	/**
	 * Connect panels to the controller
	 * @param controller
	 */
	public void register(HomeAutomationController controller){
		lights_Panel.register(controller);
		user_panel.register(controller);
		
	}

	/**
	 * Creates the paneled/tabbed view and returns
	 * the object that holds all the views 
	 * @param model
	 */
	public HomeAutomationView(HomeAutomationModel model) {
		setResizable(false);
		
		menuBar = new MenuBar();
		menuBar.setBounds(0, 0, 25, 210);
		getContentPane().add(menuBar.getMenubar(),BorderLayout.NORTH); 

		// create tab pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Desktop.background"));
		tabbedPane.setBounds(6, 5, 596, 409);
		getContentPane().add(tabbedPane);

		// add user panel
		tabbedPane.addTab("User", null, user_panel, null);
		user_panel.setLayout(null);
		user_panel.User_Panel(model);

		// add lights panel
		tabbedPane.addTab("Lights", null, lights_Panel, null);
		lights_Panel.setLayout(null);
		lights_Panel.LightsPanel(model);

		// add heating cooling panel
		tabbedPane.addTab("Heating/Cooling", null, temp_Panel, null);
		temp_Panel.setLayout(null);
		temp_Panel.TempPanel();

	

	}

}
