package communication;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
/**
 * The Client class establishes the communication between the 
 * client itself and the server via tcpserver (Transmission Control Protocol). 
 * This allowed us to send and receive messages from the server.
 *
 */
 public class Client {
	String ipaddr;			// The IP address which the client will try to communicate with
	int port;				// The port which the client will try communicate through
	Socket clientSocket = null;	// Socket for communication
	String outMessage;		// The used to hold the message that will be sent to the server
	String recievedMessage;	// The message sent back to the client from the server
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	/**
	 * IP address to attempt to make a connection to the port in which
	 * the Raspberry Pi is assigned an IP address
	 * 
	 * 
	 * @param ipaddr
	 * @param port
	 */
	 // IP address to attempt to make a connection to
	 // port on which to connected to the IP address
	 public Client(String ipaddr, int port) {
		 this.ipaddr = ipaddr;
		 this.port = port;
	 }
	 /**
	  * Just for testing
	  */
	 public Client() 
	 {
		 this.ipaddr = "localhost";
		 this.port = 5555;
	 }
	 /**
	  * If the client is connected it will return true.
	  * Mainly used to check if Raspberry Pi is connected to client.
	  * @return
	  */
	public boolean isConnected()
	{
		if (clientSocket != null) return (clientSocket.isConnected() && !clientSocket.isClosed());
		else return false;
	}
	 /**
	  * Starts the connections between the client and server.
	  * Allows us to use an outside server, such as a cell phone.
	  * @return
	  */
	public int startConnection()
	{
		System.out.println("Trying to Connect");
		//create client socket, connect to server
		try {
			this.clientSocket = new Socket(ipaddr, port);
		} catch (UnknownHostException e) { // IP address of the host could not be determined 
			return -1;
		} catch (IOException e) { //I/O error occured when creating the socket
			return -2;
		}
	
		try {
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e1) {
			return -3;
		}
		
		try {
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
		} catch (IOException e) {
			return -4;
		}
		sendMessage("start" + '\0');
		return 1;
	}
	/**
	 * Closes the connection between the server and client.
	 * Checks if it was a successful/unsuccessful connection.
	 * @return
	 */
	public int endConnection()
	{
		// isClosed() -- might want to check if already closed
		// Close connection
		boolean reachable;
		if (clientSocket != null) reachable = clientSocket.isConnected() && !clientSocket.isClosed();
		else reachable = false;
		
		if (reachable){
			try {
				// Just incase it doesn't get one message send 3
				sendMessage("close");
				sendMessage("close");
				sendMessage("close");
				clientSocket.close();
			} catch (IOException e) {  //if an I/O error occurs when closing this socket.
				return -2; 			// Failed to close
			}	
			return 1;				// Successful close
		} else {
			return -2;				// Socket was not connected, or unreachable;
		}
	}
	

	/**
	 * This allows the user to send a message to the server.
	 * The message must end with a new line "'\0'" or it will not 
	 * send the command properly.
	 * @param message
	 * @return
	 */
	public String sendMessage(String message)
	{
		
		try {
			outToServer.writeBytes(message + '\0');
		} catch (IOException e) {
			return null;
		}
				
		// Will read until a newline is received  from server
		try {
			recievedMessage = inFromServer.readLine();
		} catch (IOException e) {
			return null;
		}
			
		// Print out what the server returns
		System.out.println("FROM SERVER " + recievedMessage);

		return recievedMessage;
	}

}
 
 