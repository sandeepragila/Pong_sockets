/**
 * server.java
 * 
 * @version: 1.0
 * 
 * @revision: initial 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the server class which is used to create the server and create the 
 * data input and output streams to communicate with the client 
 * 
 * @author Sandeep Ragila 
 */

class server extends Thread {
	public static ServerSocket serverSocket;
	public static Socket clientSocket;
	public static String message="false";
	BufferedReader din;
	PrintWriter out;
	static String info;
	//default constructor to set the values 
	server(int port) {
		try{
			serverSocket= new ServerSocket(port);
			clientSocket = serverSocket.accept();
			din = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
			out = new PrintWriter (clientSocket.getOutputStream (), true);
		}
		catch(IOException e) {}
	}
	
	/**
	 * This is the run method which is overridden from the thread class 
	 */
	
	public void run() {
		while(true) {
			Controller.m1.moverPlayer1();  //moving the player1
			Controller.v1.paintPlayer1(Controller.m1.player1x, Controller.m1.player1y);
			try {
				write(""+Controller.m1.player1x+","+Controller.m1.player1y+","
				+Controller.m1.ballx+","+Controller.m1.bally+","+Controller.m1.p1score);
				read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(Model.gameOver)  //if the game ends then the loop breaks
				break;
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException ex){}
		}
	}
	
	/**
	 * This is the read method which is used to read the input stream which is written by
	 * the server and this will be used to pass the scores
	 * 
	 * @return returns the string which is written by the server 
	 * @throws IOException 
	 */

	String read() throws IOException {
		String mess;
		mess= din.readLine();
		message=mess;
		 String[] tokens = mess.split(",");
		 Controller.m1.p2score=Integer.parseInt(tokens[2]);
		 Controller.v1.paintPlayer2(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]) );
		 return tokens[2];
	}

	/**
	 * This is the write method which is used to write the data to the output stream 
	 * 
	 * @param message message is the string which is to pass to the stream 
	 */
	void write(String message) throws IOException {
		out.println(message);
	}

}