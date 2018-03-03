package model;

import java.io.Serializable;

/**
 * Light Class that holds a Light object. Will hold powerState, brightness, strobeState and Location.
 */
public class Light  implements Serializable{
	
	private String powerState;
	private int brightness = 50;
	private boolean strobeState;
	private String location;
	

	/**
	 * four-argument constructor
	 * @param power state
	 * @param brightness level
	 * @param strobe state
	 * @param location of light
	 */
	public Light(String power, int brightness, boolean strobe, String location){
		this.powerState = power;			
		this.brightness = brightness;
		this.strobeState = strobe;			
		this.location = location;
	}
	
	/**
	 * two argument constructor
	 * @param location
	 */
	public Light(String location){
		this.location = location;
	}
	
	/**
	 * set power state
	 * @param power state True(on)/False(off)
	 */
	public void setPowerState(String power) {
		powerState = power; 
	} 
	

	/**
	 * return power state
	 * @return power state True(on)/False(off)
	 */
	public String getPowerState() {
		return powerState;
	} 
	
	
	/**
	 * get Brightness
	 * @return brightness level 
	 */
	public int getBrightness() {
		return brightness;
	}
	/**
	 * set brightness
	 * @param brightness level
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	/**
	 * strobe state True or False
	 * @return strobeState
	 */
	public boolean isStrobeState() {
		return strobeState;
	}
	/**
	 * set strobeState
	 * @param strobeState
	 */
	public void setStrobeState(boolean strobeState) {
		this.strobeState = strobeState;
	}
	/**
	 * get location of the light (ex. kitchen)
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * set location
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Light [powerState=" + powerState + ", brightness=" + brightness + ", strobeState=" + strobeState
				+ ", location=" + location + "]";
	}
	
}
