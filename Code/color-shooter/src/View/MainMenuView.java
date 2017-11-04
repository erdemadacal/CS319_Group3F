package View;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainMenuView {

	JFrame gameFrame;
	// panels
	private MainMenuPanel mainMenuP;
	private CreditsPanel creditsP;
	private ChangeSettingsPanel changeSettingsP;
	private PauseMenuPanel pauseMenuP;
	private HelpPanel helpP;
	private GamePanel gamePanel;
//	private GameManager gm;

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

		cardLayout = new CardLayout();

		cards.setLayout(cardLayout);
		cards.add(mainMenuP, "1");
		cards.add(creditsP, "2");
		cards.add(changeSettingsP, "3");
		cards.add(pauseMenuP, "4");
		cards.add(helpP, "5");
		cards.add(gamePanel, "6");
		cardLayout.show(cards, "1");

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(cards);// new ChangeSettingsPanel());
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainMenuView();
			}
		});

		// MainMenuPanel panel = new MainMenuPanel(listener);

	}

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

	public void displayPauseMenu() {

	}
	

	public class MenuListener implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			if (mainMenuP.getNewGameButton() == event.getSource()) {
				displayLevelPanel();
			} else if (mainMenuP.getContinueGameButton() == event.getSource()) {
				// gsm levelView
			} else if (mainMenuP.getChangeSettingsButton() == event.getSource()) {
				displayChangeSettingsPanel();

			} else if (mainMenuP.getViewHelpButton() == event.getSource()) {
				displayHelpPanel();
			} else if (mainMenuP.getCreditsButton() == event.getSource()) {
				displayCreditsPanel();
			} else if (mainMenuP.getExitButton() == event.getSource()) {
				System.exit(0);
			}

			else if (creditsP.getReturnButton() == event.getSource()) {
				displayMenuPanel();
			} else if (changeSettingsP.getReturnButton() == event.getSource()) {
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