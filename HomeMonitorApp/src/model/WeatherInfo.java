package model;

/**
 * A structure that will hold weather information.
 * High, Low, Humidity, Snowing|Raining. 
 * Only used for one day, for multiple days make a list, this allows for better
 * flexibility: forecast past a week, or short than a week.
 *
 */
public class WeatherInfo {
	
	/*
	 * Most of the basic data is going to be displayed on the GUI so to avoid multiple conversion
	 * temperature, humidity is stored as strings.
	 */
	private int day;
	private String highTemp;
	private String lowTemp;
	private String humidity;
	private boolean snowing;
	private boolean raining;
		
	/**
	 * This initialization is for the basics
	 * @param day		What day of the week is it
	 * @param highTemp	The high temperature for the day
	 * @param lowTemp	The low temperature for the day
	 * @param humidity	The humidity for the day
	 */
	public WeatherInfo(int day, String highTemp, String lowTemp, String humidity) {
		this.day = day;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
	}
	
	/**
	 * This initialization holds more detailed data
	 * @param day		What day of the week is it
	 * @param highTemp	The high temperature for the day
	 * @param lowTemp	The low temperature for the day
	 * @param humidity	The humidity for the day
	 * @param snowing	Is there a high chance to snow 
	 * @param raining	Is there a high chance to rain
	 */
	public WeatherInfo(int day, String highTemp, String lowTemp, String humidity, boolean snowing, boolean raining) {
		this.day = day;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
		this.snowing = snowing;
		this.raining = raining;
	}

	/**
	 * Returns the day
	 * @return the day as an integer
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Set the day
	 * @param day of the week as an int, this can be relative to whatever day ie week can start on Tuesday
	 * 
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Returns the high temperature for the day
	 * @return	return high temperature for the day as a string
	 */
	public String getHighTemp() {
		return highTemp;
	}

	/**
	 * Set the high temperature for the day
	 * @param highTemp as a string
	 */
	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}

	/**
	 * Returns the low temperature for the day
	 * @return	return low temperature for the day as a string
	 */
	public String getLowTemp() {
		return lowTemp;
	}

	/**
	 * Set the low temperature for the day
	 * @param lowTemp as a string
	 */
	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	/**
	 * Returns the humidity for the day
	 * @return return humidity for the day as a string
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * Set the humidity for the day
	 * @param humidity as a string
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	/**
	 * Is it likely to snow today, the likely chance to snow isn't 
	 * a specified standard ie 70% chance and can be whatever as long as it is
	 * standardized for every day and doesn't vary.
	 * @return	bool likely to snow this day
	 */
	public boolean isSnowing() {
		return snowing;
	}

	/**
	 * Is it likely to snow today, the likely chance to snow isn't 
	 * a specified standard ie 70% chance and can be whatever as long as it is
	 * standardized for every day and doesn't vary.
	 * @param is it likely to snow this day
	 */
	public void setSnowing(boolean snowing) {
		this.snowing = snowing;
	}

	/**
	 * Is it likely to rain today, the likely chance to rain isn't 
	 * a specified standard ie 70% chance and can be whatever as long as it is
	 * standardized for every day and doesn't vary.
	 * @return	bool likely to rain this day
	 */
	public boolean isRaining() {
		return raining;
	}

	/**
	 * Is it likely to rain today, the likely chance to rain isn't 
	 * a specified standard ie 70% chance and can be whatever as long as it is
	 * standardized for every day and doesn't vary.
	 * @param is it likely to rain this day
	 */
	public void setRaining(boolean raining) {
		this.raining = raining;
	}
	

	@Override
	public String toString() {
		return "WeatherInfo [day=" + day + ", highTemp=" + highTemp + ", lowTemp=" + lowTemp + ", humidity=" + humidity + "]";
	}
	
	
	
	

}
