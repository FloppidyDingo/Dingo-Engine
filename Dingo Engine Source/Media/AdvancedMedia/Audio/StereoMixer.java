/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Audio;

import Media.Connector;
import Media.Codec;



/**
 *
 * @author James
 */
public class StereoMixer extends Codec{
    int[] LC;
    int[] RC;

    @Override
    public void process() {
        for (int i = 0; i < in.size(); i++) {
            Connector c = in.get(i);
            if ((i & 1) == 0){//even (left channel)
                
            } else {//odd (right channel)
                
            }
        }
    }
    
    public void link(Codec source, int channel){
        in.add(channel, source.out);
        source.setOrder(order - 1);
    }
}
