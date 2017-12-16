package framework;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import model.Bullet;
import model.ColorId;
import view.Camera;
import view.MainMenuView;

public class GameManager implements GameManagerInterface {

	private MainMenuView view;
	private Handler handler;
	public static boolean isEasy;
	
	public GameManager(MainMenuView view, Camera cam)
	{
		this.view = view;
	    handler = new Handler(cam);
	    isEasy = true;
	}
	
	public Handler getHandler() {
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
			handler.loadLevel(false);
		}
		else if(i == 8)//continue from where you left the game, required for pause
		{
			view.displayLevelPanel(1); 
		}
	}
	
	/*
	public void switchLevel()
	{
		handler.switchLevel();
	}
	
	public void restartLevel()
	{
		handler.loadLevel();
	}
	*/
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
				if( c == 2 && !tempObject.isJumping() && tempObject.getVelY() < 1 && isEasy) 
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					SoundManager.JUMP.play();
				}
				if(c == 3) { // shoot
					if (tempObject.getColor() == ColorId.Blue) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Blue, tempObject.getFacing(), handler));
						SoundManager.ATTACK.play();
					}
					if (tempObject.getColor() == ColorId.Red) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Red, tempObject.getFacing(), handler));
						SoundManager.ATTACK.play();
					}
					if (tempObject.getColor() == ColorId.Green) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Green, tempObject.getFacing(), handler));
						SoundManager.ATTACK.play();
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
	        String strCurrentLevel = "" + handler.getLevel();
	        String noOfDeaths = "" + handler.getNumberOfDeaths();
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
			reader = new BufferedReader(new FileReader("level.txt"));	
		    line = reader.readLine();
		    counter ++;
		    while (line != null) 
		    {
		      System.out.println("LEVEL IN WHILE" + line);
		      if(counter == 1)
		      {
		    	  level = line;
		    	  System.out.println("LEVEL IN IF" + level);
		      }
		      if(counter == 2)
		      {
		    	  noOfDeaths = line; 
		    	  System.out.println("DEATH IN IF" + noOfDeaths);
		      }
		      if(counter == 3)
		      {
		    	  health = line; 
		    	  System.out.println("HEALTH IN IF" + health);
		      } 
		      if(counter == 4)
		      {
		    	  easy = line; 
		    	  System.out.println("Easy IN IF" + isEasy);
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
			System.out.println("DEATH : " + intDeath);
		}	
		if(!health.equals("-1"))
		{
			intHealth = Integer.parseInt(health);
			handler.setPlayerHealth(intHealth);
			System.out.println("HEALTH : " + intHealth);
		}
		if(!easy.equals("-1"))
		{
			intIsEasy = Integer.parseInt(easy);
			if(intIsEasy == 0)
				isEasy = false;
			else 
				isEasy = true;
			System.out.println("HEALTH : " + intHealth);
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
		System.out.println("STOP GAME");
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
}
}
