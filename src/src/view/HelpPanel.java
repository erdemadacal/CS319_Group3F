package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HelpPanel extends JPanel{
	//panel 
			
	private static final long serialVersionUID = -4220837468943517290L;
	public static int WIDTH = 1024, HEIGHT = 768;
	private Font font;
	private JButton returnButton;
			
	//constructor
	public HelpPanel()
	{
		super();
		font = new Font("Verdana",Font.BOLD,40);
		//title.setFont(font);
				
		returnButton= new JButton("Back");
		returnButton.setBackground(Color.orange);
		returnButton.setFont(font);
				
		returnButton.setVisible(false);
		add(returnButton);
				
		//add(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
	}
			
	public JButton getReturnButton()
	{
		return returnButton;
	}
		
	@Override
	protected void paintComponent(Graphics g)
	{
	   	super.paintComponent(g);
	 	returnButton.setVisible(true);
		returnButton.setLocation(315, 700);
		returnButton.setSize(400, 50);
				 
		try {
			 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
			 g.drawImage(image, 0, 0, null);
		    // draw title
			 g.setColor(Color.ORANGE);
			 Font titleFont = new Font("Verdana",Font.BOLD,45);
			 g.setFont(titleFont);
			 g.drawString("View Help",400,100);
			 g.drawRect(20, 150, 300, 450);
			 g.drawRect(350, 150, 300, 450);
			 g.drawRect(690, 150, 315, 450);
			 g.setColor(Color.orange);
			 Font newFont = new Font("Verdana",Font.BOLD,30);
			 g.setFont(newFont);
			 g.drawString("Jump Mechanics",35,195);
			 g.drawString("Power-Ups",705,195);
			 g.setColor(Color.blue);
			 g.drawString("Color Mechanics",365,195);
			 newFont = new Font("Verdana",Font.BOLD,20);
			 g.setFont(newFont);
			 g.setColor(Color.orange);
			 g.drawString("In normal mode you can", 35, 220);
			 g.drawString("jump by pressing the up ", 35, 250);
			 g.drawString("arrow key on keyboard. ", 35, 280);
			 g.drawString("In hard mode you can not", 35, 310);
			 g.drawString("jump manually. However ", 35, 340);
			 g.drawString("the system will make", 35, 370);
			 g.drawString("the jump automatically", 35, 400);
			 g.drawString("when it runs.", 35, 430);
	
			 g.setColor(Color.red);
			 g.drawString("Regarding enemies: Your ", 365, 220);
			 g.drawString("bullets will only damage", 365, 250);
			 g.drawString("enemies of the same", 365, 280);
			 g.drawString("color as the bullet fired.", 365, 310);
			 g.drawString("Regarding tiles: You will ", 365, 340);
			 g.drawString("be able to pass the tiles ", 365, 370);
			 g.drawString("that are the samecolor as ", 365, 400);
			 g.drawString("you. You will be able to", 365, 430);
			 g.drawString("stand on black tiles ", 365, 460);
			 g.drawString("regardless of your color.", 365, 490);
			 	        
			 BufferedImage fire = ImageIO.read(getClass().getResourceAsStream("/fire.jpeg"));
			 g.drawImage(fire, 920, 215, null);
			 BufferedImage color = ImageIO.read(getClass().getResourceAsStream("/color.jpeg"));
			 g.drawImage(color, 920, 295, null);
			 BufferedImage speed = ImageIO.read(getClass().getResourceAsStream("/speed.jpeg"));
			 g.drawImage(speed, 920, 385, null);
			 BufferedImage shield = ImageIO.read(getClass().getResourceAsStream("/shield.jpeg"));
			 g.drawImage(shield, 920, 475, null);
			 BufferedImage health = ImageIO.read(getClass().getResourceAsStream("/health.jpeg"));
			 g.drawImage(health, 830, 530, null);
			 g.setColor(Color.green);
			 g.drawString("-Increase rate of fire\n", 695, 220);
			 g.drawString("-Full spectrum mode\n", 695, 300);
			 g.drawString("-Increased move speed\n", 695, 380);
			 g.drawString("-Temporary Invulnerability\n", 695, 470);
			 g.drawString("-Fill health\n", 695, 550);
			 		 	        
		}
	    catch(Exception e){
		    e.printStackTrace();
		 }
		       
	}
}
