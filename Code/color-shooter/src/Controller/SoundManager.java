package Controller;

import java.util.HashMap;

public class SoundManager {

	private HashMap<String, AudioPlayer> sfx;
	private AudioPlayer bgMusic;

	public SoundManager() {
		bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		bgMusic.play();

		sfx = new HashMap<String, AudioPlayer>();

		sfx.put("jump", new AudioPlayer("/SFX/jump.mp3")); // change wav *
	}
}





