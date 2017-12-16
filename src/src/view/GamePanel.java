package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameManager;
import model.ObjectId;
import view.BufferedImageLoader;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -4220837468943517290L;

	public static int WIDTH = 1024, HEIGHT = 768;

	private BufferedImage level1 = null, background = null;
	private BufferedImage shield = null, heart = null, colorRed = null, colorBlue = null, colorGreen = null;

	public static int levelWidth;

	private Camera cam;
	private GameManager gm;

	private Font font;
	private JButton returnButton;

	private boolean gameOver;
	private int death;

	public GamePanel(MainMenuView view) {
		cam = new Camera(0, 0);
		gm = new GameManager(view, cam);

		gameOver = false;
		death = 0;

		font = new Font("Verdana", Font.BOLD, 40);

		returnButton = new JButton("Back");
		returnButton.setBackground(Color.BLUE);
		returnButton.setFont(font);
		returnButton.setVisible(false);
		add(returnButton);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level1.png"); // loading level
		background = loader.loadImage("/back.png"); // loading background
		heart = loader.loadImage("/Heart.png");
		shield = loader.loadImage("/PwUp_TempInvulnerability.png");
		colorBlue = loader.loadImage("/Bullet_Blue.gif");
		colorRed = loader.loadImage("/Bullet_Red.gif");
		colorGreen = loader.loadImage("/Bullet_Green.gif");
		this.cam = new Camera(0, 0);

		gm.loadImageLevel(level1);

	}

	public void tick() {
		levelWidth = getLevelWidth();
		gm.tick();
		for (int i = 0; i < gm.getHandler().object.size(); i++) {
			if (gm.getHandler().object.get(i).getId() == ObjectId.Player)
				cam.tick(gm.getHandler().object.get(i));
		}

	}

	public JButton getReturnButton() {
		return returnButton;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Font newFont = new Font("Verdana", Font.BOLD, 45);

		// create graphics2d object from graphics g
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(background, (int) cam.getX(), (int) cam.getY(), null);

		if (gm.getPlayerHealth() == 0) {
			gameOver = true;
			death = gm.getNumberOfDeaths();
		}

		if (!gameOver) {
			newFont = new Font("Verdana", Font.BOLD, 30);
			g.setFont(newFont);
			g.setColor(Color.BLUE);
			g.drawString("Z-", 320, 60);
			g.drawImage(colorBlue, 360, 45, null);
			g.setColor(Color.RED);
			g.drawString("X-", 390, 60);
			g.drawImage(colorRed, 430, 45, null);
			g.setColor(Color.GREEN);
			g.drawString("C-", 460, 60);
			g.drawImage(colorGreen, 500, 45, null);

		}

		if (gameOver) {
			g.setFont(newFont);
			g.setColor(Color.ORANGE);
			g.drawString("GAME OVER !", 345, 250);
			g.drawString("You lost with " + death + " number of deaths.", 90, 400);
			returnButton.setLocation(400, 450);
			returnButton.setSize(200, 50);
			returnButton.setVisible(true);
			gm.stopGame();

		}
		if (gm.getLevel() >= 6 && !gameOver) {

			g.setFont(newFont);
			g.setColor(Color.ORANGE);
			g.drawString("YOU WON !", 345, 250);
			g.drawString("You won after " + death + " number of deaths.", 150, 400);
			returnButton.setLocation(400, 450);
			returnButton.setSize(200, 50);
			returnButton.setVisible(true);
			gm.stopGame();
		}
		g2d.translate(cam.getX(), cam.getY()); // begin of camera
		gm.render(g);
		g2d.translate(-cam.getX(), -cam.getY()); // end of camera

		for (int i = 0; i < gm.getPlayerHealth(); i++)
			g.drawImage(heart, 50 + (45 * i), 50, null);
		for (int i = 0; i < gm.getShieldUse(); i++)
			g.drawImage(shield, 700 + (70 * i), 50, null);
		if (!gameOver || gm.getLevel() >= 6)
			g.dispose();

	}

	public void setGameOver(boolean b) {
		gameOver = b;
	}

	private int getLevelWidth() {
		switch (gm.getLevel()) {
		case 1:
			return 43 * 32;
		case 2:
			return 43 * 32;
		default:
			return 43 * 32;
		}

	}

	public Camera getCamera() {
		return cam;
	}

	public GameManager getGameManager() {
		return gm;
	}
}