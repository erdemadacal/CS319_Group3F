package Model;

import java.util.ArrayList;

public class MapObject {

	private Player player;
	private TileMap tileMap;

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	private ArrayList<Enemy> enemies;
	//private ArrayList<Powerup> powerups;
	private ArrayList<Bullet> bullets;

	public MapObject() {
		tileMap = new TileMap(32);
		tileMap.loadTiles("/Tilesets/Tileset.gif");
		tileMap.loadMap("/Maps/Level1.map");
		tileMap.setPosition(0, 0);
		player = new Player(tileMap);
		enemies = new ArrayList<Enemy>();
		//powerups = new ArrayList<Powerup>();
		bullets = player.getBullets();
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}