package model;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class GameMap {
	
	private GameObject tempObject;
	private boolean changeLevel = false;
	private boolean  restart = false;
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public LinkedList<GameObject> getObject() {
	return object;
}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public int getPlayerHealth()
	{
		int playerHealth = -1;
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				playerHealth = ((Player)tempObject).getHealth();	
			}
		}
		return playerHealth;
		
	}
	public void setPlayerHealth(int health)
	{
		//int playerHealth = -1;
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				((Player)tempObject).setHealth(health);	
			}
		}
		
	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.tick(object);
			
			/*if(tempObject.getId() == ObjectId.Player)
			{
				/*if(((Player)tempObject).isReachGoal())
				{
					((Player)tempObject).setReachGoal(false);
					changeLevel = true;			
				}*/
				/*if(((Player)tempObject).getRestart())
				{
					((Player)tempObject).setRestart(false);
					 restart = true;			
				}
			}*/
			/*else if(tempObject.getId() == ObjectId.Bullet)
			{
				if(((Bullet)tempObject).getRemove())
				{
					removeObject(tempObject);		
				}
			}
			else if(tempObject.getId() == ObjectId.Block)
			{
				if(((Block)tempObject).getRemove())
				{
					removeObject(tempObject);		
				}
			}
			else if(tempObject.getId() == ObjectId.Enemy)
			{
				if(((Enemy)tempObject).getRemove())
				{
					removeObject(tempObject);		
				}
			}*/
			
		}
	}
	
	
	public boolean isChangeLevel() {
		return changeLevel;
	}
	/*public boolean getRestart() {
		return restart;
	}
    public void setRestart(boolean b) {
		restart = b;
	}
     */
	public void setChangeLevel(boolean changeLevel) {
		this.changeLevel = changeLevel;
	}

	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++ )
		{
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
}
