package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import framework.GameManager;


public class MainMenuView implements  Runnable, KeyListener{
	JFrame gameFrame;
	// panels
	private MainMenuPanel mainMenuP;
	private CreditsPanel creditsP;
	private ChangeSettingsPanel changeSettingsP;
	private PauseMenuPanel pauseMenuP;
	private HelpPanel helpP;
	private GamePanel gamePanel;
    private DifficultySelectionPanel difficultySelectionPanel;
	// listener
	private MenuListener listener;
	// for card layout
	private JPanel cards;

	// Card Layout
	private CardLayout cardLayout;
	
	//controller
	private GameManager gm;
    //showGamePanel
	//keeps track of gamepanel whether it is displayed or not 
	//to allow keyboard activities
	private boolean showGamePanel;
	
	private boolean running = false;
	private Thread thread;
	
	public MainMenuView() {
		
	    gameFrame = new JFrame("Color Shooter: The Spectrum Adventurer");
        
	    gamePanel = new GamePanel(this);
		gamePanel.init();
	    
		mainMenuP = new MainMenuPanel();
		creditsP = new CreditsPanel();
		changeSettingsP = new ChangeSettingsPanel();
		pauseMenuP = new PauseMenuPanel();
		helpP = new HelpPanel();
		difficultySelectionPanel = new DifficultySelectionPanel();
		
		gm = gamePanel.getGameManager();
		
		// create cards
		cards = new JPanel();
		cardLayout = new CardLayout();

		cards.setLayout(cardLayout);
		
        //set Listeners to buttons
		listener = new MenuListener();
		mainMenuP.getNewGameButton().addActionListener(listener);
		mainMenuP.getContinueGameButton().addActionListener(listener);
		mainMenuP.getChangeSettingsButton().addActionListener(listener);
		mainMenuP.getViewHelpButton().addActionListener(listener);
		mainMenuP.getCreditsButton().addActionListener(listener);
		mainMenuP.getExitButton().addActionListener(listener);

		gamePanel.getReturnButton().addActionListener(listener);
		creditsP.getReturnButton().addActionListener(listener);
		changeSettingsP.getReturnButton().addActionListener(listener);
		changeSettingsP.getSFX().addChangeListener(listener);
		changeSettingsP.getMusics().addChangeListener(listener);
		helpP.getReturnButton().addActionListener(listener);
        pauseMenuP.getReturnButton().addActionListener(listener);
        pauseMenuP.getResumeButton().addActionListener(listener);
        pauseMenuP.getViewHelpButton().addActionListener(listener);
        pauseMenuP.getSFX().addChangeListener(listener);
        pauseMenuP.getMusics().addChangeListener(listener);
        difficultySelectionPanel.getEasyButton().addActionListener(listener);
        difficultySelectionPanel.getHardButton().addActionListener(listener);
        
		cards.add(mainMenuP, "1");
		cards.add(creditsP, "2");
		cards.add(changeSettingsP, "3");
		cards.add(pauseMenuP, "4");
		cards.add(helpP, "5");
		cards.add(gamePanel, "6");
		cards.add(difficultySelectionPanel, "7");
		cardLayout.show(cards, "1");

		gameFrame.add(cards);
		gameFrame.addKeyListener(this);
		cards.addKeyListener(this);
		gamePanel.addKeyListener(this);
		mainMenuP.addKeyListener(this);
		creditsP.addKeyListener(this);
        changeSettingsP.addKeyListener(this);
		helpP.addKeyListener(this);
		pauseMenuP.addKeyListener(this);
		
		gameFrame.pack();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(cards);
		gameFrame.setResizable(false);
		gameFrame.setFocusable(true);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		
		showGamePanel = false; //initally it is set to false as it is not shown
	}
	
	public void showGamePanel(boolean showGamePanel)
	{
		this.showGamePanel = showGamePanel;
	}
	
	// display methods are called by the controller, game manager
	// as it decides on what to do next according to the user inputs
	public void displayMenuPanel() {
		cardLayout.show(cards, "1");
		showGamePanel = false;
	}

