/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Media.AdvancedAudio;

import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Jaca
 */
public class Sound {
    private MediaPlayer mp;
    private static double masterVolume = 1;
    private boolean playing;
    private static final ObservableList<Sound> sl = FXCollections.observableArrayList();
    private double volume;

    /**
     *
     * @return the master volume of the Sound class
     */
    public static double getMasterVolume() {
        return masterVolume;
    }
    
    /**
     *Sets the master volume of the Sound class. Applies to all Sound objects.
     * @param masterVolume
     */
    public static void setMasterVolume(double masterVolume) {
        Sound.masterVolume = masterVolume;
        for (Sound s : sl) {
            s.setVolume(s.getVolume());
        }
    }
    
    /**
     *creates a Sound object using the file specified by u
     * @param u
     */
    public Sound(URL u){
        mp = new MediaPlayer(new Media(u.toString()));
        mp.setVolume(1 * masterVolume);
        sl.add(this);
    }
    
    /**
     *sets the Sound's independent volume
     * @param d
     */
    public void setVolume(double d){
        mp.setVolume(masterVolume * d);
        volume = d;
    }
    
    /**
     *returns the Sound's independent volume
     * @return the volume
     */
    public double getVolume(){
        return volume;
    }
    
    /**
     *mutes sound if true, sound can play if false.
     * @param b
     */
    public void setMute(boolean b){
        mp.setMute(b);
    }
    
    /**
     *sets the balance of the sound. -1 is leftmost, 1 is rightmost.
     * @param d
     */
    public void setBalance(double d){
        mp.setBalance(d);
    }
    
    /**
     *returns the Sound's balance
     * @return
     */
    public double getBalance(){
        return mp.getBalance();
    }
    
    /**
     *plays the sound
     */
    public void play(){
        mp.seek(Duration.ZERO);
        mp.play();
        playing = true;
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                playing = false;
            }
        });
    }
    
    /**
     *pauses the sound
     */
    public void pause(){
        mp.pause();
        playing = false;
    }
    
    /**
     *resumes the sound if paused
     */
    public void resume(){
        mp.play();
        playing = true;
    }
    
    /**
     *stops the sound
     */
    public void stop(){
        mp.setOnEndOfMedia(null);
        mp.stop();
        playing = false;
    }
    
    /**
     *plays the sound in a loop
     */
    public void loop(){
        playing = true;
        mp.play();
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                play();
            }
        });
    }

    /**
     *
     * @return true if sound is playing, false otherwise
     */
    public boolean isPlaying() {
        return playing;
    }
    
}
