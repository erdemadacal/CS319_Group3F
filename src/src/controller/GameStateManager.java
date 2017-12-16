package controller;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import model.GameMap;
import model.GameObject;
import view.BufferedImageLoader;
import view.Camera;


public class GameStateManager {
	public GameMap map;

	private Camera cam;
	LevelFactory factory;
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	public LinkedList<GameObject> object;
	private int currentLevel;

	public GameStateManager(Camera cam) {
		this.cam = cam;
		map = new GameMap();
		object = map.getObject();
		currentLevel = 1;
		factory = new LevelFactory();
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level1.png"); // loading level
		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
		level5 = loader.loadImage("/level5.png");

		System.out.println(cam.getX());
		// currentLevel = 1;
	}

	public int getLevel() {
		return currentLevel;
	}

	public void setLevel(int level) {
		currentLevel = level;
	}
	public void loadImageLevel(BufferedImage image) {
		factory.loadImageLevel(image, map);
	}

	public void tick() {
		map.tick();
		if (map.isChangeLevel()) {
			map.setChangeLevel(false);
			switchLevel();
		}
		if (map.getRestart()) {
			loadLevel(true);
			map.setRestart(false);
		}
		if (map.getContinueGame()) {
			loadLevel(false);
			map.setContinueGame(false);
		}

	}

	public void render(Graphics g) {
		map.render(g);
	}

	public void switchLevel() {
		int playerHealth = map.getPlayerHealth();
		int playernumberOfDeaths = map.getNumberOfDeaths();
		clearLevel();
		cam.setX(0);

		switch (currentLevel) {
		case 1:
			factory.loadImageLevel(level2,map);
			currentLevel++;
			// currentLevel++;
			break;
		case 2:
			factory.loadImageLevel(level3,map);
			currentLevel++;
			// GamePanel.LEVEL++;
			// currentLevel++;
			break;
		case 3:
			factory.loadImageLevel(level4,map);
			currentLevel++;// GamePanel.LEVEL++;
			// currentLevel++;
			break;
		case 4:
			factory.loadImageLevel(level5,map);
			currentLevel++;// GamePanel.LEVEL++;
			// currentLevel++;
			break;
		case 5:
			currentLevel++;
		}
		map.setPlayerHealth(playerHealth);
		map.setNumberOfDeaths(playernumberOfDeaths);
	}

	public void loadLevel(boolean newGame) {
		int playerHealth = 5;
		int playernumberOfDeaths = 0;
		if (!newGame) {
			playerHealth = map.getPlayerHealth();
			playernumberOfDeaths = map.getNumberOfDeaths();
		}

		clearLevel();
		cam.setX(0);
		// System.out.println("RESTART");
		switch (currentLevel)// GamePanel.LEVEL)
		{
		case 1:
			factory.loadImageLevel(level1,map);
			break;
		case 2:
			factory.loadImageLevel(level2,map);
			break;
		case 3:
			factory.loadImageLevel(level3,map);
			break;
		case 4:
			factory.loadImageLevel(level4,map);
			break;
		case 5:
			factory.loadImageLevel(level5,map);
			break;
		}
		System.out.println(map.getPlayerHealth());
		if (!newGame) {
			map.setPlayerHealth(playerHealth);
			map.setNumberOfDeaths(playernumberOfDeaths);
		}
	}

	public void clearLevel() {
		object.clear();
	}

	public void addObject(GameObject object) {
		map.addObject(object);
	}

	public void removeObject(GameObject object) {
		map.removeObject(object);
	}

	public int getPlayerHealth() {
		return map.getPlayerHealth();
	}

	public int getNumberOfDeaths() {
		return map.getNumberOfDeaths();
	}

	public void setNumberOfDeaths(int death) {
		map.setNumberOfDeaths(death);
	}

	public void setPlayerHealth(int health) {
		map.setPlayerHealth(health);
	}

}