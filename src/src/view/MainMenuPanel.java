package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{
	    //panel 
	    private static final long serialVersionUID = -4220837468943517290L;
	    public static int WIDTH = 1024, HEIGHT = 768;
		
		//Font
	    private Font font;
	    
	    //menu options
	    private String[] menuOptions = {"NewGame","ContinueGame","ChangeSettings","View Help","Credits","Exit"};
	    
	    //Buttons
	    private JButton newGameButton; 
	    private JButton continueGameButton; 
	    private JButton changeSettingsButton;
	    private JButton viewHelpButton; 
	    private JButton creditsButton;
	    private JButton exitButton;

	    //constructor 
		public MainMenuPanel()//Camera cam)
		{
			super();
			
			//set player buttons
			newGameButton = new JButton(menuOptions[0]); // firstly newGame is selected  
			continueGameButton= new JButton(menuOptions[1]); ; 
			changeSettingsButton= new JButton(menuOptions[2]); 
			viewHelpButton = new JButton(menuOptions[3]); 
			creditsButton= new JButton(menuOptions[4]); 
			exitButton= new JButton(menuOptions[5]); 
			
			//set color of each button
			newGameButton.setBackground(Color.BLUE);
		    continueGameButton.setBackground(Color.RED);
			changeSettingsButton.setBackground(Color.GREEN);
			viewHelpButton.setBackground(Color.BLUE);
			creditsButton.setBackground(Color.RED);
			exitButton.setBackground(Color.BLACK);
			newGameButton.setOpaque(true);
			continueGameButton.setOpaque(true);
			changeSettingsButton.setOpaque(true);
			viewHelpButton.setOpaque(true);
			creditsButton.setOpaque(true);
			exitButton.setOpaque(true);
			 
			font = new Font("Verdana",Font.BOLD,30);
			newGameButton.setFont(font);
			continueGameButton.setFont(font);
			changeSettingsButton.setFont(font);
			viewHelpButton.setFont(font);
			creditsButton.setFont(font);
			exitButton.setFont(font);
		
			newGameButton.setVisible(false);
			continueGameButton.setVisible(false);
			changeSettingsButton.setVisible(false);
			viewHelpButton.setVisible(false);
			creditsButton.setVisible(false);
			exitButton.setVisible(false);
		
			add(newGameButton);
			add(continueGameButton);
			add(changeSettingsButton);
			add(viewHelpButton);
			add(creditsButton);
			add(exitButton);
			
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			setBackground(Color.WHITE);
			setFocusable(true);
			requestFocus();
		   
		}

		public JButton getNewGameButton()
	    {
		   	return newGameButton;
		}
		    
		public JButton getContinueGameButton()
	    {
		   	return continueGameButton;
		}
		public JButton getViewHelpButton()
	    {
		   	return viewHelpButton;
		}
		public JButton getChangeSettingsButton()
		{
		  	return changeSettingsButton;
		}
		public JButton getCreditsButton()
		{
		   	return creditsButton;
		}
		public JButton getExitButton()
		{
		   	return exitButton;
		}
		   
		    
		@Override
		protected void paintComponent(Graphics g)
		{
		   	super.paintComponent(g);
		   	//set buttons locations and make them visible
		    	
		   	newGameButton.setVisible(true);
	    	newGameButton.setLocation(375, 270);
	    	newGameButton.setSize(250, 50);
	 	    
	    	continueGameButton.setVisible(true);
	    	continueGameButton.setLocation(350, 350);
	    	continueGameButton.setSize(300, 50);
	 		    
	    	changeSettingsButton.setVisible(true);
	    	changeSettingsButton.setLocation(300, 430);
	    	changeSettingsButton.setSize(400, 50);
	    	 
	    	viewHelpButton.setVisible(true);
	    	viewHelpButton.setLocation(375, 510);
	    	viewHelpButton.setSize(250, 50);
	    		 
	    	creditsButton.setVisible(true);
	    	creditsButton.setLocation(400, 590);
	    	creditsButton.setSize(200, 50);
	    		 
	    	exitButton.setVisible(true);
	    	exitButton.setLocation(425, 670);
	    	exitButton.setSize(150, 50);
	    		 	
	    	//draw background image	
		   	try{	 
		   	   BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/menuBackground.gif"));
		   	   g.drawImage(image, 0, 0, null);
		    }
		   	catch(Exception e){
		   	   e.printStackTrace();
		   	}

		}
		   
	       
}