	public void displayHelpPanel() {
		cardLayout.show(cards, "5");
		showGamePanel = false;
	}

	public void displayCreditsPanel() {
		cardLayout.show(cards, "2");
		showGamePanel = false;
	}
	public void displayLevelPanel(int level) {
		if(level != 0)
		   gm.setLevel(level);
		cardLayout.show(cards, "6");
		showGamePanel = true;
	}

	public void displayChangeSettingsPanel() {
		cardLayout.show(cards, "3");
		showGamePanel = false;
	}

	public void displayPauseMenu() {
		cardLayout.show(cards, "4");
		showGamePanel = false;
	}
	public void displayDifficultySelectionPanel() {
		cardLayout.show(cards, "7");
		showGamePanel = false;
	}
	
	//warn the controller when there is user input
	public void keyPressed(KeyEvent e) {

		int c = e.getKeyCode();
       if(showGamePanel == true)
       {
    	   if(c == KeyEvent.VK_LEFT)
    	   {
    		  // System.out.println("LEFT");
    		   gm.updateLevelView(0);
    	   }
    	   else if(c == KeyEvent.VK_RIGHT)
    	   {
    		   //System.out.println("RIGHT");
    		   gm.updateLevelView(1);
    	   }
    	   else if(c == KeyEvent.VK_UP)
    	   {
    		   //System.out.println("UP");
				gm.updateLevelView(2);
    	   }
    	  /* else if(c == KeyEvent.VK_SPACE)
    	   {
    		   //System.out.println("SHOOTING");
    		   gm.updateLevelView(3);
    	   }*/
    	   else if(c == KeyEvent.VK_P)
    	   {
    		   //System.out.println("PauseGame");
    		   gm.changeView(5);
    	   }
    	    // Change player's color based on use input
			// Z for Blue, X for Red, C for Green
			if(c == KeyEvent.VK_Z) {
				gm.updateLevelView(4);
			}
			else if(c == KeyEvent.VK_X) {
				gm.updateLevelView(5);
			}
			else if(c == KeyEvent.VK_C) {
				gm.updateLevelView(6);
			}
			
			// Restart level//for test purpose
			/*if(c == KeyEvent.VK_R) {
				gm.restartLevel();
			}
			// Next Level. For testing purposes only
			if(c == KeyEvent.VK_PAGE_UP) {
				gm.switchLevel();
			}
			*/
			
       	}
       else
		if(c == KeyEvent.VK_ESCAPE)
		{
			gm.updateLevelView(7);
		}
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		
		int c = e.getKeyCode();
	       if(showGamePanel == true)
	       {
	    	   if(c == KeyEvent.VK_LEFT)
	    	   {
	    		   gm.stopPlayer(0);
	    	   }
	    	   else if(c == KeyEvent.VK_RIGHT)
	    	   {
	    		   gm.stopPlayer(1);
	    	   }
	    	   else if(c == KeyEvent.VK_SPACE)
	    	   {
	    		   //System.out.println("SHOOTING");
	    		   gm.updateLevelView(3);
	    	   }
	    	   
	    	   //for test purposes
	    	  /* else if(c == KeyEvent.VK_UP)
	    	   {
	    		  // System.out.println("UP");
					gm.stopPlayer(2);
	    	   }
	    	   else if(c == KeyEvent.VK_SPACE)
	    	   {
	    		   //System.out.println("SHOOTING");
	    		   gm.stopPlayer(3);
	    	   }
	    	   */
	       	}
		
	}
	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	private synchronized void stop()
	{
		if(!running)
	      return;

		
		running = false;
		try {
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
	// game loop
	public void run() 
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				if(gamePanel != null)
					gamePanel.tick();
				updates++;
				delta--;
			}
			gamePanel.repaint();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	// start the game
	public static void main(String args[]) {

		MainMenuView view = new MainMenuView();
		view.start();
	}
	
	public void updateGamePanel()
	{
	    gamePanel.repaint();   
	}
	
    // private class which implements the listeners
	// in each action, game manager is called, the input is transmitted
	public class MenuListener implements ActionListener, ChangeListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			if (mainMenuP.getNewGameButton() == event.getSource()) {
				gamePanel.setGameOver(false);
				gm.changeView(6);
				gm.startSelectionEffect();
			} 
			else if (difficultySelectionPanel.getEasyButton() == event.getSource())
			{
				gm.setIsEasy(true);
				gm.changeView(0);
				gm.startSelectionEffect();
				gm.startBackgroundLoop();
				
			}
			else if (difficultySelectionPanel.getHardButton() == event.getSource())
			{
				gm.setIsEasy(false);
				gm.changeView(0);
				gm.startSelectionEffect();
				gm.startBackgroundLoop();
			}
			else if (mainMenuP.getContinueGameButton() == event.getSource()) {
				gm.changeView(7);
				gm.startSelectionEffect();
				gm.startBackgroundLoop();
			}
			else if (mainMenuP.getChangeSettingsButton() == event.getSource()) {
				gm.changeView(1);
				gm.startSelectionEffect();
			}
			else if (mainMenuP.getViewHelpButton() == event.getSource()) {
				gm.changeView(2);
				gm.startSelectionEffect();
			} 
			else if (mainMenuP.getCreditsButton() == event.getSource()) {
				gm.changeView(3);	
				gm.startSelectionEffect();
			} 
			else if (mainMenuP.getExitButton() == event.getSource()) {
			     gm.updateLevelView(7);
			     gm.startSelectionEffect();
			}

			else if (creditsP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
				gm.startSelectionEffect();
			} 
			else if (changeSettingsP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
				gm.startSelectionEffect();
			}
			else if (helpP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
				gm.startSelectionEffect();
			}
			else if(pauseMenuP.getResumeButton() == event.getSource()) {
				gm.changeView(8);
				gm.startSelectionEffect();
			}
			else if(pauseMenuP.getReturnButton()== event.getSource()) {
				gm.changeView(4);
				gm.startSelectionEffect();
				gm.stopBackgroundLoop();
			}
			else if(pauseMenuP.getViewHelpButton() == event.getSource()) {
				gm.changeView(2);
				gm.startSelectionEffect();
			}
			else if(gamePanel.getReturnButton() == event.getSource()) {
			   gm.changeView(4);
			   gm.startSelectionEffect();
			   gm.stopBackgroundLoop();
			}
			
		}
		   @Override
			public void stateChanged(ChangeEvent e)
			{
				int sfxVolume;
				int musicVolume;
				JSlider source = (JSlider)e.getSource();

				if (!source.getValueIsAdjusting()) {
					if(changeSettingsP.getSFX() == source || pauseMenuP.getSFX() == source  )
					{
						sfxVolume = source.getValue();
						changeSettingsP.getSFX().setValue(sfxVolume);
						pauseMenuP.getSFX().setValue(sfxVolume);

						for (int i = 0; i < gm.getSFXLength(); i++) {
							if (sfxVolume > 5) {
								if (sfxVolume == 10)
									gm.getSFX(i).setMaximum();
								else
									gm.getSFX(i).setVolume((sfxVolume - 5));
							} else {
								if (sfxVolume == 0)
									gm.getSFX(i).setMinimum();
								else
									gm.getSFX(i).setVolume(-10f * (5 - sfxVolume));
							}
						}
					}

					else if(changeSettingsP.getMusics() == source || pauseMenuP.getMusics() == source)
					{
						musicVolume = (int)source.getValue();
						changeSettingsP.getMusics().setValue(musicVolume);
						pauseMenuP.getMusics().setValue(musicVolume);
						if(musicVolume>5)
						{
							if(musicVolume == 10) gm.setMaximumVolume();
							else gm.setVolume((float)(musicVolume-5));
						}
						else {
							if(musicVolume == 0) gm.setMinimumVolume();
							else gm.setVolume(-10f*(5-musicVolume));
						}
					}
				}
			}
		}
	}