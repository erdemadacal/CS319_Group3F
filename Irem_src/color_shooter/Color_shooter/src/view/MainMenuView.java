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
        
		// create cards
	    gamePanel = new GamePanel(this);
		gamePanel.init();
		//Handler handler = gamePanel.getHandler();
		//Camera cam = gamePanel.getCamera();
	    
		mainMenuP = new MainMenuPanel();//cam);
		creditsP = new CreditsPanel();
		changeSettingsP = new ChangeSettingsPanel();
		pauseMenuP = new PauseMenuPanel();
		helpP = new HelpPanel();
		difficultySelectionPanel = new DifficultySelectionPanel();
		
		gm = gamePanel.getGameManager();
		
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
		
		//KeyInput keyListener = new KeyInput(handler);
		/*gameFrame.addKeyListener(keyListener);
		cards.addKeyListener(keyListener);
		gamePanel.addKeyListener(keyListener);
		mainMenuP.addKeyListener(keyListener);
		creditsP.addKeyListener(keyListener);
        changeSettingsP.addKeyListener(keyListener);
		helpP.addKeyListener(keyListener);
		pauseMenuP.addKeyListener(keyListener);
		*/
		
		
		gameFrame.pack();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(cards);
		gameFrame.setResizable(false);
		gameFrame.setFocusable(true);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		
		showGamePanel = false;
	}
	
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
		//gamePanel.init();
		if(level != 0)
		   gamePanel.setLevel(level);
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
    	   else if(c == KeyEvent.VK_SPACE)
    	   {
    		   //System.out.println("SHOOTING");
    		   gm.updateLevelView(3);
    	   }
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
			
			// Restart level
			if(c == KeyEvent.VK_R) {
				gm.restartLevel();
			}
			// Next Level. For testing purposes only
			if(c == KeyEvent.VK_PAGE_UP) {
				gm.switchLevel();
			}
			
       	}
		if(c == KeyEvent.VK_ESCAPE)
		{
			//System.out.println("BYE!");
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
	    		   //System.out.println("LEFT");
	    		   gm.stopPlayer(0);
	    	   }
	    	   else if(c == KeyEvent.VK_RIGHT)
	    	   {
	    		   //System.out.println("RIGHT");
	    		   gm.stopPlayer(1);
	    	   }
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
	public void run() 
	{
		//gamePanel.init();
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
	
	public static void main(String args[]) {
		//new Window(800, 600, "GameTutorial", new Game());
		//System.out.println("MAIN");
		//GamePanel g = new GamePanel();
		
		//g.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//g.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		//g.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		/*JFrame frame = new JFrame("GameTutorial");
		//frame.add(g);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		*/
		
		//g.start();
		MainMenuView view = new MainMenuView();
		view.start();
	}
	
	public void updateGamePanel()
	{
	    gamePanel.repaint();   
	}
	

	public class MenuListener implements ActionListener, ChangeListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			if (mainMenuP.getNewGameButton() == event.getSource()) {
				gm.changeView(6);
			} 
			else if (difficultySelectionPanel.getEasyButton() == event.getSource())
			{
				gm.changeView(0);
			}
			else if (difficultySelectionPanel.getHardButton() == event.getSource())
			{
				gm.changeView(0);
			}
			else if (mainMenuP.getContinueGameButton() == event.getSource()) {
				gm.changeView(7); // gm levelView
			}
			else if (mainMenuP.getChangeSettingsButton() == event.getSource()) {
				gm.changeView(1);
			}
			else if (mainMenuP.getViewHelpButton() == event.getSource()) {
				gm.changeView(2);
			} 
			else if (mainMenuP.getCreditsButton() == event.getSource()) {
				gm.changeView(3);	
			} 
			else if (mainMenuP.getExitButton() == event.getSource()) {
			     gm.updateLevelView(7);
			}

			else if (creditsP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
			} 
			else if (changeSettingsP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
			}
			else if (helpP.getReturnButton() == event.getSource()) {
				gm.changeView(4);
			}
			else if(pauseMenuP.getResumeButton() == event.getSource()) {
				gm.changeView(0);
			}
			else if(pauseMenuP.getReturnButton()== event.getSource()) {
				gm.changeView(4);
			}
			else if(pauseMenuP.getViewHelpButton() == event.getSource()) {
				gm.changeView(2);
			}
			
		}
        @Override
		public void stateChanged(ChangeEvent e)
		{
        	int sfxVolume = 5; //defaultVolume
        	int musicVolume = 5; //defaultVolume
        	JSlider source = (JSlider)e.getSource();
        	
            if (!source.getValueIsAdjusting()) {
            	if(changeSettingsP.getSFX() == source || pauseMenuP.getSFX() == source  )
            	{
            		sfxVolume = (int)source.getValue();
            		System.out.println("SFX VOLUME " + sfxVolume);
            		changeSettingsP.getSFX().setValue(sfxVolume);
            		pauseMenuP.getSFX().setValue(sfxVolume);
            	}
            		
                else if(changeSettingsP.getMusics() == source || pauseMenuP.getMusics() == source)
                {
                	musicVolume = (int)source.getValue();
                	System.out.println("MUSIC VOLUME " + musicVolume);
                	changeSettingsP.getMusics().setValue(musicVolume);
            		pauseMenuP.getMusics().setValue(musicVolume);
                }
            }    
		}
	}
}
