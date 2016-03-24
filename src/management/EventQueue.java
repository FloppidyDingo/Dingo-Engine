/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package management;

import javafx.application.Platform;

/**
 *A event handler that keeps track of how many times it's been triggered. Triggered manually
 * @author Jaca
 */
public abstract class EventQueue{
    private int trig;
    
    /**
     *
     */
    public EventQueue(){
        trig = 0;
    }
    
    /**
     *Trips the trigger. Calling this increments the trigger counter.
     */
    public void trigger(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                action(trig);
                trig++;
            }
        });
    }
    
    /**
     *Set's the trigger counter to 0.
     */
    public void reset(){
        trig = 0;
    }
    
    /**
     *Set's the trigger counter.
     * @param i Value to set the counter to.
     */
    public void jump(int i){
        trig = i;
    }
    
    /**
     *Returns the number of times this object's been triggered.
     * @return
     */
    public int getCount(){
        return trig;
    }
    
    /**
     *The method called when trigger() is called.
     * @param count The number of times the object's been triggered.
     */
    public abstract void action(int count);
        
}
