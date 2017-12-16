package framework;

import java.awt.image.BufferedImage;

import view.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps, bl, ws, js, fs, pu;
	
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage bullet_sheet = null;
	private BufferedImage walker_sheet = null;
	private BufferedImage jumper_sheet = null;
	private BufferedImage floater_sheet = null;
	private BufferedImage powerup_sheet = null;
	
	
	
	
	public BufferedImage[] block = new BufferedImage[12];
	public BufferedImage[] player = new BufferedImage[6];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] walker = new BufferedImage[3];
	public BufferedImage[] jumper = new BufferedImage[3];
	public BufferedImage[] floater = new BufferedImage[3];
	public BufferedImage[] powerup = new BufferedImage[3];
	public BufferedImage gate = null;
	
	
	
	public Texture() {
		
		BufferedImageLoader loader = new  BufferedImageLoader();
		try {
			block_sheet   = loader.loadImage("/Tileset.png");
			player_sheet  = loader.loadImage("/Player_Blue_Weapon.png");
			bullet_sheet  = loader.loadImage("/Bullet_Sheet.png");
			walker_sheet  = loader.loadImage("/Walker_Sheet.png");
			jumper_sheet  = loader.loadImage("/Jumper_Sheet.png");
			floater_sheet = loader.loadImage("/Floater_Sheet.png");
			powerup_sheet = loader.loadImage("/Walker_Sheet.png");
			gate = loader.loadImage("/Gate.png");
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		bl = new SpriteSheet(bullet_sheet);
		ws = new SpriteSheet(walker_sheet);
		js = new SpriteSheet(jumper_sheet);
		fs = new SpriteSheet(floater_sheet);
		pu = new SpriteSheet(powerup_sheet);
		getTextures();
	}
	
	private void getTextures() {
		// Blocks
		block[0] = bs.grabImage(0, 0, 32, 32);  // black 	block
		block[1] = bs.grabImage(1, 0, 32, 32);  // red 		block
		block[2] = bs.grabImage(2, 0, 32, 32);  // blue 	block
		block[3] = bs.grabImage(3, 0, 32, 32);  // green 	block
		block[4] = bs.grabImage(0, 1, 32, 32);  // black 	fading block
		block[5] = bs.grabImage(1, 1, 32, 32);  // red 		fading block
		block[6] = bs.grabImage(2, 1, 32, 32);  // blue 	fading block
		block[7] = bs.grabImage(3, 1, 32, 32);  // green 	fading block
		block[8] = bs.grabImage(0, 2, 32, 32);  // black 	spike
		block[9] = bs.grabImage(1, 2, 32, 32);  // red 		spike
		block[10] = bs.grabImage(2, 2, 32, 32); // blue 	spike
		block[11] = bs.grabImage(3, 2, 32, 32); // green	spike
		
		// Player
		player[0] = ps.grabImage(1, 0, 64, 64); // idle frame player blue right
		player[1] = ps.grabImage(0, 0, 64, 64); // idle frame player blue left
		player[2] = ps.grabImage(1, 1, 64, 64); // idle frame player red right
		player[3] = ps.grabImage(0, 1, 64, 64); // idle frame player red left
		player[4] = ps.grabImage(1, 2, 64, 64); // idle frame player green right
		player[5] = ps.grabImage(0, 2, 64, 64); // idle frame player green left

		// Bullet
		bullet[0] = bl.grabImage(0, 0, 16, 16);	// Blue bullet
		bullet[1] = bl.grabImage(0, 1, 16, 16);	// Red bullet
		bullet[2] = bl.grabImage(0, 2, 16, 16);	// Green bullet
		
		// Enemies
		// Walker
		walker[0] = ws.grabImage(0, 0, 64, 64);
		walker[1] = ws.grabImage(0, 1, 64, 64);
		walker[2] = ws.grabImage(0, 2, 64, 64);
		// Jumper
		jumper[0] = js.grabImage(0, 0, 64, 64); // Blue Walker
		jumper[1] = js.grabImage(0, 1, 64, 64); // Red Walker
		jumper[2] = js.grabImage(0, 2, 64, 64);	// Green Walker
		// Floater
		floater[0] = fs.grabImage(0, 0, 64, 64); 	// Blue Floater
		floater[1] = fs.grabImage(0, 1, 64, 64); 	// Red Floater
		floater[2] = fs.grabImage(0, 2, 64, 64);	// Green Floater
		
		//PowerUps
		powerup[0] = pu.grabImage(0, 0, 64, 64);//rateOfFire
		powerup[1] = pu.grabImage(0, 1, 64, 64);
		powerup[2] = pu.grabImage(0, 2, 64, 64);

	}
	
	
}
