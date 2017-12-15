package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import view.Camera;
import view.Game;
import view.Handler;

public class Player extends GameObject {

	private float width = 64, height = 64;
	
	
	private float gravity = 0.3f;
	private final float MAX_SPEED = 10f;

	
	private Handler handler;
	private Camera cam;
	
	private int lives = 3;
	private int maxLives = 5;
	private int damage;


	Texture tex = Game.getInstance();
	
	public Player(float x, float y, Handler handler, Camera cam ,ObjectId id, ColorId color) {
		super(x, y, id, color);
		this.handler = handler;
		this.cam = cam;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if (velX < 0) facing = -1;
		else if (velX > 0) facing = 1;
		
		if (falling || jumping) {
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		// Jumping in hard difficulty
		if (velY > 1 && !Game.isEasy && !jumping) {
			jumping = true;
			velY = -10;
		}
		// if player is sent off screen restart the level
		if(y > Game.HEIGHT)
			handler.restartLevel();
		collision(object);
		System.out.println(lives);

	}
	
	private void collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			// Collision with blocks
			if(tempObject.getId() == ObjectId.Block)
			{
				
				// Collision with solid blocks and fading blocks
				if(tempObject.getColor() != color) {
					
					// making the fading blocks start to fade
					if(((Block)tempObject).getType() >= 4 && ((Block)tempObject).getType() < 8) {
						if(getBounds().intersects(tempObject.getBounds()))
							((Block)tempObject).setFading(true);
					}
					
					// Collision on top side
					if(getBoundsTop().intersects(tempObject.getBounds())) {
						y = tempObject.getY() + 32;
						velY = 0;	
					}
					
					// Collision on bottom side
					if(getBoundsBottom().intersects(tempObject.getBounds())) {
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
					}
					
					// Collision on Left side
					if(getBoundsLeft().intersects(tempObject.getBounds())) {
						x = (float) (tempObject.getX() + tempObject.getBounds().getWidth());
					}
				}
				
				// Collision with spikes
				if(((Block)tempObject).getType() >= 8) {
					if(getBounds().intersects(tempObject.getBounds()))
						handler.restartLevel();
				}		
			}
			// Collision with the level goal
			else if (tempObject.getId() == ObjectId.Gate) {
				//switch level
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.switchLevel();
				}
			} 
			// Collision with the enemy on any side
			else if (tempObject.getId() == ObjectId.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())){
					handler.restartLevel();	
				}
			}
			// Collision with power-ups
			else if (tempObject.getId() == ObjectId.Powerup) {
				if (getBounds().intersects(tempObject.getBounds())) {
					//lives++;
					switch(((PowerUp)tempObject).getType())
					{
					case 0:
						if(lives < maxLives)
							lives++;
						handler.removeObject(tempObject);
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					}
		
				}
			}
		}
	}

	@Override
	// Renders the players on the panel
	public void render(Graphics g) {
		
		// Render players blue sprite
		if(this.color == ColorId.Blue) {
			if(facing == 1)
				g.drawImage(tex.player[0], (int)x, (int)y, null);
			else
				g.drawImage(tex.player[1], (int)x, (int)y, null);
		}
		// Render players red sprite
		else if(this.color == ColorId.Red) {
			if(facing == 1)
				g.drawImage(tex.player[2], (int)x, (int)y, null);
			else
				g.drawImage(tex.player[3], (int)x, (int)y, null);
		}
		// Render players green sprite
		else if(this.color == ColorId.Green) {
			if(facing == 1)
				g.drawImage(tex.player[4], (int)x, (int)y, null);
			else
				g.drawImage(tex.player[5], (int)x, (int)y, null);
		}	
		//Graphics2D g2d = (Graphics2D) g;	
	}
	
	// Get bounds methods
	// Get bounds for bottom collision
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)(x + (width/4)), (int)((int)y + (height/2)), (int)width/2 ,(int)height/2);
	}
	// Get bounds for top collision
	public Rectangle getBoundsTop() {
		return new Rectangle((int)(x + (width/4)), (int)y, (int)width/2 ,(int)height/2);
	}
	// Get bounds for left collision
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5 , (int)5 ,(int)height - 10);
	}
	// Get bounds for right collision
	public Rectangle getBoundsRight() {
		return new Rectangle((int)(x + width - 5), (int)(y) + 5, (int)5 ,(int)height - 10);
	}
	// Get bounds for bullet collision
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width ,(int)height);
	}

}
