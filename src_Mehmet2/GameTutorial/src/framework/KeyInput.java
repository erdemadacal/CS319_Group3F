package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Bullet;
import model.ColorId;
import view.Game;
import view.Handler;

public class KeyInput extends KeyAdapter
{
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT) { 
					tempObject.setVelX(5);
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
				}
				if(key == KeyEvent.VK_UP && !tempObject.isJumping() && tempObject.getVelY() < 1 && Game.isEasy) 
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				// Shoot a bullet in the direction the players facing 
				if(key == KeyEvent.VK_SPACE) {
					if (tempObject.getColor() == ColorId.Blue) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Blue, handler, tempObject.getFacing()));
					}
					if (tempObject.getColor() == ColorId.Red) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Red, handler, tempObject.getFacing()));
					}
					if (tempObject.getColor() == ColorId.Green) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, ColorId.Green, handler, tempObject.getFacing()));
					}
					
				}
				// Change player's color based on use input
				// Z for Blue, X for Red, C for Green
				if(key == KeyEvent.VK_Z) {
					tempObject.setColor(ColorId.Blue);
				}
				else if(key == KeyEvent.VK_X) {
					tempObject.setColor(ColorId.Red);
				}
				else if(key == KeyEvent.VK_C) {
					tempObject.setColor(ColorId.Green);
				}
				
				// Restart level
				if(key == KeyEvent.VK_R) {
					handler.restartLevel();
				}
				// Next Level. For testing purposes only
				if(key == KeyEvent.VK_PAGE_UP) {
					handler.switchLevel();
				}
			}		
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
					
				
			}
			
		}
		
	}
	
	
	
}
