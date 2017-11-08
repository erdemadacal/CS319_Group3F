package Model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameObject {

	protected Sprite sprite;

	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	protected int color;

	// position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// dimensions
	protected int width;
	protected int height;

	// collision box
	protected int cwidth;
	protected int cheight;

	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;

	public double getXtemp() {
		return xtemp;
	}

	public void setXtemp(double xtemp) {
		this.xtemp = xtemp;
	}

	public double getYtemp() {
		return ytemp;
	}

	public void setYtemp(double ytemp) {
		this.ytemp = ytemp;
	}

	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;

	// animation
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;

	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;

	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed; // if you press longer than it will jump higher

	// constructor
	public GameObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	// for collision detection
	public boolean intersects(GameObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setMapPosition() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void setJumping(boolean b) {
		jumping = b;
	}
	
	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public void draw(Graphics2D g) {
		/*if (facingRight) {
			g.drawImage(sprite.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);

		} else {
			g.drawImage(sprite.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width,
					height, null);
		}*/
		g.drawImage(sprite.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
	}

	public int getx() {
		return (int) x;
	}

	public int gety() {
		return (int) y;
	}

	public int getWidth() {
		return (int) width;
	}

	public int getHeight() {
		return (int) height;
	}

	public int getCWidth() {
		return (int) cwidth;
	}

	public int getCHeight() {
		return (int) cheight;
	}

	public int getdx() {
		return (int) dx;
	}

	public int getdy() {
		return (int) dy;
	}

	public int getCurrRow() {
		return currRow;
	}

	public int getCurrCol() {
		return currCol;
	}

	public int getTileSize() {
		return tileSize;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public boolean getFalling() {
		return falling;
	}
}