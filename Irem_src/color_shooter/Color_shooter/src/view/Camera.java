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
		if(player.getX() < GamePanel.WIDTH / 2)
			x = 0;
		else if (player.getX() > GamePanel.levelWidth - GamePanel.WIDTH/2 )
			x = -GamePanel.levelWidth + GamePanel.WIDTH;
		else
			x = - player.getX() + GamePanel.WIDTH / 2;
		
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
