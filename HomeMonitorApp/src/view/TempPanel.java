package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.WeatherInfo;
import model.WeatherParser;

/**
 * TempPanel extends JPanel. Will create the panel to display 
 * @author team11
 *
 */
public class TempPanel extends JPanel {
	private JLabel lbl_Humidity;
	private JLabel lbl_Rec_Settings;
	private JLabel lbl_HI;
	private JLabel lbl_Low;
	public JLabel lbl_Tdy_Hi;
	public JLabel lbl_Tdy_Humidity;
	public JLabel lbl_Tdy_Low;
	public JLabel lbl_Today;
	public JLabel lbl_Tdy_Rec;
	public JLabel lbl_Day_One; 
	public JLabel lbl_Day_One_Hi;
	public JLabel lbl_Day_One_Low;
	public JLabel lbl_Day_One_Hum;
	public JLabel lbl_Day_Two;
	public JLabel lbl_Day_Two_Hi;
	public JLabel lbl_Day_Two_Low;
	public JLabel lbl_Day_Two_Hum;
	public JLabel lbl_Day_Three;
	public JLabel lbl_Day_Three_Hi;
	public JLabel lbl_Day_Three_Low;
	public JLabel lbl_Day_Three_Hum;
	public JLabel lbl_Day_Four;
	public JLabel lbl_Day_Four_Hi;
	public JLabel lbl_Day_Four_Low;
	public JLabel lbl_Day_Four_Hum;
	private Calendar calToday;
	private BufferedImage img;
	int iDay; // The current day of the week

	private ArrayList<WeatherInfo> weekWeatherData = new ArrayList<WeatherInfo>();
	
