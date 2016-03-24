/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;

/**
 *A timer that fires an event once every specified interval.
 * Not in sync with the animation timer.
 * @author Jaca
 */
public abstract class Timer {
    private javax.swing.Timer t;
    
    /**
     *
     * @param interval The time in between event calls (in microseconds)
     */
    public Timer(int interval){
        t = new javax.swing.Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        action();
                    }
                });
            }
        });
        t.stop();
        t.setCoalesce(false);
    }
    
    /**
     *
     * @param i The time in between event calls (in microseconds)
     */
    public void setInterval(int i){
        t.setDelay(i);
    }
    
    /**
     *Starts the timer.
     */
    public void start(){
        t.start();
    }
    
    /**
     *Stops the timer.
     */
    public void stop(){
        t.stop();
    }
    
    /**
     *Called everytime the specified interval has passed.
     */
    public abstract void action();
}
