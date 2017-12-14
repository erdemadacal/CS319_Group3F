package model;

import framework.GameObject;
import framework.ObjectId;

public abstract class Enemy extends GameObject {

	private int health;
	private int maxHealth;
	private int damage;
	
	protected float width = 64, height = 64;
	protected float gravity = 0.3f;
	protected final float MAX_SPEED = 10f;
	//protected boolean remove;

	public Enemy(float x, float y, int maxHealth, ObjectId id, ColorId color) {
		super(x, y, id,color);
		this.maxHealth = maxHealth;
		health = maxHealth;
		
		//remove = false;
	}

	public int getHealth() {
		return health;
	}

	/*public boolean getRemove()
	{
		return remove;
	}
	*/
	public void reduceHealth(int reductionAmount) {
		this.health -= reductionAmount;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isDead() {
		if(health <= 0)
			return true;
		else 
			return false;
	}
	
}
