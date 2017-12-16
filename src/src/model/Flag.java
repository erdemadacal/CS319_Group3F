package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Texture;

public class Flag extends GameObject {
	Texture tex;

	public Flag(float x, float y, ObjectId id) {
		super(x, y, id, ColorId.Black);
		tex = GameMap.getInstance();
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		g.drawImage(tex.gate, (int) x, (int) y - 32, 64, 64, null);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

}