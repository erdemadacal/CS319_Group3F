package framework;

import java.io.IOException;
import javax.sound.sampled.*;

public class SoundManager {
	public static final SoundManager BACK = new SoundManager("/SFX/back.wav");
	public static final SoundManager JUMP = new SoundManager("/SFX/playerjump.wav");
	public static final SoundManager ATTACK = new SoundManager("/SFX/playerattack.wav");
	public static final SoundManager DEAD = new SoundManager("/SFX/playerhit.wav");
	public static final SoundManager GATE = new SoundManager("/SFX/teleport.wav");
	public static final SoundManager SELECT = new SoundManager("/SFX/menuoption.wav");
	public static final SoundManager SFX[] = {JUMP, ATTACK, DEAD, GATE, SELECT};

	private FloatControl floatControl;
	private Clip clip;

	public SoundManager(String path) {
		set(Audio.load(path));
	}

	public SoundManager(Clip clip) {
		set(clip);
	}

	private void set(Clip clip) {
		floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		this.clip = clip;
	}

	public void play() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (clip != null) {
					if (!clip.isRunning()) {
						clip.setFramePosition(0);
						clip.start();
					}
				}
			}
		}).start();
	}

	public void loop() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (clip != null) {
					if (!clip.isRunning()) {
						clip.setFramePosition(0);
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
				}
			}
		}).start();
	}

	public void stop() {
		clip.stop();
	}

	public void setVolume(float value) {
		if (value < floatControl.getMaximum() && value > floatControl.getMinimum())
			floatControl.setValue(value);
		else if (value > floatControl.getMaximum()) {
			floatControl.setValue(floatControl.getMaximum());
		} else if (value < floatControl.getMinimum()) {
			floatControl.setValue(floatControl.getMinimum());
		}
		clip.getControl(FloatControl.Type.MASTER_GAIN);
		floatControl.setValue(value);
	}

	public void setMinimum() {
		floatControl.setValue(floatControl.getMinimum());
	}

	public void setMaximum() {
		floatControl.setValue(floatControl.getMaximum());
	}

	public String toString() {
		return Float.toString(floatControl.getValue());

	}

	public static class Audio {
		public static Clip load(String path) {
			try {
				AudioInputStream input = AudioSystem.getAudioInputStream(Audio.class.getResource(path));
				AudioFormat format = input.getFormat();
				AudioFormat decode = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16,
						format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);

				AudioInputStream decode_input = AudioSystem.getAudioInputStream(decode, input);

				Clip clip = AudioSystem.getClip();
				clip.open(decode_input);
				return clip;
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}