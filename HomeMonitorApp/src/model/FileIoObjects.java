package model;

import java.awt.List;
//import java.util.List;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Class that will write and read objects to a file for saving data in the application.
 */
public class FileIoObjects {

	public FileIoObjects() {
	}
	
	/**
	 * Write an ArrayList to a file. 
	 * @param lights ArrayList of Light objects.
	 * @param file
	 */
	public void writeToFile(ArrayList<Light> lights, String file) {
	      ObjectOutputStream outStream = null;
	      try {
	         outStream = new ObjectOutputStream(new FileOutputStream(file));
	         for (Light l : lights) {
	            outStream.writeObject(l);
	         }

	      } catch (IOException ioException) {
	         System.err.println("Error opening file.");
	      } catch (NoSuchElementException noSuchElementException) {
	         System.err.println("Invalid input.");
	      } finally {
	         try {
	            if (outStream != null)
	               outStream.close();
	         } catch (IOException ioException) {
	            System.err.println("Error closing file.");
	         }
	      }
	   }

	//read from file method returns an arraylist of Light objects
	/**
	 * Will read from the file and store into an ArrayList
	 * @param file
	 * @return ArrayList of Light objects.
	 */
	   public ArrayList<Light> readFromFile(String file) {
	      ArrayList<Light> list = new ArrayList<>();
	      ObjectInputStream inputStream = null;
	      try {
	         inputStream = new ObjectInputStream(new FileInputStream(file));
	         while (true) {
	        	 Light p = (Light) inputStream.readObject();
	            list.add(p);
	         }
	      } catch (EOFException eofException) {
	         return list;
	      } catch (ClassNotFoundException classNotFoundException) {
	         System.err.println("Object creation failed.");
	      } catch (IOException ioException) {
	         System.err.println("Error opening file.");
	      } finally {
	         try {
	            if (inputStream != null)
	               inputStream.close();
	         } catch (IOException ioException) {
	            System.err.println("Error closing file.");
	         }
	      }
	      return list;
	   }
}
