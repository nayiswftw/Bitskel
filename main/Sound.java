package main;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/Queen.wav");
        soundURL[1] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[2] = getClass().getResource("/res/sound/chest.wav");
        soundURL[3] = getClass().getResource("/res/sound/coin1.wav");
        soundURL[4] = getClass().getResource("/res/sound/coin2.wav");
        soundURL[5] = getClass().getResource("/res/sound/coin3.wav");
    }
    public void setFile(int i) {
       try {
           AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
              clip = AudioSystem.getClip();
              clip.open(ais);
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Sound file not found");
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}