package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ChangeSettingsPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//panel 
	public static int WIDTH = 1024, HEIGHT = 768;
	//musics and sounds 
	private static final int MS_MIN  = 0;
	private static final int MS_MAX  = 10;
	private static final int MS_INIT = 5;    //initial volume 		
			
			private String[] selection = {"Return to Main Menu", "Music", "SFX"} ;
			//music and sound
			private JSlider musics;
			private JSlider sfx;
			
			//return button
			private JButton returnButton;
			private Font font;
			//constructor
			public ChangeSettingsPanel()
			{
				super();
				
				
				//add musics and sound sliders
				musics = new JSlider(JSlider.HORIZONTAL,MS_MIN, MS_MAX, MS_INIT);
				//set tick marks in between
				musics.setMajorTickSpacing(5);
				musics.setMinorTickSpacing(1);
				musics.setPaintTicks(true);
				musics.setBackground(Color.BLACK);
			    musics.setVisible(false);
				add(musics);
				
				sfx = new JSlider(JSlider.HORIZONTAL,MS_MIN, MS_MAX, MS_INIT);
				//set tick marks in between
				sfx.setMajorTickSpacing(5);
				sfx.setMinorTickSpacing(1);
				sfx.setPaintTicks(true);
				sfx.setBackground(Color.BLACK);
			    sfx.setVisible(false);
				add(sfx);
				
				
				font = new Font("Verdana",Font.BOLD,30) ;
				//add button
				returnButton= new JButton(selection[0]);
				returnButton.setBackground(Color.BLUE);
				returnButton.setFont(font);
				
				returnButton.setVisible(false);
				add(returnButton);
				setPreferredSize(new Dimension(WIDTH, HEIGHT));
				setBackground(Color.WHITE);
				setFocusable(true);
				requestFocus();
			}			

			public JButton getReturnButton()
			{
				return returnButton;
			}
			public JSlider getSFX()
			{
				return sfx;
			}
			
			public JSlider getMusics()
			{
				return musics;
			}
			
			@Override
		    protected void paintComponent(Graphics g)
			{
				
	    super.paintComponent(g);
	    musics.setVisible(true);
	    musics.setLocation(350, 300);
	    musics.setSize(300, 50);
	    
	    sfx.setVisible(true);
	    sfx.setLocation(350, 460);
	    sfx.setSize(300, 50);
		    
	    returnButton.setVisible(true);
	    returnButton.setLocation(305, 600);
	    returnButton.setSize(400, 50);
		    
	    try
	    {    	 
		   	 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
		   	 g.drawImage(image, 0, 0, null);
		   	// draw title
		 	 g.setColor(Color.ORANGE);
		 	 Font titleFont = new Font("Verdana",Font.BOLD,45);
		 	 g.setFont(titleFont);
		 	 g.drawString("Change Settings",300,150);
				 	 
		 	 g.setFont(font);
		 	 g.setColor(Color.BLACK);
		 	 g.drawString(selection[1], 460, 270);
		 	 g.drawString(selection[2], 470, 430);
		 	
		}
		catch(Exception e)
	    {
			 e.printStackTrace();
	    }
			       
	}
			
}
