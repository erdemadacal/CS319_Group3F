package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Texture;

public class Jumper extends Enemy {

	Texture tex;

	public Jumper(float x, float y, int maxHealth, ObjectId id, ColorId color) {
		super(x, y, maxHealth, id, color);
		tex = GameMap.getInstance();
		velX = 2;
		remove = false;

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;

		if (falling) {
			velY += gravity;

			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		if (!jumping) {
			velY = -8;
			jumping = true;
		}

		collision(object);

		if (this.isDead())
			remove = true;
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			// Collision with blocks
			if (tempObject.getId() == ObjectId.Block) {

				// Collision on bottom side
				if (getBoundsBottom().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}

				// Collision on Right side
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = (float) (tempObject.getX() - width);
					velX = -2;
				}
				// Collision on Left side
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = (float) (tempObject.getX() + tempObject.getBounds().getWidth());
					velX = 2;

				}
			}
		}
	}

	// collision bounds for bullet collisions
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	// collision bounds for bottom collisions
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) (x + (width / 4)), (int) ((int) y + (height / 2)), (int) width / 2,
				(int) height / 2);
	}

	// collision bounds for left collisions
	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	// collision bounds for right collisions
	public Rectangle getBoundsRight() {

		return new Rectangle((int) (x + width - 5), (int) (y) + 5, (int) 5, (int) height - 10);
	}

	public void render(Graphics g) {

		// Jumper is Blue
		if (this.color == ColorId.Blue) {
			g.drawImage(tex.jumper[0], (int) x, (int) y, null);

		}
		// Jumper is Red
		else if (this.color == ColorId.Red) {
			g.drawImage(tex.jumper[1], (int) x, (int) y, null);

		}
		// Jumper is Green
		else if (this.color == ColorId.Green) {
			g.drawImage(tex.jumper[2], (int) x, (int) y, null);

		}

	}
}