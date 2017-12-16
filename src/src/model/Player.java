package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.GameManager;
import controller.SoundManager;
import controller.Texture;
import view.GamePanel;

public class Player extends GameObject {

	private float width = 64, height = 64;
	private float gravity = 0.3f;
	private final float MAX_SPEED = 10f;

	private int health;
	private int numberOfDeaths;
	private boolean reachGoal;
	private boolean restart;
	private boolean continueGame;
	private boolean decrementHealth;
	private boolean decrementShield;
	private boolean incrementHealth;
	private boolean incrementSpeed;
	private boolean shield;
	private int shieldUse;
	private boolean isEasy;
	Texture tex;

	public Player(float x, float y, ObjectId id, ColorId color) {
		super(x, y, id, color);
		tex = GameMap.getInstance();
		reachGoal = false;
		restart = false;
		continueGame = false;
		health = 5;
		numberOfDeaths = 0;
		decrementHealth = false;
		decrementShield = false;
		incrementHealth = false;
		incrementSpeed = false;
		shield = false;
		shieldUse = 0;
		isEasy = true;
	}

	public boolean IsEasy() {
		return isEasy;
	}

	public void setIsEasy(boolean easy) {
		isEasy = easy;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		// Jumping in hard difficulty
		if (velY > 1 && !GameManager.isEasy && !jumping) {
			jumping = true;
			velY = -10;
		}
		// if player is sent off screen restart the level
		if (y > GamePanel.HEIGHT) {
			restart = true;
			continueGame = false;
		}
		collision(object);

	}

	public void setRestart(boolean b) {
		restart = b;
	}

	public boolean getRestart() {
		return restart;
	}

	public void setContinueGame(boolean b) {
		continueGame = b;
	}

	public boolean getContinueGame() {
		return continueGame;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getDecrementHealth() {
		return decrementHealth;
	}

	public boolean getDecrementShield() {
		return decrementShield;
	}

	public boolean getIncrementHealth() {
		return incrementHealth;
	}

	public boolean getIncrementSpeed() {
		return incrementSpeed;
	}

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean b) {
		shield = b;
	}

	public int getShieldUse() {
		return shieldUse;
	}

	public void setDecrementHealth(boolean b) {
		decrementHealth = b;
	}

	public void setIncrementHealth(boolean b) {
		incrementHealth = b;
	}

	public void setDecrementShield(boolean b) {
		decrementShield = b;
	}

	public void decrementHealth() {
		health--;
	}

	public void incrementHealth() {
		health++;
	}

	public void decrementShield() {
		shieldUse--;
	}

	public void incrementNumberOfDeaths() {
		numberOfDeaths++;
	}

	public int getNumberOfDeaths() {
		return numberOfDeaths;
	}

	public void setNumberOfDeaths(int death) {
		this.numberOfDeaths = death;
	}

	private void collision(LinkedList<GameObject> object) {
		if (object != null)
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				// Collision with blocks
				if (tempObject.getId() == ObjectId.Block) {

					// Collision with spikes
					if (((Block) tempObject).getType() >= 8) {
						if (getBounds().intersects(tempObject.getBounds())) {
							System.out.println("Decrement health");
							decrementHealth = true;
							if (health > 0) {
								continueGame = true;
								restart = false;
							}

						}
					}

					// Collision with solid blocks and fading blocks
					if (tempObject.getColor() != color) {
						// making the fading blocks start to fade
						if (((Block) tempObject).getType() >= 4 && ((Block) tempObject).getType() < 8) {
							if (getBounds().intersects(tempObject.getBounds()))
								((Block) tempObject).setFading(true);
						}

						// Collision on top side
						if (getBoundsTop().intersects(tempObject.getBounds())) {
							y = tempObject.getY() + 32;
							velY = 0;
						}

						// Collision on bottom side
						if (getBoundsBottom().intersects(tempObject.getBounds())) {
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
						}

						// Collision on Left side
						if (getBoundsLeft().intersects(tempObject.getBounds())) {
							x = (float) (tempObject.getX() + tempObject.getBounds().getWidth());
						}
					}

				}
				// Collision with the level goal
				else if (tempObject.getId() == ObjectId.Gate) {
					// switch level
					if (getBounds().intersects(tempObject.getBounds())) {
						reachGoal = true;
						SoundManager.GATE.play();
					}
				}
				// Collision with the enemy on any side
				else if (tempObject.getId() == ObjectId.Enemy) {
					if (getBounds().intersects(tempObject.getBounds()) && !shield) {
						// health--;
						// numberOfDeaths++;
						decrementHealth = true;
						if (health > 0) {
							continueGame = true;
							restart = false;
						}

					} else if (getBounds().intersects(tempObject.getBounds()) && shield) {
						object.remove(tempObject);
						decrementShield = true;

						if (shieldUse <= 0)
							shield = false;
					}
				}
				// Collision with powerup
				else if (tempObject.getId() == ObjectId.PowerUp) {
					if (getBounds().intersects(tempObject.getBounds()) && ((PowerUp) tempObject).getType() == 0) {
						object.remove(tempObject);
						incrementHealth = true;
					} else if (getBounds().intersects(tempObject.getBounds())
							&& ((PowerUp) tempObject).getType() == 1) {
						object.remove(tempObject);
						incrementSpeed = true;
					} else if (getBounds().intersects(tempObject.getBounds())
							&& ((PowerUp) tempObject).getType() == 2) {
						object.remove(tempObject);
						shield = true;
						shieldUse = 3;
					}
				}

			}

	}

	public boolean isReachGoal() {
		return reachGoal;
	}

	public void setReachGoal(boolean reachGoal) {
		this.reachGoal = reachGoal;
	}

	@Override
	// Renders the players on the panel
	public void render(Graphics g) {

		// Render players blue sprite
		if (this.color == ColorId.Blue) {
			if (facing == 1)
				g.drawImage(tex.player[0], (int) x, (int) y, null);
			else
				g.drawImage(tex.player[1], (int) x, (int) y, null);
		}
		// Render players red sprite
		else if (this.color == ColorId.Red) {
			if (facing == 1)
				g.drawImage(tex.player[2], (int) x, (int) y, null);
			else
				g.drawImage(tex.player[3], (int) x, (int) y, null);
		}
		// Render players green sprite
		else if (this.color == ColorId.Green) {
			if (facing == 1)
				g.drawImage(tex.player[4], (int) x, (int) y, null);
			else
				g.drawImage(tex.player[5], (int) x, (int) y, null);
		}
	}

	// Get bounds methods
	// Get bounds for bottom collision
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) (x + (width / 4)), (int) ((int) y + (height / 2)), (int) width / 2,
				(int) height / 2);
	}

	// Get bounds for top collision
	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + (width / 4)), (int) y, (int) width / 2, (int) height / 2);
	}

	// Get bounds for left collision
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	// Get bounds for right collision
	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 5), (int) (y) + 5, (int) 5, (int) height - 10);
	}

	// Get bounds for bullet collision
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

}