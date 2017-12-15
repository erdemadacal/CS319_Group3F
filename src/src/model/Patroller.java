package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.Texture;
import view.GamePanel;


public class Patroller extends Enemy {

	
	private Handler handler;
	
	Texture tex = GamePanel.getInstance();
	
	public Patroller(float x, float y, int maxHealth, ObjectId id, ColorId color, Handler handler) { //
		super(x, y, maxHealth, id, color);
		this.handler = handler;
		velX = 2;
		//remove = false;
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if (velX < 0) facing = -1;
		else if (velX > 0) facing = 1;
		
		if (falling) {
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		collision(object);
		
		if(this.isDead())
			handler.removeObject(this);
			//remove = true;
		
	}
	
	private void collision(LinkedList<GameObject> object)
	{
		for(int i = 0;	i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			// Collision with blocks
			if(tempObject.getId() == ObjectId.Block)
			{
				
				// Collision on bottom side
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
				
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
		
	
	public Rectangle getBounds() {

		return new Rectangle((int)(x + (width/4)), (int)((int)y + (height/2)), (int)width/2 ,(int)height/2);
	}
	
	
	public Rectangle getBoundsLeft() {

		return new Rectangle((int)x, (int)y + 5 , (int)5 ,(int)height - 10);
	}
	
	public Rectangle getBoundsRight() {

		return new Rectangle((int)(x + width - 5), (int)(y) + 5, (int)5 ,(int)height - 10);
	}
	
	
	public void render(Graphics g) {
		
		// Walker is Blue
		if(this.color == ColorId.Blue) {
				g.drawImage(tex.walker[0], (int)x, (int)y, null);
		}
		// Walker is Red
		else if(this.color == ColorId.Red) {
				g.drawImage(tex.player[1], (int)x, (int)y, null);
		}
		// Walker is Green
		else if(this.color == ColorId.Green) {
			g.drawImage(tex.player[4], (int)x, (int)y, null);
		}
	}

	
}
