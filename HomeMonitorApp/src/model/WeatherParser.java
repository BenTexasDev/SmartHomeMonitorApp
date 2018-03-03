package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 * This method analyzes forecast data. Weather data and related information functions 
 * should call this method for conversions.
 *
 */
public class WeatherParser {
	private static final String FILENAME = "src/Forecast.json";
	
	// Used to convert integers to strings
	private static String[] strDays = new String[]{
             "Sunday",
             "Monday",
             "Tuesday",
             "Wednesday",
             "Thusday",
             "Friday",
             "Saturday"
           };	
	

	/**
	 * Takes a wunderground 10 day forecast and parses the file for 
	 * day, temperature, and humidity
	 * @param the directory/file name to parse
	 */
	public static void weatherFileParse(ArrayList<WeatherInfo> weekWeatherData)
	{
		
		//Regex for finding the day
		Pattern day = Pattern.compile("\"day\":([0-9]+)");
		
		//Regex for finding the temperatures
		Pattern temp = Pattern.compile("fahrenheit\":\"([0-9]+)\"");
		
		//Regex for finding the humidity
		Pattern humidity = Pattern.compile("\"avehumidity\": ([0-9]+)");;
		
		// Create a scanner to go through the forcast file
		Scanner in = null;
		try {
		    in = new Scanner(new File(FILENAME));
		} catch (FileNotFoundException exception) {
		    System.err.println("failed to open " + FILENAME);
		    System.exit(1);
		}
	
		// Variables we are looking to store
		int iDay = 0;
		String iTempMin = null;
		String iTempMax = null;
		String iHumidity = null;
		int MaxMin = 0;
		
		// Now we begin to parse the file line by line
		while (in.hasNextLine()) {
		    String line = in.nextLine();			// The current line being read in
		    Matcher mDay = day.matcher(line);			// Check if the line contains the date
		    Matcher mTemp = temp.matcher(line);			// Check if the line contains the temperature
		    Matcher mHumidity = humidity.matcher(line);		// Check if the line contains the humidity

		   
		    while (mDay.find())
		    	iDay = Integer.parseInt(mDay.group(1));
		    
		    // There are two strings that contain temperature the first string contains the max, the second the min. 
		    // To know which one is being read a flag is set upon either being read that assign the temperature to 
		    // its correct variable.
		    while (mTemp.find()) {	
		    	if (MaxMin == 0){
		    		iTempMax = mTemp.group(1);
		    		MaxMin = 1;
		    	} else {
		    		iTempMin = mTemp.group(1);
		    		MaxMin = 0;
		    	}
		    }
		    
		    while (mHumidity.find()) {
		    	iHumidity = mHumidity.group(1);
		    	
		    	// When humidity is read it is the end of the data for that day, push into ArrayList
		    	weekWeatherData.add(new WeatherInfo((iDay - 1), iTempMax, iTempMin, iHumidity));
		    	
		    }
		    

		}    
	}
	
	/**
	 * This function takes an integer that represents the day of the week and returns a string 
	 * for the day the integer represented
	 * @param iDay	An integer that represents a day of the week
	 * @return		A string that contains the day the integer represented
	 */
	public static String convIntToString(int iDay){
		
		if (iDay > 6){
			iDay = iDay - 7;
		}
		
		return strDays[iDay];
	}
	
	/**
	 * You need the ability to control the thermostat and read temperature from each room to 
	 * get a true idea of what the temperature should be then you can figure out the best 
	 * temperature for the cost. You would need to store data over a period of time you will 
	 * get an accurate reading on what the ideal temperature is. However due to limitations in
	 * available hardware and limited funds a simple algorithm is used to find the recommend 
	 * temperature instead of an accurate maximum cost saving algorithm. 
	 * @param dayLow	The high for the day
	 * @param dayHigh	The low for the day
	 * @return	Ideal cost saving temperature
	 */
	public static String recommendedTemp(String dayLow, String dayHigh){
		
		 Integer iDayLow = Integer.parseInt(dayLow);
		 Integer iDayHigh = Integer.parseInt(dayHigh);
		 Integer iDayAVG = (iDayHigh + iDayLow)/2;
		 
		 /*
		  * Adjust temperatures to a comfortable setting
		  */
		 // For every degree higher you set your thermostat over 78 degrees Fahrenheit results in
		 // an approximate increase in home energy bill by 6 to 8% PER degree. 
		 Integer idealHigh = 78;
		 
		 // For winter 68 is generally a good cost saving temperature.
		 Integer idealLow = 72;
		 
		 //Extreme cases
		 if (iDayAVG < idealLow) return idealLow.toString();
		 if (iDayAVG > idealHigh) return idealHigh.toString();
		 
		 // If no other returns are called then it is with the idealLow <= iDayAVG <= idealHigh
		 return iDayAVG.toString(); 
	}
	
}



