package model;

import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Will do an api request to wunderground.com to gather the current weather information
 * for the next 10 days. After the api is called it will then save the data to a output file.
 *
 */
public class WeatherAPIGather {

	// Super secret key obtained from wunderground that allows api request
	static String key = "79f551d87f72c1ce";
	static String output = "./src/Forecast.json";
	
	/**
	 * The data that is received from the web site is a string so we use a 
	 * list of strings to store the data
	 */
	static List<String> List = new LinkedList<String>();
	
	/**
	 * This method handles the connection to the web site with api calls. The function will take a url then
	 * save the contents to the LinkedList<String> list.
	 * @param urlToRead	the URL to the web site you want to request an api GET from
	 */
	private static void getHTML(String urlToRead) {
		URL url;
		try {
			url = new URL(urlToRead); // url to api
			HttpURLConnection conn;
			
			try {
				conn = (HttpURLConnection) url.openConnection();
				
				conn.setRequestMethod("GET");
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					List.add(line);
				}
				rd.close();
						
			} catch (IOException e) {
				System.out.println("WeatherAPIGather:GetHTML: Unable to establish connection");
			}

		} catch (MalformedURLException e) {
			System.out.println("WeatherAPIGather:GetHTML: Bad Url");
		}

	}
	
	/**
	 * This function takes the List<String> that getHTML produced then convert that structure into
	 * a text file that stores all the data to a text file.
	 * @param content	the List<String> that getHTML produced.
	 * @param filePath	Where you want to save the file.
	 */
	private static void writeToFile(List<String> content, String filePath) {
		
		Path file = Paths.get(filePath);
		try {
			Files.write(file, content, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method obtains the forecast for the next 10 days for the San Antonio region. In order for it to
	 * properly work it must have a valid wunderground api key. The forecast is saved into a text file called
	 *  Output.txt within the project folder.
	 */
	public static void obtainForcast()
	{
		getHTML("http://api.wunderground.com/api/"+key+"/forecast10day/q/TX/San_Antonio.json");
		writeToFile(List,output);
	}
	
}