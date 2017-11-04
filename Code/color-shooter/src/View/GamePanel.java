package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GameManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	//panel 
			public static final int WIDTH = 640;
			public static final int  HEIGHT = 480;
			public static final int SCALE = 2;
			private GameManager gm;
			private Graphics2D gr;
			private BufferedImage bf;
			
			//constructor
			public GamePanel()
			{
				super();
				
				bf = new BufferedImage(
						WIDTH, HEIGHT,
						BufferedImage.TYPE_INT_RGB
					);
				gr = (Graphics2D) bf.getGraphics();
			
			//running = true;
			
				gm = new GameManager();
				//add(title);
				setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
				setFocusable(true);
				requestFocus();
				
			}
			
			@Override
		    protected void paintComponent(Graphics g)
		    {
		    	super.paintComponent(g);
		    	gr.drawRect(15, 15, 10, 10);
		    	g.drawRect(40, 40, 10, 10);
		    	 try
		    	 {
		    		// BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
		    		// g.drawImage(image, 0, 0, null);
		    		 gm.drawAll(gr);
		    		 gr.drawRect(10, 10, 10, 10);
			 	        
		 	        
		    	 }
		    	 catch(Exception e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		       
		    }
}
