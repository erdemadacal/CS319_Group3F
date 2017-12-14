package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CreditsPanel extends JPanel{
	
	  private static final long serialVersionUID = 1L;
	   //panel 
		//public static final int WIDTH = 640;
		//public static final int  HEIGHT = 480;
		//public static final int SCALE = 2;
	   public static int WIDTH = 1024, HEIGHT = 768;
		private Font font;
		private JButton returnButton;
		
		//constructor
		public CreditsPanel()
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
			 returnButton.setLocation(405, 600);
			 returnButton.setSize(400, 50);
			 
	    	 try
	    	 {
	    		 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
	    		 g.drawImage(image, 0, 0, null);
	    		// draw title
		 	        g.setColor(Color.ORANGE);
		 	        Font titleFont = new Font("Verdana",Font.BOLD,45);
		 	        g.setFont(titleFont);
		 	        g.drawString("Credits",500,110);
		 	       
		 	       g.setColor(Color.BLACK);
		 	       Font font = new Font("Verdana",Font.BOLD,40);
		 	        g.setFont(font);
		 	       g.drawString("Irem Ural",475,250);
		 	       g.drawString("Erdem Adacal",425,300);
		 	       g.drawString("Hasan Selim Yagci",375,350);
		 	       g.drawString("Alper Mehmet Ozdemir",325,400);

		 	       g.setColor(Color.BLUE);
		 	      g.drawString("https://github.com/erdemadacal/CS319_Group3F",100,500);
	 	        
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	       
	    }
}
