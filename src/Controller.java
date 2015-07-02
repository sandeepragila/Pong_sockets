/**
 * Controller.java
 * 
 * @version: 1.0
 * 
 * @revision: initial
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
/**
 * This is the Controller class which has all the controls of the game
 * 
 * @author Sandeep Ragila
 */
public class Controller extends Thread {
	static View v1;
	static Model m1;
	static server serv;
	static client cli;
	static String myinfo;
	/**
	 * This method invokes when a key is pressed 
	 * 
	 * @return returns void 
	 */
	private static void pressListener(KeyEvent evt)
	{

		if(evt.getKeyCode()==KeyEvent.VK_UP) // event when the UP arraw key is pressed
			Model.player2up=true;
		else if(evt.getKeyCode()==KeyEvent.VK_DOWN) //event when the DOWN arrow key is pressed 
			Model.player2down=true;
	}

	/**
	 * This method invokes when a key is released 
	 * 
	 * @return returns void 
	 */ 
	private static void releaseListener(KeyEvent evt) {
		if(evt.getKeyCode()==KeyEvent.VK_UP ) //UP arrow key is released
			Model.player2up=false;
		else if(evt.getKeyCode()==KeyEvent.VK_DOWN) //DOWN arrow key is released
			Model.player2down=false;

	}

	/**
	 * This method is invoked when the controller thread is created 
	 * 
	 * @return returns void 
	 */
	public void run() {
		if(myinfo=="server"){
			new Ball().start();  //a Ball thread is created
		serv.start();    //a Player1 thread is created
		}
		else if(myinfo=="client")
	    cli.start();    //a Player2 thread is created
		while(true) {
			m1.nextMethod();
			if(Model.gameOver) //checks if game is over
				break;
			try {
				Thread.sleep(0);
			}
			catch(InterruptedException ex){}	
		}
		synchronized(myinfo) {
			myinfo.notify();
		}
	}
	
	/**
	 * this method is used to check who won the game  
	 */
	public static void winner() {
		if(myinfo=="server") { //if the player is server
			int score = 0;
				try {
					score= Integer.parseInt(serv.read()); //read the score from the client 
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		if(Model.p1score<score) 
			JOptionPane.showMessageDialog(v1, "Sorry you lost!!! "); //message when player1 loses
		else
			JOptionPane.showMessageDialog(v1, "Hurray!! You WON "); //message when player1 wins
		}
		else if(myinfo=="client") { //if the player is client 
			int score = 0;
			try {
				score= Integer.parseInt(cli.read()); //read the score from the server
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	if(Model.p2score<score)
		JOptionPane.showMessageDialog(v1, "Sorry you lost!!! "); //message when player2 loses
	else
		JOptionPane.showMessageDialog(v1, "Hurray!! You WON "); //message when player2 wins
		}
	}
		
	/**
	 * This method is the main() method which creates objects for the view and the 
	 * mode.  
	 * 
	 * @return returns void 
	 */
	public static void main(String[] args) {
		v1 = new View();  //view object is created
		m1= new Model();  //model Object is created
		boolean set;
		set=v1.what();
		//this is to see if he wants to be client or server 
		if(set) {
			myinfo="client";
			int port = Integer.parseInt(v1.portNumber);
			cli=new client(v1.serverAddress,port);
			v1.View(myinfo);
		}
		else {
			myinfo="server";
			int port = Integer.parseInt(v1.portNumber);
			serv=new server(port);
			v1.View(myinfo); //calling the main view 
		} 
		v1.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {
				pressListener(evt);
			}

			public void keyReleased(KeyEvent evt) {
				releaseListener(evt);
			}
		});

		new Controller().start();//controller thread is created
		synchronized(myinfo) {
			try {
				myinfo.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		winner();
	}
}