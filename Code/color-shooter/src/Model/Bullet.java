package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Bullet extends GameObject {

	private boolean hit; // bullet hit stg or not
	private boolean remove; // remove the bullet from the game
	private int color;

	public Bullet(TileMap tm, boolean right, int color) {
		super(tm);

		facingRight = right;
		moveSpeed = 3.8;

		if (right)
			dx = moveSpeed;
		else
			dx = -moveSpeed;

		width = 16;
		height = 16;
		cwidth = 14;
		cheight = 14;

		// load sprites
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Blue.gif"));
			sprite = new Sprite(image, 6); // as there are 4 frames

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit() {
		if (hit)
			return;
		hit = true;
		dx = 0;
		remove = true;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		super.draw(g);
	}
}