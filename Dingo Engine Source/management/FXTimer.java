/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 *
 * @author James
 */
public abstract class FXTimer {

    private final Timeline t;
    
    public FXTimer(int interval){
        t = new Timeline(new KeyFrame(Duration.millis(interval), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                action();
            }
        }));
        
        t.setCycleCount(Timeline.INDEFINITE);
        t.play ();
    }
    
    public void setInterval(int i){
        t.setDelay(Duration.millis(i));
    }
    
    /**
     *Starts the timer.
     */
    public void start(){
        t.play();
    }
    
    /**
     *Stops the timer.
     */
    public void stop(){
        t.stop();
    }
    public abstract void action();
}
