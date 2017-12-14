package framework;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Block;
import model.Bullet;
import model.ColorId;
import model.Flag;
import model.Player;
import view.BufferedImageLoader;
import view.Camera;
import view.GamePanel;
import view.MainMenuView;

public class GameManager implements GameManagerInterface {

	MainMenuView view;
	public Handler getHandler() {
		return handler;
	}

	Handler handler;
	
	public GameManager(MainMenuView view, Camera cam)
	{
		this.view = view;
	    handler = new Handler(cam);
	}
	
	
	
	public void changeView(int i) //change view menus 
	{
		if(i == 0) // new game
		{
			view.displayLevelPanel(1); 
			handler.loadLevel();
			System.out.println("CHANGE VIEW");
		}
		else if (i == 1)
			view.displayChangeSettingsPanel();
		else if (i == 2)
			view.displayHelpPanel();
		else if(i == 3)
			view.displayCreditsPanel();
		else if(i == 4)
			view.displayMenuPanel();
		else if(i ==5)
			view.displayPauseMenu();
		else if (i == 6)
			view.displayDifficultySelectionPanel();
		else if(i == 7)  // continue game 
		{
			String str = ""; 
			str = loadGame();
			System.out.println("LEVELLL" + str);
			int level;
			level = Integer.parseInt(str);
			view.displayLevelPanel(level);
			handler.loadLevel();
		}
	}
	
	
	public void switchLevel()
	{
		handler.switchLevel();
	}
	
	public void restartLevel()
	{
		handler.loadLevel();
	}
	public int getPlayerHealth()
	{
		return handler.getPlayerHealth();
	}
	
	public void updateLevelView(int c) 
	{
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				//System.out.println("UPDATE");
				if(c ==0) { //left
					tempObject.setVelX(-5);
				}
				if(c == 1) { //right 
					tempObject.setVelX(5);
					//System.out.println("UPDATE_RIGHT");
				}
				
				/*if(c ==2 && !tempObject.isJumping()) //up
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-6);
				}*/
				if( c == 2 && !tempObject.isJumping() && tempObject.getVelY() < 1 && GamePanel.isEasy) 
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if(c == 3) { // shoot
					if (tempObject.getColor() == ColorId.Blue) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Blue, tempObject.getFacing(), handler));
					}
					if (tempObject.getColor() == ColorId.Red) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Red, tempObject.getFacing(), handler));
					}
					if (tempObject.getColor() == ColorId.Green) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Green, tempObject.getFacing(), handler));
					}
				}
				//System.out.println("PLAYER X " + tempObject.getVelX());
				if(c == 4) // change player color to blue 
					tempObject.setColor(ColorId.Blue);
				
				if(c == 5) // change player color to red
			 		tempObject.setColor(ColorId.Red);
			    if( c == 6)
			    	tempObject.setColor(ColorId.Green);
			}
			 
		}
		
		if(c == 7) {
			saveGame();
			System.exit(1);
		}
		view.updateGamePanel();
		
	}
	public void saveGame() 
	{
		try {
	        PrintWriter writer = new PrintWriter("level.txt", "UTF-8");
	        String currentLevel = "" + GamePanel.LEVEL;
	        writer.println(currentLevel); 
	        writer.close();
		}
		catch(Exception e)
		{
		   e.printStackTrace();
		}
	}
	public String loadGame()
	{
		String line = "";
		String level = "0"; 
        BufferedReader reader = null;
        
		try{	
			reader = new BufferedReader(new FileReader("level.txt"));	
		    line = reader.readLine();
		    while (line != null) 
		    {
		      System.out.println("LEVEL IN WHILE" + line);
		      if(line != null)
		      {
		    	  level = line;
		    	  System.out.println("LEVEL IN IF" + level);
		      }
		      line = reader.readLine();	  
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return level; //returns 0 if there is no previous game
	}
	public void tick() {
		handler.tick();
	}
	
	public void render(Graphics g)
	{
		handler.render(g);
	}
	public void loadImageLevel(BufferedImage image) {
		handler.loadImageLevel(image);
		}
	
	public void stopPlayer(int c)
	{	
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
		
			if(tempObject.getId() == ObjectId.Player)
			{
				if(c == 0) tempObject.setVelX(0);
				if(c == 1) tempObject.setVelX(0);
				
			
			}
		}

		view.updateGamePanel();
	}
	
	
	
	
}
