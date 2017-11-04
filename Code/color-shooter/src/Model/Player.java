package Model;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

	private Sprite sprite;

	// player properties
	private int health;
	private int maxHealth;
	private boolean dead;
	private int color;
	private int rateOfFire;
	private int deaths;

	// bullet
	private boolean shooting;
	private int bulletDamage;
	private ArrayList<Bullet> bullets;

	// scratch
	//private boolean scratching;
	//private int scratchDamage;
	//private int scratchRange;

	// gliding
	//private boolean gliding;

	// animations
	private Sprite sprites;

	private final int[] numFrames = { // each actions frame number
			1,1,1,1,1};

	// animation action
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int SHOOTING = 4;


	public Player(TileMap tm) {
		super(tm);

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;

		facingRight = true;

		health = maxHealth = 5;

		bulletDamage = 5;
		bullets = new ArrayList<Bullet>();


		// load sprites
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Resources/Player_Blue_Weapon.gif"));

			sprite = new Sprite( image, 4 );

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getColor(){return color;}

	/*public void checkAttack(ArrayList<Enemy> enemies) {

		// loop through enemies

		for (int i = 0; i < enemies.size(); i++) {

			Enemy e = enemies.get(i);

			// bullets
			for (int j = 0; j < bullets.size(); j++) {
				if (bullets.get(j).intersects(e)) {
					e.hit(bulletDamage);
					bullets.get(j).setHit();
					break;
				}
			}

			// check enemy collision
			if (intersects(e)) {
				hit(e.getDamage());
			}
		}
	}*/

	public void hit(int damage) {
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
	}

	public void getNextPosition() // by reading
	{
		// movement
		if (left) {
			dx = dx - moveSpeed;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else if (right) {
			dx = dx + moveSpeed;
			if (dx > maxSpeed)
				dx = maxSpeed;
		} else {
			if (dx > 0) {
				dx = dx - stopSpeed;
				if (dx < 0)
					dx = 0;
			} else if (dx < 0) {
				dx = dx + stopSpeed;
				if (dx > 0)
					dx = 0;
			}
		}

		// jump
		if (jumping && !falling) {
			//sfx.get("jump").play();
			dy = jumpStart;
			falling = true;

		}
		// falling
		if (falling) {
			dy = dy + fallSpeed;

			if (dy > 0)
				jumping = false;

			if (dy < 0 && !jumping)
				dy = dy + stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}

	public boolean isShooting() {
		return shooting;
	}
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		// draw bullets
		/*for (Bullet b : bullets) {
			b.draw(g);
		}*/
			super.draw(g);
	}
	public void die(){
		this.dead = true;
		deaths++;
	}
	public int getDeaths(){
		return  deaths;
	}
}