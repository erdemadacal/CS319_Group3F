package Model;

import java.util.ArrayList;

public class MapObject {

	final static int WIDTH = 1280;
	final static int HEIGHT = 960;

	private Player player;
	private TileMap tileMap;

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	ArrayList<Enemy> enemies;
	ArrayList<Powerup> powerups;
	ArrayList<Bullet> bullets;

	public MapObject() {
		player = new Player(tileMap);
		enemies = new ArrayList<Enemy>();
		powerups = new ArrayList<Powerup>();
		bullets = new ArrayList<Bullet>();
	}

	public void createBullet() {
	}

	public void createEnemy() {
	}

	public void createPowerup() {
	}

	public Player getPlayer() {
		return player;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}