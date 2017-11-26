package Model;

import View.GamePanel;
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

	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = (GamePanel.HEIGHT) / tileSize;
		numColsToDraw = (GamePanel.WIDTH) / tileSize;
	}

	public void loadTiles(String s) {
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Sprite[3][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Sprite(subimage, Sprite.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Sprite(subimage, Sprite.FADING);
				subimage = tileset.getSubimage(col * tileSize, tileSize * 2, tileSize, tileSize);
				tiles[2][col] = new Sprite(subimage, Sprite.SPIKES);
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
			numCols = 40;
			numRows = 30;
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			// reads every line tokenizing each element seperated by a
			// space character
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.trim().split(" ");
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

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g) {

		for (int row = 0; row < numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = 0; col < numColsToDraw; col++) {

				if (col >= numCols)
					break;

				if (map[row][col] > 11) // int>11 --> space
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
			}
		}
	}

	public int getType(int row, int col) {
		// TODO Auto-generated method stub
		return map[row][col] / 4;
	}
}