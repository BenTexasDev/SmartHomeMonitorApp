package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controller.HomeAutomationController;
import model.HomeAutomationModel;
import view.HomeAutomationView;

public class ProjectMain {

	public static void main(String[] args) {

		HomeAutomationModel model = new HomeAutomationModel();
		HomeAutomationView view = new HomeAutomationView(model);
		HomeAutomationController controller = new HomeAutomationController(model, view);
		
		view.register(controller);
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.addWindowListener(new WindowAdapter()
		{
				public void windowClosing(WindowEvent e)
				{
					model.getClient().endConnection();
				
				}
		});
		view.setBounds(100, 100, 608, 442);
		view.setVisible(true);

	}

}

