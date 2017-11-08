package Model;

import java.awt.image.BufferedImage;

public class Sprite {
	
	private BufferedImage image;
    private int type;

    // tile types
    public static final int NORMAL = 1;
    public static final int FADING = 2;
    public static final int SPIKES = 3;
    public static final int PLAYER = 4;
    public static final int ENEMY  = 5;
    public static final int BULLET = 6;

    public Sprite(BufferedImage image, int type){
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }
    public int getType(){
        return type;
    }

}
