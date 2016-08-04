/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedAudio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author James
 */
public class DingoSoundDriver {
    private int sampleRate;
    private int volume;
    private Clip clip;
    private AudioFormat af; 
    private byte[] buf;

    public DingoSoundDriver(int SampleRate, int bitDepth, int bufferSize) {
        this.sampleRate = SampleRate;
        this.volume = 127;
        this.af = new AudioFormat(sampleRate, bitDepth, 2, true, false);///sample rate, bit depth, channels, signed, big endian
        buf = new byte[bufferSize];
    }
    
    public void tone(int freq){//change
        int frequency = freq * 10;
        if ( clip!=null ) {
            clip.stop();
            clip.close();
        } else {
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(DingoSoundDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int frame = 0;
        for(int i=0; i<8000; i++){
            if(frame > (8000 / frequency * 10)){
                buf[i*2] = (byte)volume;
                frame = 0;
            }else{
                buf[i*2] = 0;
            }
            frame ++;
            buf[(i*2)+1] = buf[i*2];
        }
        try {
            AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(buf), af, buf.length/2 );
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void stopAll(){
        if(clip == null){
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(DingoSoundDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clip.stop();
        clip.close();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
