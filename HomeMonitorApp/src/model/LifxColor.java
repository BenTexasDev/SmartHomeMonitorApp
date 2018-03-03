package model;

import java.io.Serializable;
/**
 * LifxColor object extends Light and is for the LifxColor model light bulb. 
 * 
 *
 */
public class LifxColor extends Light implements Serializable{
	
	private static final long serialVersionUID = -7202898785359824754L;
	private int saturation = 65535;
	private int kelvin = 3500;
	private String modelNum;	
	private int lightColor = 65535;
	private int recommended_watts;
	
	
	/**
	 * 10 argument constructor
	 * @param power
	 * @param brightness
	 * @param strobe
	 * @param location
	 * @param modelNum
	 * @param color
	 * @param uptime
	 * @param downtime
	 * @param kelvin
	 * @param recommended_watts
	 */
	public LifxColor(String power, int brightness,boolean strobe,  String location,
			String modelNum, int lightColor, int recommended_watts) {
		
		super (power, brightness, strobe, location);
		setModelNum(modelNum);
		setlightColor(lightColor, 65535, kelvin);
		setRecommended_watts(recommended_watts);
	
	}
	/**
	 * Two argument Constructor
	 * @param location
	 * @param modelNum
	 */
	public LifxColor(String location, String modelNum){
		super(location);
		setLocation(location);
		setModelNum(modelNum);
		setlightColor(0, 65535, 3500);
		setRecommended_watts(0);
		
	}
	/**
	 * Get method for modelNum
	 * @return String modelNum The model number of the Light.
	 */
	public String getModelNum() {
		return modelNum;
	}
	/**
	 * Setter for Model number of the LifxColor object
	 * @param modelNum
	 */
	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	/**
	 * Getter for lightColor.
	 * @return int lightColor
	 */
	public int getlightColor() {
		return lightColor;
	}
	/**
	 * Setter for lightColor. Sets the three values needed by the LifxColor API to change the color.
	 * @param lightColor
	 * @param saturation
	 * @param kelvin
	 */
	public void setlightColor(int lightColor, int saturation, int kelvin) {
		this.lightColor = lightColor;
		this.saturation = saturation;
		this.kelvin = kelvin;
	}

	/**
	 * Getter for recommended watts of the lightbulb. 
	 * @return int recommended watts
	 */
	public int getRecommended_watts() {
		return recommended_watts;
	}

	/**
	 * Setter for recommended watts
	 * @param recommended_watts
	 */
	public void setRecommended_watts(int recommended_watts) {
		this.recommended_watts = recommended_watts;
	}
	/**
	 * Will convert the color,kelvin,brightness from color wheel value to value accepted by the LifX API.
	 * @return String format to be sent to client. 
	 */
	public String convertColorClient(){
		String str = "";
		int colorTmp = (getlightColor() * 65535)/360;
		int brightTmp = convertBrightnessClient(getBrightness());
		str += colorTmp + ","+ saturation+"," + brightTmp + "," + kelvin;
		return str;
	}
	/**
	 * Will convert the brightness form 0-100 to the LifxColor API range.  
	 * @param brightness
	 * @return int brightness
	 */
	// method to convert brightness returns an int
	public int convertBrightnessClient(int brightness){
		int result = 0;
		result = (brightness * 65535)/ 100;
		return result;
	}
	@Override
	public String toString() {
		return super.toString()+" LifxColor modelNum=" + modelNum + ", lightColor=" + lightColor  + ", recommended_watts=" + recommended_watts;
	}



}
