
	package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ChangeSettingsPanel extends JPanel{
		//panel 
		public static final int WIDTH = 640;
		public static final int  HEIGHT = 480;
		public static final int SCALE = 2;	
		
		//musics and sounds 
		static final int MS_MIN  = 0;
		static final int MS_MAX  = 10;
		static final int MS_INIT = 5;    //initial volume 		
		
		private String[] selection = {"Return to Main Menu", "Music", "SFX"} ;
		private int selectChoice;
		//music and sound
		private JSlider musics;
		private JSlider sfx;
		
		//return button
		private JButton returnButton;
		
		private JLabel label;
		private Font font;
		//constructor
		public ChangeSettingsPanel()
		{
			super();
			
			//label = new JLabel("Change Settings");
			//add(label);
			
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
			
			selectChoice = 0; 
			//add(title);
			
			
			setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
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
		    musics.setVisible(true);
		    musics.setLocation(450, 300);
		    musics.setSize(300, 50);
		    
		    sfx.setVisible(true);
		    sfx.setLocation(450, 460);
		    sfx.setSize(300, 50);
		    
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
			 	 g.drawString("Change Settings",400,150);
			 	 
			 	 g.setFont(font);
			 	 g.setColor(Color.BLACK);
			 	g.drawString(selection[1], 560, 270);
			 	g.drawString(selection[2], 570, 430);
			 	//g.drawString(selection[4], 450, 270+i*40);
			 	 //for texts rather than buttons
			 /*	Font newFont = new Font("Verdana",Font.BOLD,35) ;
			 	g.setFont(newFont);
			 	 for(int i = 0; i < selection.length;i++){
			 	    if(i == selectChoice){
			 	       g.setColor(Color.WHITE);
			 	    }
			 	    else{
			 	       g.setColor(Color.RED);
			 	    }
			 	    g.drawString(selection[i], 450, 270+i*40);
			 	 }
			 	  */      
		 	      
		    	 }
		    	 catch(Exception e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		       
		    }
		
	}

