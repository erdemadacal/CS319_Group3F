package Model;

import View.MainMenuPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

	// pos
	private double x; // x position
	private double y; // y position

	// bounds
	private int xmin; // minimum value for x pos
	private int ymin; // minimum value for y pos
	private int xmax; // maximum value for x pos
	private int ymax; // maximum value for y pos

	private double tween; // used for smooth camera scrolling

	// map
	private int[][] map; // 2D int array to map tile types
	private int tileSize; // size of each tile in pixels
	private int numCols; // number of columns in the map
	private int numRows; // number of rows in the map
	private int width; // width of the map in pixels
	private int height; // height of the map in pixels

	// tileset
	private BufferedImage tileset; // image containing all tiles
	private int numTilesAcross;
	private Sprite[][] tiles; // to store all tiles imported

	// drawing
	private int rowOffset; // which row to start drawing
	private int colOffset; // which col to start drawing
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = MainMenuPanel.HEIGHT / tileSize + 2;
		numColsToDraw = MainMenuPanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	public void loadTiles(String s) {
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Sprite[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Sprite(subimage, Sprite.NORMAL);
				subimage = tileset.getSubimage(col * tileSize,  tileSize, tileSize, tileSize);
				tiles[0][col] = new Sprite(subimage, Sprite.FADING);
				subimage = tileset.getSubimage(col * tileSize, tileSize * 2, tileSize, tileSize);
				tiles[0][col] = new Sprite(subimage, Sprite.SPIKES);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String s) {
		// first line of text file show the number of columns
		// second line of text file show the number of rows
		// rest is the map
		try {
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = MainMenuPanel.WIDTH - width;
			xmax = 0;
			ymin = MainMenuPanel.HEIGHT - height;
			ymax = 0;

			String delims = "\\s+"; // space
			// reads every line tokenizing each element seperated by a
			// space character
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTileSize() {
		return tileSize;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setTween(double d) {
		tween = d;
	}

	public void setPosition(double x, double y) {
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.x / tileSize;
	}

	private void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (x > xmax)
			x = xmax;
		if (y < ymin)
			x = ymin;
		if (y > ymax)
			x = ymax;
	}

	public void draw(Graphics2D g) {

	}
}