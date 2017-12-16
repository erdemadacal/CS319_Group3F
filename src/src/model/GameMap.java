package model;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class GameMap {
	
	private GameObject tempObject;
	private boolean changeLevel;
	private boolean  restart;
	private boolean continueGame;
	public LinkedList<GameObject> object; 
	private static Texture tex;
	
	public GameMap()
	{
		object = new LinkedList<GameObject>();
		tex = new Texture();
		changeLevel = false;
		restart = false;
		continueGame = false;
	}
	public LinkedList<GameObject> getObject() 
	{
		return object;
    }

	public static Texture getInstance() 
	{
		return tex;
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public int getPlayerHealth()
	{
		int playerHealth = -1; // not valid
		for(int i = 0; i < object.size(); i++) 
		{
			tempObject = object.get(i);
			if(tempObject != null)
			{
				if(tempObject.getId() == ObjectId.Player)
				{
					playerHealth = ((Player)tempObject).getHealth();	
				}
			}
			
		}
		return playerHealth;
		
	}
	public int getNumberOfDeaths()
	{
		int numberOfDeaths = -1; // not valid
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				numberOfDeaths = ((Player)tempObject).getNumberOfDeaths();	
			}
		}
		return numberOfDeaths;
		
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
	public void setNumberOfDeaths(int death)
	{
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				((Player)tempObject).setNumberOfDeaths(death);	
			}
		}
		
	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++)
		{

			GameObject	tempObject = object.get(i);
			if(tempObject != null)
			{
				tempObject.tick(object);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(((Player)tempObject).getRestart())
				{
					((Player)tempObject).setRestart(false);
					 restart = true;			
				}
				if(((Player)tempObject).getContinueGame())
				{
					((Player)tempObject).setContinueGame(false);
					 continueGame = true;			
				}
				if(((Player)tempObject).isReachGoal())
				{
					((Player)tempObject).setReachGoal(false);
					changeLevel = true;			
				}
				if(((Player)tempObject).getDecrementHealth())
				{
					((Player)tempObject).setDecrementHealth(false);
					((Player)tempObject).decrementHealth();
					((Player)tempObject).incrementNumberOfDeaths();
				}
				
				
			}
			else if(tempObject.getId() == ObjectId.Bullet)
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
				if(((Player)tempObject).getDecrementShield())
				{
					removeObject(tempObject);		
					
					((Player)tempObject).setDecrementShield(false);
					((Player)tempObject).decrementShield();
				}
				
				
			}
			
			}
		}
	}
	
	
	public boolean isChangeLevel() {
		return changeLevel;
	}
	
	public void setChangeLevel(boolean changeLevel) {
		this.changeLevel = changeLevel;
	}
	
	public boolean getRestart() {
		return restart;
	}
    public void setRestart(boolean b) {
		restart = b;
	}
  
    
    public boolean getContinueGame() {
		return continueGame;
	}
    public void setContinueGame(boolean b) {
		continueGame = b;
	}	

	public void render(Graphics g)
	{
		if(object != null )
		{
			if(!object.isEmpty())
			{
				for(int i = 0; i < object.size(); i++ )
				{
					tempObject = object.get(i);
					tempObject.render(g);
				}
			}
			
		}
		
	}
}
