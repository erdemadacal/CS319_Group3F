package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.Texture;
import view.GamePanel;


public class Floater extends Enemy {

	
	private Handler handler;
	// Range must always be a "positive" integer
	private int range = 5;
	private float initialX, finalX;
	
	Texture tex;
	
	public Floater(float x, float y, int maxHealth, ObjectId id, ColorId color, Handler handler) {
		super(x, y, maxHealth, id, color);
		tex = GameMap.getInstance();
		this.handler = handler;
		velX = 2;
		initialX = x;
		finalX = x + range * 32;
		//remove = false;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		
		if (velX < 0) facing = -1;
		else if (velX > 0) facing = 1;
		
		collision(object);
		
		// Check if movement range has been exceeded
		if(x <= initialX)
			velX = 2;
		else if(x >= finalX)
			velX = -2;
		
		if(this.isDead())
			//remove = true;
			handler.removeObject(this);
		
	}
	
	private void collision(LinkedList<GameObject> object)
	{
		for(int i = 0;	i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			// Collision with blocks
			if(tempObject.getId() == ObjectId.Block)
			{

				// Collision on Right side
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = (float)(tempObject.getX() - width); 
					velX = -2;
				}
				// Collision on Left side
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = (float) (tempObject.getX() + tempObject.getBounds().getWidth());
					velX = 2;
		
				}
			} 
		}
	}
		
	// Full-body Collision Box for collision with bullets and players
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	// Left Collision Box
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5 , (int)5 ,(int)height - 10);
	}
	// Bullet Collision Box
	public Rectangle getBoundsRight() {
		return new Rectangle((int)(x + width - 5), (int)(y) + 5, (int)5 ,(int)height - 10);
	}
	
	
	public void render(Graphics g) {
		
		// Walker is Blue
		if(this.color == ColorId.Blue) {
			g.drawImage(tex.floater[0], (int)x, (int)y, null);
			System.out.println("Blue floater");
		}
		// Walker is Red
		else if(this.color == ColorId.Red) {
			g.drawImage(tex.floater[1], (int)x, (int)y, null);
			System.out.println("Red floater");
		}
		// Walker is Green
		else if(this.color == ColorId.Green) {
			g.drawImage(tex.floater[2], (int)x, (int)y, null);
			System.out.println("Green floater");
		}
	}

	
}
