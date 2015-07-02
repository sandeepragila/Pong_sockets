/**
 * client.java
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
 * This is the client class which is invoked by the client and this will send the 
 * request to the server and this class is also used to communicate between the 
 * two players 
 * 
 * @author Sandeep Ragila
 */

class client extends Thread{
	static Socket socket;
	static ServerSocket serverSocket;
	static String messagec;
	static PrintWriter out;
	static Socket clientSocket;
	static String myinfo;
	static BufferedReader din2;
	static String serverAddress;

	/**
	 * This is the parameterized constructor which is used to set the address of the client
	 * and also the input and output streams
	 * 
	 * @param add this is the address which is passed 
	 */

	client(String add,int port) {
		try{
			this.serverAddress=add;
			//connecting to the server
			socket = new Socket(serverAddress,port);
			//setting up the input and output streams 
			din2 = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			out = new PrintWriter (socket.getOutputStream (), true);
		}
		catch(IOException e) {}
	}
	
	/**
	 * This is the run method which is called when the thread is started 
	 */
	
	public void run() {
		while(true) {
			Controller.m1.moverPlayer2();  //moving player2
			Controller.v1.paintPlayer2(Controller.m1.player2x, Controller.m1.player2y);
			try {
				write(""+Controller.m1.player2x+","+Controller.m1.player2y+","+Controller.m1.p2score);
				read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(Model.gameOver)  //if the game ends the loop breaks
				break;
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException ex){}
		}
	}
	
	/**
	 * This is the read method which is used to read the input stream which is written by
	 * the server and this will be used to pass the scores and players components 
	 * 
	 * @return returns the string which is written by the server 
	 * @throws IOException 
	 */
	
	String read() throws IOException {
		String mess;
		mess=din2.readLine(); //waiting until someone writes the data into stream
		 String[] tokens = mess.split(",");
		 Controller.m1.p1score=Integer.parseInt(tokens[4]);
			Controller.v1.ballMove(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
		 Controller.v1.paintPlayer1(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]) );
		 return tokens[4];
	}

	/**
	 * This is the write method which is used to write the data to the output stream 
	 * 
	 * @param message message is the string which is to pass to the stream 
	 */

	void write( String message) {
		out.println(message);
	}
}