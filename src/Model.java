/**
 * Controller.java
 * 
 * @version: 1.0
 * 
 * @revision: initial
 */
import java.util.Random;

import javax.swing.JPanel;
/**
 * This is the Model class which has the logic of the game
 * 
 * @author Sandeep Ragila
 */

public class Model {
	static Random rand= new Random();
	public static int ballx =200;//Math.abs(rand.nextInt()%150); 
	public static int bally =250;//Math.abs(rand.nextInt()%100);
	public static int player1x=10; 
	public static int player1y=100; 
	public static int player2x=380;
	public static int player2y=100;
	static int toright=5; // to the right
	static int toleft= -5; //to the left
	static int toupward=5; // upward
	static int todown= -5; // down
	static int ballw, ballh; // Width and height of the ball
	// Scores
	static int p1score=0, p2score=0;
	static boolean player1up,player1down, player2up, player2down;
	static boolean playing, gameOver=false;
	/**
	 * This method helps in moments of player2  
	 * 
	 * @return returns void 
	 */
	public void moverPlayer2() //user
	{
		JPanel mypane=View.getPanel();
		if (player2up == true && player2y >= 0){
			player2y += todown;
		}
		if (player2down == true && player2y <= (mypane.getHeight()-25)) {
			player2y += toupward;
		}
	}
	/**
	 * This method helps in moments of player1 
	 * 
	 * @return returns void 
	 */
	public void moverPlayer1() //computer 
	{
		JPanel mypane=View.getPanel();  
		if(bally>=player1y	) {
			player1up=false;
			player1down=true;
		}
		else {
			player1up=true;
			player1down=false;
		}
		if (player1up == true && player1y >= 0)
			player1y =bally;//+= todown;
		if (player1down == true && player1y <= (mypane.getHeight()-25)){
			player1y =bally;//+= toupward;
		}
	}

	static boolean leftRight=false;
	static boolean upDown=false;
	/**
	 * This method helps in moments of player2 
	 * 
	 * @return returns void 
	 */
	public static void method() {

		if(playing){
			// The ball move from left to right
			if (leftRight) {
				ballx += toright;
				if (ballx >= (ballw - 8))
					leftRight= false;
			}
			else {
				ballx += toleft;
				if ( ballx <= 0)
					leftRight = true;
			}
			// The ball moves from up to down
			if (upDown){
				bally += toupward;
				if (bally >= (ballh - 30))
					upDown= false;
			}
			else{
				//ball moving down
				bally += todown;
				if ( bally <= 0)
					upDown = true;
			}
		}
	}

	/**
	 * This method is used to calculate the score and game ending calculations 
	 * 
	 * @return returns void 
	 * @throws InterruptedException 
	 */
	public void nextMethod() {
	//	if(ballw!=0) {
		// The score of the player 1
		if (ballx == (ballw-5 )){
			p1score++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// The score of the player 2 
		if ( ballx == 5){
			p2score++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	//	}
		// When the score reach to the value the game will end
		if(p1score==5 || p2score==5){
			playing=false;
			gameOver=true;
			return;
		}
		// The ball stroke with the player 1
		if(ballx==player1x+10 && bally>=player1y && bally<=(player1y+25)){
			leftRight=true;
			return;
		}
		// The ball stroke with the player 2
		if(ballx==(player2x-5) && bally>=player2y && bally<=(player2y+25)){
			leftRight=false;
			return;
		}
	}
}