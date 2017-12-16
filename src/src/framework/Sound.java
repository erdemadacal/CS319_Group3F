package framework;

import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
	public static final Sound BACK = new Sound("/SFX/back.wav");
	public static final Sound JUMP = new Sound("/SFX/playerjump.wav");
	public static final Sound ATTACK = new Sound("/SFX/playerattack.wav");
	public static final Sound DEAD = new Sound("/SFX/playerhit.wav");
	public static final Sound GATE = new Sound("/SFX/teleport.wav");
	public static final Sound SELECT = new Sound("/SFX/menuselect.wav");
	public static final Sound OPTION = new Sound("/SFX/menuoption.wav");
	public static final Sound SFX[] = {JUMP, ATTACK, DEAD, GATE, SELECT, OPTION};

	private FloatControl floatControl;
	private Clip clip;

	public Sound(String path) {
		set(Audio.load(path));
	}

	public Sound(Clip clip) {
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
