package Controller;

import java.util.ArrayList;
import Model.MapObject;
import Model.TileMap;
import Model.Walker;
import Model.Bullet;
import Model.Enemy;
import Model.Player;

public class GameStateManager {
	private MapObject currentMap;
	private TileMap tileMap;
	private ArrayList<Enemy> enemies;
	private Player p;
	
	public GameStateManager() {
		currentMap = new MapObject();
		tileMap = currentMap.getTileMap();
		p = currentMap.getPlayer();
		enemies = currentMap.getEnemies();
	}
	
	public void set() {
		
	
		
		//level view
		//bg = new Background("/BackGround.gif", 0.1);
		p.setPosition(100,100);
		populateEnemies();
		
		//bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		//bgMusic.play();
	}
	
	public void populateEnemies() {
		Walker w1 = new Walker(tileMap);
		w1.setPosition(128, 64);
		enemies.add(w1);
		/*Walker w2 = new Walker(tileMap);
		w2.setPosition(128, 64);
		enemies.add(w2);
		Walker w3 = new Walker(tileMap);
		w3.setPosition(128, 64);
		enemies.add(w3);
		Walker w4 = new Walker(tileMap);
		w4.setPosition(128, 64);
		enemies.add(w4);
		Walker w5 = new Walker(tileMap);
		w5.setPosition(128, 64);
		enemies.add(w5);*/
	}
	
	public MapObject getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(MapObject currentMap) {
		this.currentMap = currentMap;
	}
}
