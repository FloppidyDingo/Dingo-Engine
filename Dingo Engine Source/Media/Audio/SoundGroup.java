/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Media.Audio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jaca
 */
public class SoundGroup {
    private ObservableList<Sound> sl;
    
    public SoundGroup(){
        sl = FXCollections.observableArrayList();
    }
    
    public void setVolume(double d){
        for (Sound sound : sl) {
            sound.setVolume(d);
        }
    }
    
    public void setMute(boolean b){
        for (Sound sound : sl) {
            sound.setMute(b);
        }
    }
    
    public void setBalance(double d){
        for (Sound sound : sl) {
            sound.setBalance(d);
        }
    }
    
    public void add(Sound s){
        sl.add(s);
        s.setVolume(1);
    }
    
    public void remove(Sound s){
        sl.remove(s);
    }
    
    public void clear(){
        sl.clear();
    }
    
    public void stop(){
        for (Sound sound : sl) {
            sound.stop();
        }
    }
    
    public void pause(){
        for (Sound sound : sl) {
            sound.pause();
        }
    }

    public ObservableList<Sound> getList() {
        return sl;
    }
    
}
