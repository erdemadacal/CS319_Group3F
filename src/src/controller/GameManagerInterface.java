package controller;

import java.io.IOException;

public interface GameManagerInterface {

	public void changeView(int i) ;
	public void updateLevelView(int i) throws IOException;
	public void stopPlayer(int i);
	public void startSelectionEffect();
	public void startBackgroundLoop();
	public void stopBackgroundLoop();
	public void setVolume(Float f);
	public void setMinimumVolume();
	public void setMaximumVolume();
	public SoundManager getSFX(int i);
	public int getSFXLength();
}
