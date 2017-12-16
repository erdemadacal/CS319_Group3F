package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Texture;

public class PowerUp extends GameObject {

	Texture tex = GameMap.getInstance();
	private int type;

	public PowerUp(float x, float y, int type, ObjectId id) {
		super(x, y, id, ColorId.Black);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tex.powerup[type], (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 40, 40);
	}

}