package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Walker extends Enemy {

	private BufferedImage[] sprites;

	public Walker(TileMap tm) {
		super(tm);

		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		health = maxHealth = 2;
		damage = 1;

		// load sprites

		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Walker.gif"));// enemy
			sprite = new Sprite(image, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}

		right = true;
		facingRight = true;
	}

	public void getNextPosition() {

		// movement
		if (left) {
			dx = dx - moveSpeed;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else if (right) {
			dx = dx + moveSpeed;
			if (dx > maxSpeed)
				dx = maxSpeed;
		}
		// falling
		if (falling) {
			dy += fallSpeed;
		}
	}

	public void draw(Graphics2D g) {

		// if(notOnScreen()) return; //not necessary

		setMapPosition();
		super.draw(g);
	}
}