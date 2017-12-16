package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class Patroller extends Enemy {

	Texture tex;
	
	public Patroller(float x, float y, int maxHealth, ObjectId id, ColorId color) { 
		super(x, y, maxHealth, id, color);
		tex = GameMap.getInstance();
		velX = 2;
		// remove = false;

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

		collision(object);
		returnOnLedge();

		if (this.isDead())
			// handler.removeObject(this);
			remove = true;

	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			// Collision with blocks
			if (tempObject.getId() == ObjectId.Block) {

				// Collision on bottom side
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
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

	private void returnOnLedge() {
		if (velY > 1 && falling) {
			if (velX < 0) {
				this.x += 64;
				this.y = (int) (y / 32) * 32;
				velX = 2;

			} else {
				this.x -= 32;
				this.y = (int) (y / 32) * 32;
				velX = -2;
			}
			System.out.println("X is : " + x + "   Y is : " + y);

		}

	}

	public Rectangle getBounds() {

		return new Rectangle((int) (x + (width / 4)), (int) ((int) y + (height / 2)), (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int) (x + width - 5), (int) (y) + 5, (int) 5, (int) height - 10);
	}

	public void render(Graphics g) {
		// Patroller is Blue
		if (this.color == ColorId.Blue) {
			if (velX < 0)
				g.drawImage(tex.patroller[0], (int) x, (int) y, null);
			// System.out.println("Patroller Blue");
			else
				g.drawImage(tex.patroller[1], (int) x, (int) y, null);
		}
		// Patroller is Red
		else if (this.color == ColorId.Red) {
			if (velX < 0)
				g.drawImage(tex.patroller[2], (int) x, (int) y, null);
			else
				g.drawImage(tex.patroller[3], (int) x, (int) y, null);
		}
		// Patroller is Green
		else if (this.color == ColorId.Green) {
			if (velX < 0)
				g.drawImage(tex.patroller[4], (int) x, (int) y, null);
			else
				g.drawImage(tex.patroller[5], (int) x, (int) y, null);
		}
	}

}
