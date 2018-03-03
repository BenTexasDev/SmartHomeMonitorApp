package model;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.FileInputStream;
/**
 * 
 * The class User reads a file Login.txt that will contain the user and password
 * to recognize in moment of login
 * it contains a Constructor with no parameters
 * setTempUSer,Set
 * */
public class User {
	/**
	 * @String Username */
	private String userName;
	/**
	 * @string password*/
	private String password;
	
	/**
	 * @StringBuffer tempPass
	 * */
	private StringBuffer tempPass;
	
	/**
	 * @StringBuffer tempUser
	 * */
	private StringBuffer tempUser;
	
	/**
	 * @InputStream inStream for reading file
	 * */
	private InputStream inStream;
	
	private StringBuffer bufStr;
	private static BufferedInputStream buff;
	
	/**
	 * @param username
	 * @param password
	 * @param numDevices
	 * more
	 * */
	public User(){
		openFile();
		String Usr = new String(bufStr);
		StringTokenizer toks = new StringTokenizer(Usr);
		this.userName = toks.nextToken();
		this.password= toks.nextToken();
		tempUser = new StringBuffer();
		tempPass = new StringBuffer();
	}
	
	/**
	 * setTempUser void method to append a character at the end of the StringBuffer
	 * tempUser
	 * @param char ch*/
	public void setTempUser(char ch){
		if(!Character.isIdentifierIgnorable(ch)){
			tempUser.append(ch);
		}
	}
	/**
	 * void method DeleteCharFromTempUser delete a character everytime 
	 * a backspace is hitted*/
	public void deleteCharFromTempUser(){
		if(tempUser.length()>0){
			tempUser.deleteCharAt(tempUser.length()-1);
			tempUser.trimToSize();
		}
	}
	/**
	 * setTempPass append the character pass at the end
	 *  of the tempPass stringbuffer
	 * @param char pass*/
	public void setTempPass(char pass){
		if(!Character.isIdentifierIgnorable(pass))
		tempPass.append(pass);
	}
	
	/**void method deleteCharFromTempPass
	 * deletes one character at tthe time every backspace*/
	public void deleteCharFromTempPass(){
		if(tempPass.length()>0){
			tempPass.deleteCharAt(tempPass.length()-1);
			tempPass.trimToSize();
		}
	}
	
	/**
	 * Boolean UserRecognition()
	 * Returns true if the password and user are same as the login-in file
	 * @return true
	 * @return false
	 */
	public Boolean userRecognition(){
		if(this.userName.equals(tempUser.toString()) &&
				this.password.equals(tempPass.toString())){
			return true;
		}else{
			return false;
		}

	}
	
	/**
	 * private openFile()
	 * explicitly opens a text File containing user and password*/
	private void openFile(){
		inStream = null;
		try {
			inStream = new FileInputStream("login.txt");
			 buff = new BufferedInputStream(inStream);
			bufStr = new StringBuffer();
			 while(buff.available() >0){
				char c = (char)buff.read();
				bufStr.append(c);
			}
		} catch (IOException e) {
			System.err.println("Error Opening FIle");
			e.printStackTrace();
			System.exit(1);
		}	
		
	}

}
 