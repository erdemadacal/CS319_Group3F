package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import model.Block;
import model.ColorId;
import model.Flag;
import model.Floater;
import model.Player;
import model.Walker;
import model.GameMap;
import model.Jumper;
import view.BufferedImageLoader;
import view.Camera;
import view.GamePanel;


public class Handler
{
	public GameMap map;
	
	private Camera cam;
	
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	public LinkedList<GameObject> object;
	private int currentLevel;
	public Handler(Camera cam) {
		this.cam = cam;
		map = new GameMap();
		object = map.getObject();
		currentLevel = 1;
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level1.png");			// loading level
		level3 = loader.loadImage("/level2.png");
		level2 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
		level5 = loader.loadImage("/level5.png");
		
		System.out.println(cam.getX());
		//currentLevel = 1;
	}
	
	public int getLevel()
	{
		return currentLevel;
	}
	public void setLevel(int level)
	{
		currentLevel = level;
	}
	
	public void tick() {
		map.tick();
		if(map.isChangeLevel()) {
			map.setChangeLevel(false);
			switchLevel();
	    }
		if(map.getRestart())
		{
			loadLevel(true);
			map.setRestart(false);
		}
		if(map.getContinueGame())
		{
			loadLevel(false);
			map.setContinueGame(false);
		}
		
	}
	
	public void render(Graphics g)
	{
		map.render(g);
	}
	public void loadImageLevel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int xx = 0; xx < height; xx++) {
			for(int yy = 0; yy < width; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				
				// create blocks
				// create solid blocks
				if (red == 0 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 0, ObjectId.Block, ColorId.Black,this));
				if (red == 255 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 1, ObjectId.Block, ColorId.Red,this));
				if (red == 0 && green == 0 && blue == 255)
					addObject(new Block(xx*32, yy*32, 2, ObjectId.Block, ColorId.Blue,this));
				if (red == 0 && green == 255 && blue == 0)
					addObject(new Block(xx*32, yy*32, 3, ObjectId.Block, ColorId.Green,this));
				
				// create fading blocks
				if (red == 50 && green == 50 && blue == 50)
					addObject(new Block(xx*32, yy*32, 4, ObjectId.Block, ColorId.Black,this));
				if (red == 200 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 5, ObjectId.Block, ColorId.Red,this ));
				if (red == 0 && green == 0 && blue == 200)
					addObject(new Block(xx*32, yy*32, 6, ObjectId.Block, ColorId.Blue,this));
				if (red == 0 && green == 200 && blue == 0)
					addObject(new Block(xx*32, yy*32, 7, ObjectId.Block, ColorId.Green,this));
				
				// create spikes
				if (red == 100 && green == 100 && blue == 100)
					addObject(new Block(xx*32, yy*32, 8, ObjectId.Block, ColorId.Black,this));
				if (red == 150 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 9, ObjectId.Block, ColorId.Red,this));
				if (red == 0 && green == 0 && blue == 150)
					addObject(new Block(xx*32, yy*32, 10, ObjectId.Block, ColorId.Blue,this));
				if (red == 0 && green == 150 && blue == 0)
					addObject(new Block(xx*32, yy*32, 11, ObjectId.Block, ColorId.Green,this));
				
				
				// create player
				if (red == 150 && green == 150 && blue == 150)
					addObject(new Player(xx*32, yy*32, ObjectId.Player, ColorId.Blue, this));
				
				// create gate (level goal)
				if (red == 255 && green == 255 && blue == 0)
					addObject(new Flag(xx*32, yy*32,  ObjectId.Gate));
				
				// create enemies
				// create walkers
				if(red == 255 && green == 10 && blue == 10)
					addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red, this));
				if(red == 10 && green == 255 && blue == 10)
					addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green, this));
				if(red == 10 && green == 10 && blue == 255)
					addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue, this));
				// create jumpers
				if(red == 255 && green == 50 && blue == 50)
					addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red, this));
				if(red == 50 && green == 255 && blue == 50)
					addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green, this));
				if(red == 50 && green == 50 && blue == 255)
					addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue, this));
				// create floaters
				if(red == 255 && green == 100 && blue == 100)
					addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red, this));
				if(red == 100 && green == 255 && blue == 100)
					addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green, this));
				if(red == 100 && green == 100 && blue == 255)
					addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue, this));
				
			}
		}
	}
	
	/*public int getCurrentLevel()
	{
		return currentLevel;
	}
	*/
	public void switchLevel() {
		int playerHealth = map.getPlayerHealth();
		int playernumberOfDeaths = map.getNumberOfDeaths();
		clearLevel();
		cam.setX(0);
		
		switch(currentLevel)
		{
		  case 1:
			    loadImageLevel(level2);
			    currentLevel++;
			  //  currentLevel++;
			    break;
		  case 2:
				loadImageLevel(level3);
				currentLevel++;
				//GamePanel.LEVEL++;
				//currentLevel++;
				break;
		  case 3:
			    loadImageLevel(level4);
			    currentLevel++;//GamePanel.LEVEL++;
				//currentLevel++;
				break;
		  case 4:
			    loadImageLevel(level5);
			    currentLevel++;//GamePanel.LEVEL++;
			    //currentLevel++;
			    break;
		}
		map.setPlayerHealth(playerHealth);
		map.setNumberOfDeaths(playernumberOfDeaths);
	}
	public void loadLevel(boolean newGame) {
		int playerHealth = 5;
		int playernumberOfDeaths = 0;
		if(!newGame)
		{
			playerHealth = map.getPlayerHealth();
			playernumberOfDeaths = map.getNumberOfDeaths();
		}
		
		clearLevel();
		cam.setX(0);
		//System.out.println("RESTART");
		switch(currentLevel)//GamePanel.LEVEL)
		{
		  case 1:
			    loadImageLevel(level1);
			    break;
		  case 2:
				loadImageLevel(level2);
				break;
		  case 3:
				loadImageLevel(level3);
				break;
		  case 4:
				loadImageLevel(level4);
				break;
		  case 5:
				loadImageLevel(level5);
				break;
		
		}
		System.out.println(map.getPlayerHealth());
		if(!newGame)
		{
			map.setPlayerHealth(playerHealth);
			map.setNumberOfDeaths(playernumberOfDeaths);
		}
	}
	
	public void clearLevel() {
		object.clear();
	}
	
	public void addObject(GameObject object) {
		map.addObject(object);
	}
	
	public void removeObject(GameObject object){
		map.removeObject(object);
	}
	
	public int getPlayerHealth()
	{
		return map.getPlayerHealth();
	}
	
	public int getNumberOfDeaths()
	{
		return map.getNumberOfDeaths();
	}
	public void setNumberOfDeaths(int death)
	{
		map.setNumberOfDeaths(death);
	}
	public void setPlayerHealth(int health)
	{
		map.setPlayerHealth(health);
	}
	
	/*public void createLevel()
	{
		for (int xx = 0; xx < Game.WIDTH * 2; xx +=32)
			addObject(new Block(xx, Game.HEIGHT - 32, 0, ObjectId.Block, ));
		
		for (int xx =96; xx < Game.WIDTH - 128; xx +=32)
			addObject(new Block(xx, Game.HEIGHT - 192, 0, ObjectId.Block));
		
		for (int yy =0; yy < Game.HEIGHT; yy +=32)
			addObject(new Block(0, yy , 0, ObjectId.Block));
		
		for (int yy =0; yy < Game.HEIGHT; yy +=32)
			addObject(new Block(Game.WIDTH * 2, yy, 0, ObjectId.Block));
	}*/
}