	/**
	 * Public method that holds the panel responsible for displaying the Temperature Panel
	 * */
	public void TempPanel() {

		WeatherParser.weatherFileParse(weekWeatherData);
		
		
		calToday = Calendar.getInstance();
		iDay = calToday.get(Calendar.DAY_OF_WEEK) - 1;
		
		//Background Image
		 try {                
	          img = ImageIO.read(new File("Images/sky4.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    
	
		// labels for today
		lbl_Today = new JLabel("Today (" + WeatherParser.convIntToString(iDay) + ")");
		lbl_Today.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Today.setBounds(105, 12, 96, 16);
		add(lbl_Today);

		ImageIcon sun = new ImageIcon("Images/sun2.png");
		JLabel hilabel = new JLabel("", sun, JLabel.CENTER);
		JPanel hi = new JPanel(new BorderLayout());
		hi.setBackground(null);
		hi.setOpaque(false);
		hi.add(hilabel);
		hi.setBounds(118,35,30,30);
		this.add(hi);
		
		//Image label for Hi
		ImageIcon flake = new ImageIcon("Images/flake1.png");
		JLabel lowlabel = new JLabel("", flake, JLabel.CENTER);
		JPanel low= new JPanel(new BorderLayout());
		low.setBackground(null);
		low.setOpaque(false);
		low.add(lowlabel);
		low.setBounds(230,35,30,30);
		this.add(low);
		//Image label for Low
		ImageIcon rain = new ImageIcon("Images/humid1.png");
		JLabel humidlabel = new JLabel("", rain, JLabel.CENTER);
		JPanel humid= new JPanel(new BorderLayout());
		humid.setBackground(null);
		humid.setOpaque(false);
		humid.add(humidlabel);
		humid.setBounds(185,198,30,30);
		this.add(humid);
		//Image label for Recommended Temp
		ImageIcon both = new ImageIcon("Images/weather1.png");
		JLabel reclabel = new JLabel("", both, JLabel.CENTER);
		JPanel rec= new JPanel(new BorderLayout());
		rec.setBackground(null);
		rec.setOpaque(false);
		rec.add(reclabel);
		rec.setBounds(490,9,30,30);
		this.add(rec);
		
		
		// labels for today's hi/low/humidity/rec settings
		lbl_Tdy_Hi = new JLabel(weekWeatherData.get(0).getHighTemp()+"\u00B0");
		lbl_Tdy_Hi.setOpaque(false);
		lbl_Tdy_Hi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tdy_Hi.setForeground(Color.RED);
		lbl_Tdy_Hi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 48));
		lbl_Tdy_Hi.setBackground(null);
		lbl_Tdy_Hi.setBounds(49, 60, 116, 71);
		add(lbl_Tdy_Hi);

		lbl_Tdy_Humidity = new JLabel(weekWeatherData.get(0).getHumidity()+"%");
		lbl_Tdy_Humidity.setOpaque(false);
		lbl_Tdy_Humidity.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tdy_Humidity.setForeground(Color.BLACK);
		lbl_Tdy_Humidity.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 48));
		lbl_Tdy_Humidity.setBackground(null);
		lbl_Tdy_Humidity.setBounds(99, 130, 116, 71);
		add(lbl_Tdy_Humidity);
		
		
		lbl_Tdy_Low = new JLabel(weekWeatherData.get(0).getLowTemp()+"\u00b0");
		lbl_Tdy_Low.setVerticalAlignment(SwingConstants.TOP);
		lbl_Tdy_Low.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tdy_Low.setForeground(Color.BLUE);
		lbl_Tdy_Low.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 48));
		lbl_Tdy_Low.setBounds(160, 67, 116, 71);
		lbl_Tdy_Low.setBackground(null);
		lbl_Tdy_Low.setOpaque(false);
		add(lbl_Tdy_Low);

		lbl_Tdy_Rec = new JLabel(WeatherParser.recommendedTemp(
				weekWeatherData.get(0).getLowTemp(), weekWeatherData.get(0).getHighTemp())+"\u00b0");
		lbl_Tdy_Rec.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tdy_Rec.setForeground(new Color(50,205,50));
		lbl_Tdy_Rec.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 99));
		lbl_Tdy_Rec.setBounds(295, 34, 251, 168);
		lbl_Tdy_Rec.setBackground(null);
		lbl_Tdy_Rec.setOpaque(false);
		add(lbl_Tdy_Rec);

		// just labels that shouldn't be changed
		lbl_Humidity = new JLabel("Humidity");
		lbl_Humidity.setBounds(132, 203, 61, 16);
		add(lbl_Humidity);

		lbl_Rec_Settings = new JLabel("Recommended Settings");
		lbl_Rec_Settings.setBounds(355, 12, 150, 16);
		add(lbl_Rec_Settings);

		lbl_HI = new JLabel("HI");
		lbl_HI.setBounds(100, 40, 19, 16);
		add(lbl_HI);

		lbl_Low = new JLabel("Low");
		lbl_Low.setBounds(202, 40, 31, 16);
		add(lbl_Low);

		// day one
		lbl_Day_One = new JLabel(WeatherParser.convIntToString(iDay+1));
		lbl_Day_One.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_One.setBounds(34, 231, 96, 16);
		add(lbl_Day_One);
		// day one high
		lbl_Day_One_Hi = new JLabel(weekWeatherData.get(1).getHighTemp()+"\u00b0");
		lbl_Day_One_Hi.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_One_Hi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_One_Hi.setForeground(Color.BLACK);
		lbl_Day_One_Hi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_One_Hi.setBounds(24, 250, 52, 36);
		add(lbl_Day_One_Hi);
		// day one low
		lbl_Day_One_Low = new JLabel(weekWeatherData.get(1).getLowTemp()+"\u00b0");
		lbl_Day_One_Low.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_One_Low.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_One_Low.setForeground(Color.BLACK);
		lbl_Day_One_Low.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_One_Low.setBounds(88, 250, 52, 36);
		add(lbl_Day_One_Low);
		// day one humidity
		lbl_Day_One_Hum = new JLabel(weekWeatherData.get(1).getHumidity()+"%");
		lbl_Day_One_Hum.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_One_Hum.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_One_Hum.setForeground(Color.BLACK);
		lbl_Day_One_Hum.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_One_Hum.setBounds(56, 282, 58, 36);
		add(lbl_Day_One_Hum);

		// day two
		lbl_Day_Two = new JLabel(WeatherParser.convIntToString(iDay + 2));
		lbl_Day_Two.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Two.setBounds(175, 231, 96, 16);
		add(lbl_Day_Two);

		// day two high
		lbl_Day_Two_Hi = new JLabel(weekWeatherData.get(2).getHighTemp()+"\u00b0");
		lbl_Day_Two_Hi.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Two_Hi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Two_Hi.setForeground(Color.BLACK);
		lbl_Day_Two_Hi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Two_Hi.setBounds(165, 250, 52, 36);
		add(lbl_Day_Two_Hi);
		// day two low
		lbl_Day_Two_Low = new JLabel(weekWeatherData.get(2).getLowTemp()+"\u00b0");
		lbl_Day_Two_Low.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Two_Low.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Two_Low.setForeground(Color.BLACK);
		lbl_Day_Two_Low.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Two_Low.setBounds(222, 250, 52, 36);
		add(lbl_Day_Two_Low);
		// day two humidity
		lbl_Day_Two_Hum = new JLabel(weekWeatherData.get(2).getHumidity()+"%");
		lbl_Day_Two_Hum.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Two_Hum.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Two_Hum.setForeground(Color.BLACK);
		lbl_Day_Two_Hum.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Two_Hum.setBounds(195, 282, 58, 36);
		add(lbl_Day_Two_Hum);

		// day three
		lbl_Day_Three = new JLabel(WeatherParser.convIntToString(iDay + 3));
		lbl_Day_Three.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Three.setBounds(314, 231, 96, 16);
		add(lbl_Day_Three);

		lbl_Day_Three_Hi = new JLabel(weekWeatherData.get(3).getHighTemp()+"\u00b0");
		lbl_Day_Three_Hi.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Three_Hi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Three_Hi.setForeground(Color.BLACK);
		lbl_Day_Three_Hi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Three_Hi.setBounds(304, 247, 52, 36);
		add(lbl_Day_Three_Hi);

		lbl_Day_Three_Low = new JLabel(weekWeatherData.get(3).getLowTemp()+"\u00b0");
		lbl_Day_Three_Low.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Three_Low.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Three_Low.setForeground(Color.BLACK);
		lbl_Day_Three_Low.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Three_Low.setBounds(358, 247, 52, 36);
		add(lbl_Day_Three_Low);

		lbl_Day_Three_Hum = new JLabel(weekWeatherData.get(3).getHumidity()+"%");
		lbl_Day_Three_Hum.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Three_Hum.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Three_Hum.setForeground(Color.BLACK);
		lbl_Day_Three_Hum.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Three_Hum.setBounds(335, 282, 58, 36);
		add(lbl_Day_Three_Hum);

		// day four

		lbl_Day_Four = new JLabel(WeatherParser.convIntToString(iDay + 4));
		lbl_Day_Four.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Four.setBounds(450, 231, 96, 16);
		add(lbl_Day_Four);

		// day four hi
		lbl_Day_Four_Hi = new JLabel(weekWeatherData.get(4).getHighTemp()+"\u00b0");
		lbl_Day_Four_Hi.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Four_Hi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Four_Hi.setForeground(Color.BLACK);
		lbl_Day_Four_Hi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Four_Hi.setBounds(438, 247, 52, 36);
		add(lbl_Day_Four_Hi);
		// day four low
		lbl_Day_Four_Low = new JLabel(weekWeatherData.get(4).getLowTemp()+"\u00b0");
		lbl_Day_Four_Low.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Four_Low.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Four_Low.setForeground(Color.BLACK);
		lbl_Day_Four_Low.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Four_Low.setBounds(494, 247, 52, 36);
		add(lbl_Day_Four_Low);
		// day four hum
		lbl_Day_Four_Hum = new JLabel(weekWeatherData.get(4).getHumidity()+"%");
		lbl_Day_Four_Hum.setVerticalAlignment(SwingConstants.TOP);
		lbl_Day_Four_Hum.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Day_Four_Hum.setForeground(Color.BLACK);
		lbl_Day_Four_Hum.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		lbl_Day_Four_Hum.setBounds(468, 282, 58, 36);
		add(lbl_Day_Four_Hum);
	}
	/**
	 * adds the background image
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.drawImage(img, 0, 0, this);
	  }


}
