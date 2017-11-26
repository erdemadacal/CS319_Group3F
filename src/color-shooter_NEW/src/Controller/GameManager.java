package Controller;

import Model.Enemy;
import Model.Player;
import Model.TileMap;
import View.GamePanel;
import Model.Bullet;
import Model.MapObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameManager {
	private GameStateManager gsm;
	//private SoundManager sm;
	//private CollisionManager cm;
	//private InputManager im;
	private Player p;
	private MapObject mapObject;
	private TileMap tileMap;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private Graphics2D g2;

	public GameManager() {
		gsm = new GameStateManager();
		gsm.set();
		mapObject = gsm.getCurrentMap();
		tileMap = mapObject.getTileMap();
		p = gsm.getCurrentMap().getPlayer();
		enemies = gsm.getCurrentMap().getEnemies();
		bullets = gsm.getCurrentMap().getBullets();	
		//cm = new CollisionManager(mapObject);
		//sm = new SoundManager();
		//im = new InputManager();
	}

	public void updateAll() {
		try {
			updatePlayer();
			updateAllEnemies();
			updateAllBullets();
			tileMap.setPosition(GamePanel.WIDTH / 2 - p.getx(),GamePanel.HEIGHT / 2 - p.gety());
			// for(PowerUp b:powerups) {updatePowerUp();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePlayer() {
		p.getNextPosition();
		// checkTileMapCollision();
		p.setPosition(p.getx()+5,p.gety()+5);
		// bullet attack
		if (p.isShooting()) {
			Bullet b = new Bullet(tileMap, p.isRight() ,p.getColor());
			b.setPosition(p.getx(), p.gety());
			bullets.add(b);
		}
		//p.checkAttack(enemies);
		}
	
		/* set direction
		if (right)facingRight = true;
		if (left)facingRight = false;*/
	
	public void updateAllEnemies() {
		for (Enemy e : enemies) {
			updateEnemy(e);
			if (e.isDead()) {
				enemies.remove(e);
			}
		}	
	}
	
	public void updateEnemy(Enemy e) {
		e.getNextPosition();
		//handleEnemyCollision();
		e.setPosition(e.getXtemp(),e.getYtemp());
		// direction flip
		if (e.isRight() && e.getdx() == 0) {
			e.setRight(false);
			e.setLeft(true);
				} else if (e.isLeft() && e.getdx() == 0) {
					e.setRight(true);
					e.setLeft(false);
				}	
	}
	
	public void updateAllBullets() {
		for (Bullet b : bullets) {
			updateBullet(b);
			if (b.shouldRemove()) {
				bullets.remove(b);
			}
		}	
	}
	
	public void updateBullet(Bullet b) {
		//handleBulletCollision();
		b.setPosition(b.getXtemp(),b.getYtemp());
		if (b.getdx() == 0 && !b.isHit()) {b.setHit();}
	}
	
	public void updatePowerUp() {

	}
	public Player getPlayer()
	{
		return p;
	}
	public void drawAll(Graphics g) {
		g2 = (Graphics2D) g;
		tileMap.draw(g2);
		p.draw(g2);
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g2);
		}
	}
	public void drawBullets(Graphics g) {
		g2 = (Graphics2D) g;
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g2);
		}
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) p.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) p.setRight(true);
		if(k == KeyEvent.VK_DOWN) p.setDown(true);
		if(k == KeyEvent.VK_UP) p.setJumping(true);
		if(k == KeyEvent.VK_SPACE) p.setShooting(true);
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) p.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) p.setRight(false);
		if(k == KeyEvent.VK_DOWN) p.setDown(false);
		if(k == KeyEvent.VK_SPACE) p.setShooting(false);
	}
}