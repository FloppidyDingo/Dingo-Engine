/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Audio;

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
public class DingoSoundDriver extends AudioDriver{
    private int sampleRate;
    private Clip clip;
    private AudioFormat af; 
    private byte[] buf;
    
    @Override
    public void update(){
        int[] in = input.getData();
        if (clip != null) {
            clip.stop();
            clip.close();
        }else{
            try{
                clip = AudioSystem.getClip();
            }catch (LineUnavailableException ex) {
                Logger.getLogger(DingoSoundDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int[] LC = new int[in.length / 2];
        int[] RC = new int[in.length / 2];
        for(int i = 0; i < in.length; i += 2){
            LC[i / 2] = in[i];
            RC[i / 2] = in[i + 1];
        }
        for (int i = 0; i < buf.length / 2; i++) {
            buf[2 * i] = (byte)(LC[i] * volume);
            buf[(2 * i) + 1] = (byte)(RC[i] * volume);
        }
        try{
            AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(buf), af, buf.length/2 );
            clip.open(stream);
            clip.start();
        }catch(LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop(){
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

    @Override
    public void init(int SampleRate, int bitDepth, int bufferSize) {
        this.sampleRate = SampleRate;
        this.volume = 127;
        this.af = new AudioFormat(sampleRate, bitDepth, 2, true, false);///sample rate, bit depth, channels, signed, big endian
        buf = new byte[bufferSize * 2];
    }
}
