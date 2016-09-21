/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Audio;

import Media.Codec;
import Media.Connector;



/**
 *
 * @author James
 */
public abstract class AudioDriver {
    protected float volume;
    protected String name;
    protected String version;
    public Connector input;
    
    public abstract void update();
    
    public abstract void stop();
    
    public abstract void init(int SampleRate, int bitDepth, int bufferSize);
    
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void link(Codec source) {
        this.input = source.getOut();
    }
    
}
