package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import view.Game;
import view.Handler;

public class Block extends GameObject {

	Texture tex = Game.getInstance();
	
	private int type; 
	private double alpha = 255;
	private BufferedImage image;
	private boolean fading = false;
	private Handler handler;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public void setFading(boolean fading) {
		this.fading = fading;
	}
	

	public Block(float x, float y, int type, ObjectId id, ColorId color, Handler handler) {
		super(x, y, id, color);
		this.handler = handler;
		this.type = type;
		this.image = new BufferedImage(tex.block[type].getWidth(),tex.block[type].getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		try {
			g.drawImage(tex.block[type], 0, 0, null);
		}finally {
			g.dispose();
		}
	}

	public void tick(LinkedList<GameObject> object) {
		/*if(type >= 4 && type < 8 && x == 384) {
			System.out.println("x: " + x + " ___y: " + y + " ___fading: " + fading + " ___alpha: " + alpha);
		}*/
		if( fading && (alpha > 50)) {
			decreaseAlpha(this.image, 1);
			alpha--;
		}
		if(alpha <= 50) {
			handler.removeObject(this);
		}

	}
		
	

	public void render(Graphics g) 
	{
			g.drawImage(image, (int)x, (int)y, null );
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public static void decreaseAlpha(BufferedImage img, int amount)
	{

	    for(int x=0; x<img.getWidth(); x++)
	        for(int y=0; y<img.getHeight(); y++)
	        {
	           Color c = new Color(img.getRGB(x, y), true);
	           int alpha = c.getAlpha() - amount;
	           if (alpha < 0) {
	        	   alpha = 0;
	           }
	           alpha = alpha << 24;
	           int pixel = img.getRGB(x, y);
	           pixel = pixel & 0x00FFFFFF;
	           pixel = pixel | alpha;
	           img.setRGB(x, y, pixel);
   
	        }
	}
  
}
	
