package controller;

import java.awt.image.BufferedImage;

import model.*;

public class LevelFactory {
	public void loadImageLevel(BufferedImage image, GameMap map) {
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
					map.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block, ColorId.Black));
				if (red == 255 && green == 0 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block, ColorId.Red));
				if (red == 0 && green == 0 && blue == 255)
					map.addObject(new Block(xx*32, yy*32, 2, ObjectId.Block, ColorId.Blue));
				if (red == 0 && green == 255 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 3, ObjectId.Block, ColorId.Green));
				
				// create fading blocks
				if (red == 50 && green == 50 && blue == 50)
					map.addObject(new Block(xx*32, yy*32, 4, ObjectId.Block, ColorId.Black));
				if (red == 200 && green == 0 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 5, ObjectId.Block, ColorId.Red));
				if (red == 0 && green == 0 && blue == 200)
					map.addObject(new Block(xx*32, yy*32, 6, ObjectId.Block, ColorId.Blue));
				if (red == 0 && green == 200 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 7, ObjectId.Block, ColorId.Green));
				
				// create spikes
				if (red == 100 && green == 100 && blue == 100)
					map.addObject(new Block(xx*32, yy*32, 8, ObjectId.Block, ColorId.Black));
				if (red == 150 && green == 0 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 9, ObjectId.Block, ColorId.Red));
				if (red == 0 && green == 0 && blue == 150)
					map.addObject(new Block(xx*32, yy*32, 10, ObjectId.Block, ColorId.Blue));
				if (red == 0 && green == 150 && blue == 0)
					map.addObject(new Block(xx*32, yy*32, 11, ObjectId.Block, ColorId.Green));
				
				
				// create player
				if (red == 150 && green == 150 && blue == 150)
					map.addObject(new Player(xx*32, yy*32, ObjectId.Player, ColorId.Blue));
				
				// create gate (level goal)
				if (red == 255 && green == 255 && blue == 0)
					map.addObject(new Flag(xx*32, yy*32,  ObjectId.Gate));
				
				// create enemies
				// create walkers
				if(red == 255 && green == 10 && blue == 10)
					map.addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red));
				if(red == 10 && green == 255 && blue == 10)
					map.addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green));
				if(red == 10 && green == 10 && blue == 255)
					map.addObject(new Walker(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue));
				// create jumpers
				if(red == 255 && green == 50 && blue == 50)
					map.addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red));
				if(red == 50 && green == 255 && blue == 50)
					map.addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green));
				if(red == 50 && green == 50 && blue == 255)
					map.addObject(new Jumper(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue));
				// create floaters
				if(red == 255 && green == 100 && blue == 100)
					map.addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red));
				if(red == 100 && green == 255 && blue == 100)
					map.addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green));
				if(red == 100 && green == 100 && blue == 255)
					map.addObject(new Floater(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue));
				// powerup
				if(red == 10 && green == 10 && blue == 10)
					map.addObject(new PowerUp(xx*32, yy*32, 0, ObjectId.PowerUp));
				if(red == 15 && green == 15 && blue == 15)
					map.addObject(new PowerUp(xx*32, yy*32, 1, ObjectId.PowerUp));
				if(red == 20 && green == 20 && blue == 20)
					map.addObject(new PowerUp(xx*32, yy*32, 2, ObjectId.PowerUp));
				// create patrollers
				if(red == 255 && green == 150 && blue == 150)
					map.addObject(new Patroller(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Red));
				if(red == 150 && green == 255 && blue == 150)
					map.addObject(new Patroller(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Green));
				if(red == 150 && green == 150 && blue == 255)
					map.addObject(new Patroller(xx*32, yy*32, 3, ObjectId.Enemy, ColorId.Blue));
			}
		}
	}
}
