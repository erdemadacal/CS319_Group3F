package view;

import framework.GameObject;

public class Camera  {
	
	private float x, y;
	
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y; 
	}
	
	public void tick(GameObject player)
	{
		if(player.getX() < Game.WIDTH / 2)
			x = 0;
		else if (player.getX() > Game.levelWidth - Game.WIDTH/2 )
			x = -Game.levelWidth + Game.WIDTH;
		else
			x = - player.getX() + Game.WIDTH / 2;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
