package model;

import java.util.ArrayList;
import java.util.List;

import communication.Client;
import view.HomeAutomationView;
public class HomeAutomationModel {
	
	
	
	//declare list of lights;
	
	private ArrayList<Light> lights;
	
	//declare a user
	private User user;
	
	//declare the client
	//not sure if it will stay here.
	private Client client;
	//file io object
	private FileIoObjects fileIO;
	
	public HomeAutomationModel(){
		
		//----------------------------------------------------
		// Obtain the latest weather info
		//System.out.println("Obtained Forecast");
		//WeatherAPIGather.obtainForcast();
		//----------------------------------------------------
		
		
		this.fileIO = new FileIoObjects();
		this.lights = new ArrayList<>();
		//fill lights arraylist from file
		lights = fileIO.readFromFile("listInfo.txt");
	
		this.user = new User();
		this.client = new Client("raspberrypi.local",5555);
		
	}
	
	// For Testing purposes
	LifxColor currLight = new LifxColor("Test","TESTMODEL");
	
	
	/**
	 * Send code to the RPi that sets the brightness
	 * @param Value
	 */
	public void setLightValue(int Value)
	{
		
		//Show console output so we can see what the RPi will receive 
		// Show the value
		System.out.println("Value:" + Value);
		
		//Set the brightness in the light object that holds the bulb data
		currLight.setBrightness(Value);
		
		// Check if the client is connected 
		if (client.isConnected()) {
			client.sendMessage("brightness,"+currLight.convertColorClient());
		}
		
		// Print the value we sent to the RPi
		System.out.println(currLight.convertColorClient());
	}
	
	/**
	 * Send the code to RPi that controls the color
	 * @param cValue color value we want to set the bulb to
	 * @param saturation saturation of the color
	 * @param kelvin how cool you want the bulb to be
	 */
	public void setColorValue(int cValue, int saturation, int kelvin)
	{
		System.out.println("cValue:" + cValue);
		currLight.setlightColor(cValue, saturation, kelvin);
		if (client.isConnected())
		{
			client.sendMessage("color,"+currLight.convertColorClient());
		}
		System.out.println(currLight.convertColorClient());
	}
	
	/**
	 * Send the cold to the RPi that turns on and off the bulb
	 * @param string that sets the state of bulb on|off
	 */
	public void sendPowerState(String power)
	{
		System.out.println("Power State: " + power);
		currLight.setPowerState(power);
		if (client.isConnected())
		{
			client.sendMessage("power,"+currLight.getPowerState());
		}
	}
	
	/** Get the current user
	 * @return user from model class*/
	public User getUser(){
		return this.user;
	}
	
	/** Get the current connection to the RPi
	 * @returns client from model class*/
	public Client getClient(){
		return this.client;
	}
	
	/** The List that holds all the bulb
	 * @returns a list of Lights from model class*/
	public List<Light> getLights(){
		return this.lights;
	}

	/**
	 * Used to add a bulb to the array list that holds the bulbs object
	 * @param deviceType The type of the bulb
	 * @param location	 Where the the bulb is located 
	 * @param modelNum   Model number of the bulb
	 */
	public void addDevice(String deviceType, String location, String modelNum){
		if(deviceType.equalsIgnoreCase("Lifx")){
			lights.add(new LifxColor(location,modelNum));
			//after each add of a device write it to file
			String file = "listInfo.txt";
			fileIO.writeToFile(lights, file);
		}
	}
}
