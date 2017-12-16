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

public class PauseMenuPanel extends JPanel{

	//panel 
			
    private static final long serialVersionUID = -4220837468943517290L;
	public static int WIDTH = 1024, HEIGHT = 768;
	
    //musics and sounds 
	private static final int MS_MIN  = 0;
	private static final int MS_MAX  = 10;
	private static final int MS_INIT = 5;    //initial volume 		
			
	private String[] selection = {"Resume Game", "View Help", "Return to Main Menu", "Music", "SFX"} ;
	private JSlider musics;
	private JSlider sfx;
			
	private JButton resumeButton;
	private JButton viewButton;
	private JButton returnButton;

	private Font font;
	
	//constructor
	public PauseMenuPanel()
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
		resumeButton= new JButton(selection[0]);
		resumeButton.setBackground(Color.GREEN);
		resumeButton.setFont(font);
		viewButton = new JButton(selection[1]);
		viewButton.setBackground(Color.RED);
		viewButton.setFont(font);
		returnButton= new JButton(selection[2]);
		returnButton.setBackground(Color.orange);
		returnButton.setFont(font);
				
		resumeButton.setVisible(false);
		viewButton.setVisible(false);
		returnButton.setVisible(false);
			
		add(resumeButton);
		add(viewButton);
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
	public JButton getResumeButton()
	{
		return resumeButton;
	}
	public JButton getViewHelpButton()
	{
	      return viewButton;
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
	    musics.setLocation(350, 450);
	    musics.setSize(300, 50);
			    
	    sfx.setVisible(true);
	    sfx.setLocation(350, 550);
	    sfx.setSize(300, 50);
		    
	    resumeButton.setVisible(true);
	    resumeButton.setLocation(350, 270);
	    resumeButton.setSize(300, 50);
			    
	    viewButton.setVisible(true);
	    viewButton.setLocation(375, 350);
	    viewButton.setSize(250, 50);
			    
	    returnButton.setVisible(true);
	    returnButton.setLocation(300, 650);
	    returnButton.setSize(400, 50);
			    
	     try
	   	 {
			    	 
	    	 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
	    	 g.drawImage(image, 0, 0, null);
	       	// draw title
		 	 g.setColor(Color.BLUE);
		 	 Font titleFont = new Font("Verdana",Font.BOLD,45);
		 	 g.setFont(titleFont);
		 	 g.drawString("Pause",425,200);
				 	 
		 	 g.setFont(font);
		 	 g.setColor(Color.BLACK);
		 	 g.drawString(selection[3], 450, 440);
		 	 g.drawString(selection[4], 470, 550);
				 	    
			 	      
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
			       
	}
			
}
