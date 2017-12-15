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
			//public static final int WIDTH = 640;
			//public static final int  HEIGHT = 480;
			//public static final int SCALE = 2;
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
				returnButton.setBackground(Color.BLUE);
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
				 returnButton.setLocation(405, 700);
				 returnButton.setSize(400, 50);
				 
		    	 try
		    	 {
		    		 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
		    		 g.drawImage(image, 0, 0, null);
		    		// draw title
			 	        g.setColor(Color.ORANGE);
			 	        Font titleFont = new Font("Verdana",Font.BOLD,45);
			 	        g.setFont(titleFont);
			 	        g.drawString("View Help",500,110);
			 	        g.drawRect(100, 200, 350, 400);
			 	        g.drawRect(470, 200, 350, 400);
			 	        g.drawRect(850, 200, 350, 400);
			 	        g.setColor(Color.blue);
			 	        Font newFont = new Font("Verdana",Font.BOLD,30);
			 	        g.setFont(newFont);
			 	        g.drawString("Jump Mechanics",110,250);
			 	        g.drawString("Color Mechanics",480,250);
			 	        g.setColor(Color.blue);
			 	        g.drawString("Power-Ups",860,250);
			 	        newFont = new Font("Verdana",Font.BOLD,20);
			 	        g.setFont(newFont);
			 	        g.setColor(Color.orange);
			 	        g.drawString("In normal mode you can", 110, 270);
			 	        g.drawString("\n jump by pressing the up ", 110, 300);
			 	        g.drawString("arrow key on the keyboard. ", 110, 330);
			 	        g.drawString("In hard mode you can not", 110, 360);
			 	        g.drawString("jump manually. However the", 110, 390);
			 	        g.drawString("system will have make the ", 110, 420);
			 	        g.drawString("jump automatically when it", 110, 450);
			 	        g.drawString("runs.", 110, 480);
			 
			 	        g.drawString("Regarding enemies: Your ", 480, 270);
			 	        g.drawString("bullets will only damage", 480, 300);
			 	        g.drawString("enemies of the same color as", 480, 330);
			 	        g.drawString("the bullet fired.", 480, 360);
			 	        g.drawString("Regarding tiles: You will be ", 480, 390);
			 	        g.drawString("able to pass the tiles that", 480, 420);
			 	        g.drawString("are the samecolor as you.", 480, 450);
			 	        g.drawString("You will be able to", 480, 480);
			 	        g.drawString("stand on black tiles regardless", 480, 510);
			 	        g.drawString("of your color.", 480, 540);
			 	        
			 	        BufferedImage fire = ImageIO.read(getClass().getResourceAsStream("/fire.jpeg"));
			    		g.drawImage(fire, 1100, 240, null);
			    		BufferedImage color = ImageIO.read(getClass().getResourceAsStream("/color.jpeg"));
			    		g.drawImage(color, 1120, 300, null);
			    		BufferedImage speed = ImageIO.read(getClass().getResourceAsStream("/speed.jpeg"));
			    		g.drawImage(speed, 1130, 370, null);
			    		BufferedImage shield = ImageIO.read(getClass().getResourceAsStream("/shield.jpeg"));
			    		g.drawImage(shield, 1130, 450, null);
			    		BufferedImage health = ImageIO.read(getClass().getResourceAsStream("/health.jpeg"));
			    		g.drawImage(health, 1000, 530, null);
			 	        g.drawString("-Increase rate of fire\n", 860, 270);
			 	        g.drawString("-Full spectrum mode\n", 860, 350);
			 	        g.drawString("-Increased move speed\n", 860, 400);
			 	        g.drawString("-Temporary Invulnerability\n", 860, 480);
			 	        g.drawString("-Fill health\n", 860, 550);
			 	       
			 	        
			 	        
		    	 }
		    	 catch(Exception e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		       
		    }
}
