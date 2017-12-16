package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import model.Bullet;
import model.ColorId;
import model.GameObject;
import model.ObjectId;
import model.Player;
import view.Camera;
import view.MainMenuView;

public class GameManager implements GameManagerInterface {

	private MainMenuView view;
	private GameStateManager handler;
	public static boolean isEasy;
	private URL url;
	private File file;
	
	public GameManager(MainMenuView view, Camera cam)
	{
		this.view = view;
	    handler = new GameStateManager(cam);
	    isEasy = true;
	}
	
	public GameStateManager getHandler() {
		return handler;
	}

	public boolean isEasy()
	{
		return isEasy;
	}
	public void setIsEasy(boolean easy)
	{
		isEasy = easy;
	}
	public void setLevel(int level)
	{
		handler.setLevel(level);
	}

	public int getLevel()
	{
		return handler.getLevel();
	}

	public void changeView(int i) //change view menus 
	{
		if(i == 0) // new game
		{
			view.displayLevelPanel(1); 
			handler.loadLevel(true);
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
			int level;
			level = Integer.parseInt(str);
			view.displayLevelPanel(level);
			handler.loadLevel(false);
		}
		else if(i == 8)//continue from where you left the game, required for pause
		{
			view.displayLevelPanel(1); 
		}
	}
	

	public int getPlayerHealth()
	{
		return handler.getPlayerHealth();
	}
	public int getNumberOfDeaths()
	{
		return handler.getNumberOfDeaths();
	}
	
	public void updateLevelView(int c) 
	{
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				
				if(c ==0) { //left
					if(((Player)tempObject).getIncrementSpeed())
						tempObject.setVelX(-10);
					else
						tempObject.setVelX(-5);
					
				}
				if(c == 1) { //right 
					if(((Player)tempObject).getIncrementSpeed())
						tempObject.setVelX(10);
					else
						tempObject.setVelX(5);
					
				}
				
				
				if( c == 2 && !tempObject.isJumping() && tempObject.getVelY() < 1 && isEasy) 
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					SoundManager.JUMP.play();
				}
				if(c == 3) { // shoot
					if (tempObject.getColor() == ColorId.Blue) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Blue, tempObject.getFacing()));
						SoundManager.ATTACK.play();
					}
					if (tempObject.getColor() == ColorId.Red) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Red, tempObject.getFacing()));
						SoundManager.ATTACK.play();
					}
					if (tempObject.getColor() == ColorId.Green) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Green, tempObject.getFacing()));
						SoundManager.ATTACK.play();
					}
				}
				
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
			url = getClass().getResource("/level.txt");
			file = new File(url.getPath());
		    PrintWriter writer = new PrintWriter(file,"UTF-8");
		    // now do something
		    System.out.println("girdim");
	        String strCurrentLevel = "" + handler.getLevel();
	        System.out.println(handler.getLevel());
	        String noOfDeaths = "" + handler.getNumberOfDeaths();
	        System.out.println(handler.getNumberOfDeaths());
	        String health = "" + handler.getPlayerHealth();
	        String easy = "";
	        if(isEasy)
	        	easy = "" + 1;
	        else 
	        	easy = "" + 0;
	        
	        writer.println(strCurrentLevel);
	        writer.println(noOfDeaths);
	        writer.println(health);
	        writer.println(easy);
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
		String noOfDeaths = "-1";
		String health = "-1";
		String easy = "-1";
		int intDeath = 0; 
		int intHealth = 0; 
		int intIsEasy = 1;
        BufferedReader reader = null;
        int counter = 0;
        
		try{	
			reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/level.txt")));	
		    line = reader.readLine();
		    counter ++;
		    while (line != null) 
		    {
		      if(counter == 1)
		      {
		    	  level = line;
		      }
		      if(counter == 2)
		      {
		    	  noOfDeaths = line; 
		      }
		      if(counter == 3)
		      {
		    	  health = line; 
		      } 
		      if(counter == 4)
		      {
		    	  easy = line; 
		      }
		      line = reader.readLine();	  
		      counter ++;
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!noOfDeaths.equals("-1"))
		{
			intDeath = Integer.parseInt(noOfDeaths);
			handler.setNumberOfDeaths(intDeath);
		}	
		if(!health.equals("-1"))
		{
			intHealth = Integer.parseInt(health);
			handler.setPlayerHealth(intHealth);
		}
		if(!easy.equals("-1"))
		{
			intIsEasy = Integer.parseInt(easy);
			if(intIsEasy == 0)
				isEasy = false;
			else 
				isEasy = true;
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
	public void stopGame()
	{
		handler.clearLevel();
		view.showGamePanel(false);
	}
	public void startSelectionEffect() {
		SoundManager.SELECT.play();
	}
	public void startBackgroundLoop() {
		SoundManager.BACK.loop();
	}
	public void stopBackgroundLoop() {
		SoundManager.BACK.stop();
	}
	public void setVolume(Float f) {
		SoundManager.BACK.setVolume(f);
	}
	public void setMinimumVolume() {
		SoundManager.BACK.setMinimum();
	}
	public void setMaximumVolume() {
		SoundManager.BACK.setMaximum();
	}
	public SoundManager getSFX(int i) {
		return SoundManager.SFX[i];
		}
	public int getSFXLength() {
		return SoundManager.SFX.length;
		}

	public boolean isShield() {
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
		
			if(tempObject.getId() == ObjectId.Player)
			{
				if ( ((Player)tempObject).isShield() )
					return true;
			}
		}
		return false;
	}
	public int getShieldUse() {
		int shieldUse= 0;
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
		
			if(tempObject.getId() == ObjectId.Player)
			{
				shieldUse = ((Player)tempObject).getShieldUse();
					
			}
		}
		return shieldUse;
	}
	
}
