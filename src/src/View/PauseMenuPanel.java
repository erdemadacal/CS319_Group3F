
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

public class PauseMenuPanel extends JPanel{
		//panel 
		public static final int WIDTH = 640;
		public static final int  HEIGHT = 480;
		public static final int SCALE = 2;	
		
		//musics and sounds 
		static final int MS_MIN  = 0;
		static final int MS_MAX  = 10;
		static final int MS_INIT = 5;    //initial volume 		
		
		private String[] selection = {"Resume Game", "View Help", "Return to Main Menu", "Music", "SFX"} ;
		private int selectChoice;
		private JSlider musics;
		private JSlider sfx;
		
		private JButton resumeButton;
		private JButton viewButton;
		private JButton returnButton;
		private JLabel label;
		private Font font;
		//constructor
		public PauseMenuPanel()
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
			resumeButton= new JButton(selection[0]);
			resumeButton.setBackground(Color.GREEN);
			resumeButton.setFont(font);
			viewButton = new JButton(selection[1]);
			viewButton.setBackground(Color.RED);
			viewButton.setFont(font);
			returnButton= new JButton(selection[2]);
			returnButton.setBackground(Color.BLUE);
			returnButton.setFont(font);
			
			resumeButton.setVisible(false);
			viewButton.setVisible(false);
			returnButton.setVisible(false);
		
			add(resumeButton);
			add(viewButton);
			add(returnButton);
			
			selectChoice = 0; 
			//add(title);
			
			
			setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
			setBackground(Color.WHITE);
			setFocusable(true);
			requestFocus();
		}			
			
		@Override
	    protected void paintComponent(Graphics g)
		{
			
		    super.paintComponent(g);
		    musics.setVisible(true);
		    musics.setLocation(450, 450);
		    musics.setSize(300, 50);
		    
		    sfx.setVisible(true);
		    sfx.setLocation(450, 550);
		    sfx.setSize(300, 50);
		    
		    resumeButton.setVisible(true);
		    resumeButton.setLocation(450, 270);
		    resumeButton.setSize(300, 50);
		    
		    viewButton.setVisible(true);
		    viewButton.setLocation(475, 350);
		    viewButton.setSize(250, 50);
		    
		    returnButton.setVisible(true);
		    returnButton.setLocation(400, 650);
		    returnButton.setSize(400, 50);
		    
		     try
		   	 {
		    	 
		    	 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images.jpg"));
		    	 g.drawImage(image, 0, 0, null);
		    	// draw title
			 	 g.setColor(Color.ORANGE);
			 	 Font titleFont = new Font("Verdana",Font.BOLD,45);
			 	 g.setFont(titleFont);
			 	 g.drawString("Pause",525,150);
			 	 
			 	 g.setFont(font);
			 	 g.setColor(Color.BLACK);
			 	 g.drawString(selection[3], 550, 440);
			 	 g.drawString(selection[4], 570, 550);
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

