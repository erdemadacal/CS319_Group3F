package framework;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
	public static final AudioClip JUMP = Applet.newAudioClip(Sound.class.getResource("/SFX/playerjump.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("/SFX/back.wav"));
	public static final AudioClip ATTACK = Applet.newAudioClip(Sound.class.getResource("/SFX/playerattack.wav"));
	public static final AudioClip DEAD = Applet.newAudioClip(Sound.class.getResource("/SFX/playerhit.wav"));
	public static final AudioClip GATE = Applet.newAudioClip(Sound.class.getResource("/SFX/teleport.wav"));
	public static final AudioClip SELECT = Applet.newAudioClip(Sound.class.getResource("/SFX/menuselect.wav"));
	/*public Sound() {
	try {
        // Open an audio input stream.
        URL url = this.getClass().getResource("/SFX/back.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        Clip clip = AudioSystem.getClip();
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
        clip.loop(0);
        final FloatControl control = (FloatControl) 
                clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-30.0f);
        System.out.println(url + "asfasfasdas");

     } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
     } catch (IOException e) {
        e.printStackTrace();
     } catch (LineUnavailableException e) {
        e.printStackTrace();
     }
	
}*/
	}