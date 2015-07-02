/**
 * game.java
 * 
 * @version: 1.0
 * 
 * @revision: initial
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * This game class is respondible for the creation of the game panel 
 *  
 * @author Sandeep Ragila
 */
public class game extends JPanel {
	Image ball;
	/**
	 * This is the constructor for the game class 
	 */
	public game(){
		Model.playing=true;
		ball= new ImageIcon("Back.JPG").getImage();
	}
	
	/**
	 * This method is used to draw the ball and rectangles and is invoked when
	 * repaint() is called 
	 */
	public void paintComponent(Graphics gc){
		setOpaque(false);
		super.paintComponent(gc);
		gc.drawImage(ball, 0, 0, this);
		gc.setColor(Color.red);  
		//gc.drawImage(ball, Model.ballx, Model.bally, this);
		gc.fillOval(Model.ballx, Model.bally, 8,8);// Draw ball
		gc.setColor(Color.BLACK);
		gc.fillRect(Model.player1x, Model.player1y, 10, 25);// Draw rectangles
		gc.fillRect(Model.player2x, Model.player2y, 10, 25);
		gc.drawString("Player1: "+Model.p1score, 25, 10);//Draw scores
		gc.drawString("Player2: "+Model.p2score, 250, 10);
		if(Model.gameOver)
			gc.drawString("Game Over", 150, 125);
	}
	
}