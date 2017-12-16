package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	// panel

	private static final long serialVersionUID = -4220837468943517290L;
	public static int WIDTH = 1024, HEIGHT = 768;
	private Font font;
	private JButton returnButton;

	// constructor
	public HelpPanel() {
		super();
		font = new Font("Verdana", Font.BOLD, 40);
		returnButton = new JButton("Back");
		returnButton.setBackground(Color.orange);
		returnButton.setFont(font);
		returnButton.setVisible(false);
		add(returnButton);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		returnButton.setVisible(true);
		returnButton.setLocation(315, 700);
		returnButton.setSize(400, 50);

		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/back.png"));
			g.drawImage(image, 0, 0, null);
			// draw title
			g.setColor(Color.ORANGE);
			Font titleFont = new Font("Verdana", Font.BOLD, 45);
			g.setFont(titleFont);
			g.drawString("View Help", 400, 100);
			g.drawRect(20, 150, 300, 450);
			g.drawRect(350, 150, 300, 450);
			g.drawRect(690, 150, 315, 450);
			Font newFont = new Font("Verdana", Font.BOLD, 30);
			g.setFont(newFont);
			g.drawString("Jump Mechanics", 35, 195);
			g.drawString("Power-Ups", 705, 195);
			g.drawString("Color Mechanics", 365, 195);
			newFont = new Font("Verdana", Font.BOLD, 20);
			g.setFont(newFont);
			g.setColor(Color.GREEN);
			g.drawString("In normal mode you can", 35, 250);
			g.drawString("jump by pressing the up ", 35, 280);
			g.drawString("arrow key on keyboard. ", 35, 310);
			g.drawString("In hard mode you can not", 35, 340);
			g.drawString("jump manually. However ", 35, 370);
			g.drawString("the system will make", 35, 400);
			g.drawString("the jump automatically", 35, 430);
			g.drawString("when it runs.", 35, 460);

			g.drawString("Regarding enemies: Your ", 365, 250);
			g.drawString("bullets will only damage", 365, 280);
			g.drawString("enemies of the same", 365, 310);
			g.drawString("color as the bullet fired.", 365, 340);
			g.drawString("Regarding tiles: You will ", 365, 370);
			g.drawString("be able to pass the tiles ", 365, 400);
			g.drawString("that are the samecolor as ", 365, 430);
			g.drawString("you. You will be able to", 365, 460);
			g.drawString("stand on black tiles ", 365, 490);
			g.drawString("regardless of your color.", 365, 520);

			BufferedImage speed = ImageIO.read(getClass().getResourceAsStream("/speed.jpeg"));
			g.drawImage(speed, 710, 450, null);
			BufferedImage shield = ImageIO.read(getClass().getResourceAsStream("/shield.jpeg"));
			g.drawImage(shield, 810, 450, null);
			BufferedImage health = ImageIO.read(getClass().getResourceAsStream("/health.jpeg"));
			g.drawImage(health, 910, 450, null);
			g.drawString("-Increased move speed\n", 695, 250);
			g.drawString("-Temporary Invulnerability\n", 695, 300);
			g.drawString("-Fill health\n", 695, 350);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}