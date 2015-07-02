/**
 * Ball.java
 * 
 * @version: 1.0
 * 
 * @revision: initial
 */
import java.util.*;
/**
 * This is the Ball class which calculates the moments of the ball
 * 
 * @author Sandeep Ragila
 */

class Ball extends Thread {
	public void run(){
		while(true) {
			Model.method();   //invokes method from model
			Controller.v1.ballMove(Controller.m1.ballx, Controller.m1.bally);
			try {
				Thread.sleep(30);
			}
			catch(InterruptedException ex){}
		}
	}
}