package Controller;

import java.util.ArrayList;
import Model.MapObject;
import Model.TileMap;
import Model.Walker;
import Model.Enemy;
import Model.Player;

public class GameStateManager {
	private MapObject currentMap;
	private TileMap tileMap;
	private ArrayList<Enemy> enemies;
	private Player p;
	
	public GameStateManager() {
		currentMap = new MapObject();
		enemies = currentMap.getEnemies();
		tileMap = currentMap.getTileMap();
		p = currentMap.getPlayer();
	}
	
	public void set() {
		tileMap.loadTiles("/Tileset.gif");
		tileMap.loadMap("/Level1.txt");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		//level view
		//bg = new Background("/BackGround.gif", 0.1);
		
		p.setPosition(640,480);
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
