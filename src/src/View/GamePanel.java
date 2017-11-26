package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.GameManager;
import Model.Bullet;
import Model.Player;
import Model.TileMap;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	// panel
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	private GameManager gm;
	private Graphics gr;
	private boolean running;
	public long elapsedTime;
	private Bullet b;

	// constructor
	public GamePanel(GameManager gm){
		super();
		elapsedTime = 0;
		running = true;

		this.gm = gm; 
		// add(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		//a();
	}
	public void update(int i)
	{ 
		Player p = gm.getPlayer();
		
		if(i == 0) {
			p.setx(p.getx() - 5);
			p.setLeft(true);
			p.setRight(false);
		}
		else if(i == 1)
		{
			p.setx(p.getx() + 5);
			p.setLeft(false);
			p.setRight(true);
			
		}
		else if (i == 2)
			p.sety(p.gety() - 5);
		else if(i == 3)	
			p.sety(p.gety() + 5);
		else if(i == 4)
		{
			p.setShooting(true);	
		TileMap tm = gm.getTileMap();
			b = new Bullet(tm,true,1);
			p.getBullets().add(b);
			draw();}
		else if(i == 5)	
			System.exit(0);
		repaint();
	}
		/*public void a() {
			SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {				
						elapsedTime++;
						while(running)
						{
							update();
							//draw();
							

							try {
								Thread.sleep(1);
							} catch (Exception e){}
						}
				}
			});
		}*/
		
		private void update() {
			gm.updateAll();
		}
		public void draw() {
			gm.drawBullets(gr);	
		}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gm.drawAll(g);
	}
}
