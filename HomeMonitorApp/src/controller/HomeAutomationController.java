package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.HomeAutomationModel;
import model.User;
import view.CWheel;
import view.ConnectionErrorDialog;
import view.HomeAutomationView;

/**
 * The controller for the project.
 *
 */
public class HomeAutomationController implements ActionListener, ChangeListener {

	/**
	 * The model upon which the controller acts. Values will be set in the model
	 */
	private HomeAutomationModel model;

	/**
	 * The view which the controller listens to, values come from the
	 * controller. Used to change view as well.
	 */
	private HomeAutomationView view;

	/**
	 * The controller allows the view and model to talk to each other
	 * 
	 * @param aModel
	 *            Where info is held
	 * @param aView
	 *            Where info is gathered
	 */
	public HomeAutomationController(HomeAutomationModel aModel, HomeAutomationView aView) {
		this.model = aModel;
		this.view = aView;
	}

	/**
	 * actionPerformed is for single press commands: buttons, radio, ect. Most
	 * buttons will go here, e.getActionCommand() allows us to see what button
	 * is was clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		switch(e.getActionCommand())
		{
		
			case "connectButton":

				//USerREcognition
				if(model.getUser().userRecognition()){
					System.out.println("connection Succeed");
					model.getClient().startConnection();
					if (model.getClient().isConnected()){
						view.getUser_panel().getConnectButton().setEnabled(false);
						view.getUser_panel().getDisconnectButton().setEnabled(true);
					} else {
						view.getUser_panel().getConnectButton().setEnabled(true);
						view.getUser_panel().getDisconnectButton().setEnabled(false);
					
					}
				} else{
					//ConnectionErrorDialog dialog;
					ConnectionErrorDialog.display();
					System.out.println("connection Failed");
				}
				break;
			
			case "disconnectButton":
				model.getClient().endConnection();
				if (!model.getClient().isConnected()){
					view.getUser_panel().getConnectButton().setEnabled(true);
					view.getUser_panel().getDisconnectButton().setEnabled(false);
				} else {
					view.getUser_panel().getConnectButton().setEnabled(false);
					view.getUser_panel().getDisconnectButton().setEnabled(true);
				}
				break;
			case "lPowerON":
				model.sendPowerState("on");
				break;
			case "lPowerOFF":
				model.sendPowerState("off");
				break;
			case "white":
				System.out.println("White");
				this.model.setColorValue(321, 0, 9000);

				break;
		}

	}

	/**
	 * stateChanged listen to when something is moved or changed from previous
	 * state.
	 */
	public void stateChanged(ChangeEvent evt) {
		//JSlider slider = (JSlider) evt.getSource();
		if (evt.getSource() instanceof CWheel){
			CWheel t = (CWheel) evt.getSource();
			this.model.setColorValue(Math.round(360*t.getValue()), 65535, 3500);
		}
		
		if (evt.getSource() instanceof JSlider){
			JSlider slider = (JSlider) evt.getSource();
			if (slider.getValueIsAdjusting()) {
				int value = slider.getValue();
				this.model.setLightValue(value);
			}
		}
	}
}
