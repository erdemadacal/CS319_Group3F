package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import framework.GameManager;
import framework.ObjectId;
import framework.Sound;
import framework.Texture;
import view.BufferedImageLoader;

public class GamePanel extends JPanel {

	
	private static final long serialVersionUID = -4220837468943517290L;

	public static int WIDTH = 1024, HEIGHT = 768;
	
	public BufferedImage level = null, background = null, level2 = null, heart = null;
	//public static BufferedImage[] levels = new BufferedImage[5];
	
	public static int levelWidth;
	public static boolean isEasy = true;
	// Object
	Camera cam;
	static Texture tex;
	
	Random rand = new Random();
	
	public static int LEVEL = 1;
	private GameManager gm;
	
	public GamePanel(MainMenuView view)
	{
		cam = new Camera(0,0);
		gm = new GameManager(view,cam);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		//setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
	}
	public void init() 
	{
		//WIDTH = getWidth();
		//HEIGHT = getHeight();
		
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1.png");			// loading level
		level2 = loader.loadImage("/level2.png");
		background = loader.loadImage("/BackGround.jpg");	// loading background
		heart = loader.loadImage("/Heart.png");
		
		this.cam = new Camera(0,0);
		
		//levels[0] = level;
		//levels[1] = level2;
		
		gm.loadImageLevel(level);

		//handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		//handler.createLevel();
		
		//this.addKeyListener(new KeyInput(handler));
	}
	
	public void tick()
	{
		//System.out.println("TICK");
		levelWidth = getLevelWidth();
		gm.tick();
		for(int i = 0; i < gm.getHandler().object.size(); i++) {
			if(gm.getHandler().object.get(i).getId() == ObjectId.Player)
				cam.tick(gm.getHandler().object.get(i));
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// create graphics2d object from graphics g
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.drawImage(background, (int)cam.getX(), (int)cam.getY(), null);
		for(int i=0 ; i < gm.getPlayerHealth(); i ++)
			g.drawImage(heart, 50 + (45* i), 50, null);
		
		g2d.translate(cam.getX(), cam.getY()); // begin of camera
		gm.render(g);
		g2d.translate(-cam.getX(), -cam.getY()); // end of camera
		//System.out.println("CAM X " + (int)cam.getX() + "CAM Y " + (int)cam.getY());
		///////////////////////////////////////
		g.dispose();
		
		
	}
	
	private int getLevelWidth() {
		switch(LEVEL)
		{
			case 1:
				return 43 * 32;
			case 2:
				return 43 * 32;
			default:
				return 25 * 32;
		}
		
	}
	
	public void setLevel(int level)
	{
		LEVEL = level; 
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public Camera getCamera() {
		return cam;
	}
	public GameManager getGameManager() {
		return gm;
	}
	
	
}
