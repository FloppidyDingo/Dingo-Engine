/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Audio;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author James
 */
public class DingoSoundDriver extends AudioDriver{
    private int sampleRate;
    private AudioFormat af;
    private byte[] buf;
    private SourceDataLine line;
    
    @Override
    public void update(){
        for (int i = 0; i < buf.length; i++) {
            buf[i] = 0;
        }
        if(input == null){
            return;
        }
        if(input.getData() == null){
            return;
        }
        int[] in = input.getData();
        for (int i = 0; i < in.length; i++) {
            float j = in[i];
            buf[i] = (byte)((float)j * volume);
        }
        line.write(buf, 0, buf.length);
    }
    
    @Override
    public void stop(){
        if(line != null){
            line.flush();
            line.stop();
            line.close();
        }
    }

    @Override
    public void init(int SampleRate, int bitDepth, int bufferSize) {
        this.sampleRate = SampleRate;
        this.volume = 1;
        this.af = new AudioFormat(sampleRate, bitDepth, 2, true, false);///sample rate, bit depth, channels, signed, little endian
        buf = new byte[bufferSize * 2];
        try {
            line = AudioSystem.getSourceDataLine(af);
            line.open(af, bufferSize * 2);
            line.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(DingoSoundDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getSampleRate() {
        return sampleRate;
    }
    
}
