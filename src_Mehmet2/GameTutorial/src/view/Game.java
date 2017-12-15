package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;

public class Game extends JPanel implements Runnable {

	
	private static final long serialVersionUID = -4220837468943517290L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH = 1024, HEIGHT = 768;
	public static int levelWidth;
	public static boolean isEasy = false;
	
	public BufferedImage level = null, background = null, level2 = null;
	// Object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	Random rand = new Random();
	
	public static int LEVEL = 1;
	
	private void init() 
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();

		background = loader.loadImage("/BackGround.jpg");	// loading background
		
		
		cam = new Camera(0, 0);
		handler = new Handler(cam);
		
		handler.restartLevel();;

		
		this.addKeyListener(new KeyInput(handler));
	}

	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() 
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			repaint();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		levelWidth = getLevelWidth();
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.Player)
				cam.tick(handler.object.get(i));
		}
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// create graphics2d object from graphics g
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.drawImage(background, (int)cam.getX(), (int)cam.getY(), null);
		
		g2d.translate(cam.getX(), cam.getY()); // begin of camera
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY()); // end of camera
		
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
				return 43 * 32;
		}
		
	}
	
	
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String args[]) {

		Game g = new Game();
		
		g.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		g.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		g.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame("GameTutorial");
		frame.add(g);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		g.start();
	}

	
}
