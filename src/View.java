/**
 * View.java
 * 
 * @version: 1.0
 * 
 * @revision: initial
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This is the view class which is used only for the UI and the UI of the whole
 * game is present in this class 
 *  
 * @author Sandeep Ragila
 */

public class View extends JFrame {

	public JPanel jContentPane = null;    
	public static game panel = null;
	static String portNumber;
	static String serverAddress;
	public View(){}
	
	/**
	 * This is the constructor 
	 */

	public void View(String setting) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initialize();
			}
		});

	}
	
	/**
	 * This is the method which asks the player if he wants to be server or client 
	 * 
	 * @return boolean value if he wants server true or else false 
	 */
	
	boolean what() {
		if(JOptionPane.showConfirmDialog(null," Do you want to host?","",0)!=0) {
			serverAddress = JOptionPane.showInputDialog(
					null,
					"Enter IP Address of the Server:",
					"Welcome to the Game!",
					JOptionPane.QUESTION_MESSAGE);
			portNumber = JOptionPane.showInputDialog(
					null,
						"Enter the Port Number:",
				"Welcome to the Game!",
				JOptionPane.QUESTION_MESSAGE);
			return true;	
		}
		else {
					portNumber = JOptionPane.showInputDialog(
						null,
							"Enter the Port Number:",
					"Welcome to the Game!",
					JOptionPane.QUESTION_MESSAGE);
			System.out.println("server ready for connections");
			JOptionPane.showMessageDialog(null,"Waiting for the other player!!");
			return false;		
		}
	}

	/**
	 * This method helps in the ball moments 
	 * 
	 * @return returns void 
	 */

	public void ballMove (int nx, int ny)
	{
		Model.ballx= nx;
		Model.bally= ny;
		Model.ballw=this.getWidth();
		Model.ballh=this.getHeight();
		repaint();
	}
	
	/**
	 * This method helps in the player1 moments 
	 * 
	 * @return returns void 
	 */

	public void paintPlayer1(int x, int y){
		Model.player1x=x;  //x position
		Model.player1y=y;  //y position
		repaint();
	}
	/**
	 * This method helps in the player2 moments 
	 * 
	 * @return returns void
	 */

	public void paintPlayer2(int x, int y){
		Model.player2x=x;  //x position
		Model.player2y=y;  //y position
		repaint();
	}

	BufferedImage img;
	/**
	 * This method initializes JFrame
	 *
	 * @return void
	 */
	public void initialize() {
//		try {
//			img = ImageIO.read(new File("Back.JPG"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(400, 400);    //set the size of the frame
		this.setBackground(Color.yellow);
		this.setContentPane(getJContentPane()); //setting the panel to the frame
	//	setContentPane(new JLabel(new ImageIcon(img)));
		this.setTitle("Pong- "+ Controller.myinfo);
	}
	
	/**
	 * setting the panel 
	 * 
	 * @return returns of type JPanel 
	 */

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setOpaque(false);
			jContentPane.setLayout(new BorderLayout()); //setting the layout to the panel
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	/**
	 * This method creates only one panel 
	 * 
	 * @return returns of type game 
	 */

	public static game getPanel() {
		if (panel == null) {
			panel = new game(); // The panel is created
		}
		return panel;
	}
	
	
}