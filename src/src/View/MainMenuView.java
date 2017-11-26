package View;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainMenuView implements KeyListener {

	JFrame gameFrame;
	// panels
	private MainMenuPanel mainMenuP;
	private CreditsPanel creditsP;
	private ChangeSettingsPanel changeSettingsP;
	private PauseMenuPanel pauseMenuP;
	private HelpPanel helpP;
	private GamePanel gamePanel;

	// listener
	private MenuListener listener;
	// for card layout
	private JPanel cards;

	// Card Layout
	private CardLayout cardLayout;

	public MainMenuView() {
		gameFrame = new JFrame("Color Shooter: The Spectrum Adventurer");

		
		// create cards
		mainMenuP = new MainMenuPanel();
		creditsP = new CreditsPanel();
		changeSettingsP = new ChangeSettingsPanel();
		pauseMenuP = new PauseMenuPanel();
		helpP = new HelpPanel();
		gamePanel = new GamePanel();//gm);

		cards = new JPanel();
		cardLayout = new CardLayout();

		cards.setLayout(cardLayout);
		

		listener = new MenuListener();
		mainMenuP.getNewGameButton().addActionListener(listener);
		mainMenuP.getContinueGameButton().addActionListener(listener);
		mainMenuP.getChangeSettingsButton().addActionListener(listener);
		mainMenuP.getViewHelpButton().addActionListener(listener);
		mainMenuP.getCreditsButton().addActionListener(listener);
		mainMenuP.getExitButton().addActionListener(listener);

		creditsP.getReturnButton().addActionListener(listener);
		changeSettingsP.getReturnButton().addActionListener(listener);
		helpP.getReturnButton().addActionListener(listener);

		
		cards.add(mainMenuP, "1");
		cards.add(creditsP, "2");
		cards.add(changeSettingsP, "3");
		cards.add(pauseMenuP, "4");
		cards.add(helpP, "5");
		cards.add(gamePanel, "6");
		cardLayout.show(cards, "1");

		gameFrame.add(cards);
		gameFrame.addKeyListener(this);
		cards.addKeyListener(this);
		gamePanel.addKeyListener(this);
		mainMenuP.addKeyListener(this);
		creditsP.addKeyListener(this);
        changeSettingsP.addKeyListener(this);
		helpP.addKeyListener(this);
		
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(cards);
		gameFrame.setResizable(false);
		gameFrame.setFocusable(true);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainMenuView();
				//m.gamePanel.a();
			}
		});
	}
	public void keyPressed(KeyEvent e)
	{
		int c = e.getKeyCode();
		//if(cards.)
		if(c == KeyEvent.VK_LEFT)
		{
			System.out.println("LEFT");
			gamePanel.update(0);
		}
		else if(c == KeyEvent.VK_RIGHT)
		{
			System.out.println("RIGHT");
			gamePanel.update(1);
		}
		else if(c == KeyEvent.VK_UP)
		{
			System.out.println("UP");
			gamePanel.update(2);
		}
		else if(c == KeyEvent.VK_DOWN)
		{
			System.out.println("DOWN");
			gamePanel.update(3);
		}
		else if(c == KeyEvent.VK_SPACE)
		{
			System.out.println("SHOOTING");
			gamePanel.update(4);
		}
		else if(c == KeyEvent.VK_ESCAPE)
		{
			System.out.println("BYE!");
			gamePanel.update(5);
		}
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void displayMenuPanel() {
		cardLayout.show(cards, "1");
	}

	public void displayHelpPanel() {
		cardLayout.show(cards, "5");
	}

	public void displayCreditsPanel() {
		cardLayout.show(cards, "2");
	}
	public void displayLevelPanel() {
		cardLayout.show(cards, "6");
	}

	public void displayChangeSettingsPanel() {
		cardLayout.show(cards, "3");
	}

	public void displayPauseMenu() {}
	

	public class MenuListener implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			if (mainMenuP.getNewGameButton() == event.getSource()) {
				displayLevelPanel();
			} 
			else if (mainMenuP.getContinueGameButton() == event.getSource()) {
				// gsm levelView
			}
			else if (mainMenuP.getChangeSettingsButton() == event.getSource()) {
				displayChangeSettingsPanel();
			}
			else if (mainMenuP.getViewHelpButton() == event.getSource()) {
				displayHelpPanel();
			} 
			else if (mainMenuP.getCreditsButton() == event.getSource()) {
				displayCreditsPanel();
			} 
			else if (mainMenuP.getExitButton() == event.getSource()) {
				System.exit(0);
			}

			else if (creditsP.getReturnButton() == event.getSource()) {
				displayMenuPanel();
			} 
			else if (changeSettingsP.getReturnButton() == event.getSource()) {
				displayMenuPanel();
			}
			if (helpP.getReturnButton() == event.getSource()) {
				displayMenuPanel();
			}
			
		}

		
		@Override
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {};
		public void mouseExited(MouseEvent event) {};
		public void mouseReleased(MouseEvent event) {};
		public void mousePressed(MouseEvent event) {};
	}
}