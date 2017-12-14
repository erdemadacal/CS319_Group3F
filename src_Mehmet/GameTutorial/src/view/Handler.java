package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import model.Block;
import model.ColorId;
import model.Flag;
import model.Floater;
import model.Jumper;
import model.Player;
import model.Walker;


public class Handler
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	
	public Handler(Camera cam) {
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level3.png");			// loading level
		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
		level5 = loader.loadImage("/level5.png");
		
	}
	
	
	public void tick() {
		for(int i = 0; i < object.size(); i++ )
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++ )
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
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
					addObject(new Block(xx*32, yy*32, 1, ObjectId.Block, ColorId.Red, this));
				if (red == 0 && green == 0 && blue == 255)
					addObject(new Block(xx*32, yy*32, 2, ObjectId.Block, ColorId.Blue, this));
				if (red == 0 && green == 255 && blue == 0)
					addObject(new Block(xx*32, yy*32, 3, ObjectId.Block, ColorId.Green, this));
				
				// create fading blocks
				if (red == 50 && green == 50 && blue == 50)
					addObject(new Block(xx*32, yy*32, 4, ObjectId.Block, ColorId.Black, this));
				if (red == 200 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 5, ObjectId.Block, ColorId.Red, this));
				if (red == 0 && green == 0 && blue == 200)
					addObject(new Block(xx*32, yy*32, 6, ObjectId.Block, ColorId.Blue, this));
				if (red == 0 && green == 200 && blue == 0)
					addObject(new Block(xx*32, yy*32, 7, ObjectId.Block, ColorId.Green, this));
				
				// create spikes
				if (red == 100 && green == 100 && blue == 100)
					addObject(new Block(xx*32, yy*32, 8, ObjectId.Block, ColorId.Black, this));
				if (red == 150 && green == 0 && blue == 0)
					addObject(new Block(xx*32, yy*32, 9, ObjectId.Block, ColorId.Red, this));
				if (red == 0 && green == 0 && blue == 150)
					addObject(new Block(xx*32, yy*32, 10, ObjectId.Block, ColorId.Blue, this));
				if (red == 0 && green == 150 && blue == 0)
					addObject(new Block(xx*32, yy*32, 11, ObjectId.Block, ColorId.Green, this));
				
				
				// create player
				if (red == 150 && green == 150 && blue == 150)
					addObject(new Player(xx*32, yy*32, this, cam, ObjectId.Player, ColorId.Blue));
				
				// create gate (level goal)
				if (red == 255 && green == 255 && blue == 0)
					addObject(new Flag(xx*32, yy*32,  ObjectId.Gate));
				
				// create enemies
				// create walkers
				if(red == 255 && green == 10 && blue == 10)
					addObject(new Walker(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Red));
				if(red == 10 && green == 255 && blue == 10)
					addObject(new Walker(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Green));
				if(red == 10 && green == 10 && blue == 255)
					addObject(new Walker(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Blue));
				// create jumpers
				if(red == 255 && green == 50 && blue == 50)
					addObject(new Jumper(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Red));
				if(red == 50 && green == 255 && blue == 50)
					addObject(new Jumper(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Green));
				if(red == 50 && green == 50 && blue == 255)
					addObject(new Jumper(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Blue));
				// create floaters
				if(red == 255 && green == 100 && blue == 100)
					addObject(new Floater(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Red));
				if(red == 100 && green == 255 && blue == 100)
					addObject(new Floater(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Green));
				if(red == 100 && green == 100 && blue == 255)
					addObject(new Floater(xx*32, yy*32, 3, this, ObjectId.Enemy, ColorId.Blue));
				
			}
		}
	}
	
	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL)
		{
		case 1:
			loadImageLevel(level2);
			break;
		case 2:
			loadImageLevel(level3);
			break;
		case 3:
			loadImageLevel(level4);
			break;
		case 4:
			loadImageLevel(level5);
			break;
		case 5:
			loadImageLevel(level1);
			break;
		}
		
		Game.LEVEL++;
		
	}
	public void restartLevel() {
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL)
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
		
	}
	
	private void clearLevel() {
		object.clear();
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	

}
