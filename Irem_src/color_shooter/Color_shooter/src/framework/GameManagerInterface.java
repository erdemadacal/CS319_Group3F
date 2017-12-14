package framework;

import java.io.IOException;

public interface GameManagerInterface {

	public void changeView(int i) ;
	public void updateLevelView(int i) throws IOException;
	public void stopPlayer(int i);
}
