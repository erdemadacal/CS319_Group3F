package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.Texture;
import view.GamePanel;

public class Bullet extends GameObject {

	Texture tex ;
	Handler handler;
	private int bulletSpeed = 10;
	private boolean remove;

	public Bullet(float x, float y, ObjectId id, ColorId color, int direction ,Handler handler) {
		super(x + 24 +(direction * 24), y + 24, id, color);
		tex = GameMap.getInstance();
		this.velX = direction * bulletSpeed;
		this.handler = handler;
		remove = false;
	}


	public void tick(LinkedList<GameObject> object) {
		x += velX;	
		collision(object);
	}


	public void render(Graphics g) {
		if(this.color == ColorId.Blue)
			g.drawImage(tex.bullet[0], (int)x, (int)y, null);
		else if(this.color == ColorId.Red)
			g.drawImage(tex.bullet[1], (int)x, (int)y, null);
		else if(this.color == ColorId.Green)
			g.drawImage(tex.bullet[2], (int)x, (int)y, null);
		
	}
	
	private void collision(LinkedList<GameObject> object)
	{
		for(int i = 0;	i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			// Collision with blocks
			if(tempObject.getId() == ObjectId.Block)
			{
				if(getBounds().intersects(tempObject.getBounds())) {
					//handler.removeObject(this);
					remove = true;
				}	
			} 
			if(tempObject.getId() == ObjectId.Enemy)
			{
				// Check if the bullet has collided with an enemy
				if(getBounds().intersects(tempObject.getBounds())) {
					// Remove bullet on collision with enemy regardless of color
					//handler.removeObject(this);
					remove = true;
					// Check if the enemy has the same color as the bullet 
					if(this.color == tempObject.getColor()) {
						// Remove the enemy as well if it has the same color as the bullet
						((Enemy)tempObject).reduceHealth(1);
			
						
					}
				}
			}
		}
	}
	
	/*public boolean getRemove()
	{
		return remove;
	}
	*/
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
