package Model;

public class Enemy extends GameObject {

	// enemy stuff
	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;

	public Enemy(TileMap tm) {
		super(tm);
	}

	public void getNextPosition() {
	}

	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}

	public void hit(int damage) {
		if (dead)
			return;
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
	}
}