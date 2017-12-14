package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DifficultySelectionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1024, HEIGHT = 768;
	JButton easy;
	JButton hard;
	public DifficultySelectionPanel()
	{
		Font font = new Font("Verdana",Font.BOLD,40);
		easy = new JButton("Easy");
		easy.setBackground(Color.BLUE);
		easy.setFont(font);
		
		hard = new JButton("Hard");
		hard.setBackground(Color.RED);
		hard.setFont(font);
		
		easy.setVisible(false);
		hard.setVisible(false);
		
		add(easy);
		add(hard);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
	}
	
	public JButton getEasyButton()
	{
		return easy;
	}
	
	public JButton getHardButton()
	{
		return hard;
	}
	
	@Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);

    	easy.setLocation(320, 300);
    	easy.setSize(200, 50);
    	hard.setLocation(580, 300);
    	hard.setSize(200, 50);
    	easy.setVisible(true);
    	hard.setVisible(true);
	     
    	 try
    	 {
    		 BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
    		 g.drawImage(image, 0, 0, null);
    		// draw title
    		 g.setColor(Color.ORANGE);
    	     Font titleFont = new Font("Verdana",Font.BOLD,45);
    	     g.setFont(titleFont);
    	     g.drawString("Select Easy/Hard mode",250,150);
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
       
    }
}
