/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jaca
 */
public class Trigger extends Rectangle{
    private int userData;
    private boolean fired;
    private Event event;
    private String ID;
    private boolean enabled;
    private boolean oneshot;
    private int bx;
    private int by;

    /**
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h the height.
     */
    public Trigger(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.setTranslateX(-x);
        this.setTranslateY(-y);
        this.setFill(Paint.valueOf("purple"));
        this.setOpacity(0);
        this.enabled = true;
        fired = false;
        event = new Event() {
            @Override
            public void fire() {}
        };
    }
    
    /**
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h the height.
     * @param UD The User Data
     */
    public Trigger(double x, double y, double w, double h, int UD) {
        super(x, y, w, h);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setFill(Paint.valueOf("purple"));
        this.setOpacity(0);
        this.enabled = true;
        userData = UD;
        fired = false;
        event = new Event() {
            @Override
            public void fire() {}
        };
    }
    
    /**
     *Set to true to make trigger visible (for debugging purposes)
     * @param b
     */
    public void setShowing(boolean b){
        if(b){
            this.setOpacity(1);
        }else{
            this.setOpacity(0);
        }
    }
    
    /**
     *
     * @return The User Data.
     */
    public int getUD() {
        return userData;
    }

    /**
     *Sets the User Data (may break things).
     * @param userData
     */
    public void setUD(int userData) {
        this.userData = userData;
    }
    
    /**
     *Assigns an Event object to fire when triggered.
     * @param e
     */
    public void setOnFire(Event e){
        event = e;
    }
    
    /**
     *Returns the associated Event object.
     * @return
     */
    public Event getOnFired(){
        return event;
    }
    
    /**
     *Resets a One-shot trigger to be fired again.
     */
    public void resetOneShot(){
        fired = false;
    }
    
    /**
     *Disables a one-shot trigger.
     */
    public void disableOneShot(){
        fired = true;
    }

    /**
     *
     * @return The trigger's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     *Sets the trigger's ID.
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *Returns true if the trigger is enabled.
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     *Enables or disables the trigger.
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     *Fires the trigger (should not be called manually, the physics engine takes care of this).
     */
    public void fire(){
        if (!oneshot) {
            event.fire();
        } else {
            if (!fired) {
                event.fire();
            }
            fired = true;
        }
    }

    /**
     *
     * @return True if trigger can only be fired once.
     */
    public boolean isOneshot() {
        return oneshot;
    }

    /**
     *Sets whether the trigger can only be fired once or multiple times.
     * @param oneshot
     */
    public void setOneshot(boolean oneshot) {
        this.oneshot = oneshot;
    }

    public int getBx() {
        return bx;
    }

    public void setBx(int bx) {
        this.bx = bx;
    }

    public int getBy() {
        return by;
    }

    public void setBy(int by) {
        this.by = by;
    }
    
}
