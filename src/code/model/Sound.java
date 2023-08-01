package src.code.model;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class, loads and plays music files and sound effects
 * 
 * @author Roos Spierings (s)
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/src/res/sound/thunder.wav");
        soundURL[1] = getClass().getResource("/src/res/sound/footstep.wav");
        soundURL[2] = getClass().getResource("/src/res/sound/metal_hit.wav");
        soundURL[3] = getClass().getResource("/src/res/sound/dig.wav");
    }

    public void setFile(int i) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundURL[i]));
        } catch (Exception e) {
            System.out.println("Failed to load sound: " + soundURL[i]);
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
