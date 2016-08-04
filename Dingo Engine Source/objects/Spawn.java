/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.scene.shape.Circle;

/**
 *
 * @author James
 */
public class Spawn extends Circle{
    private String ID;
    private int UD;
    private int time = 1;
    private int count = 0;
    private int bx;
    private int by;
    
    /**
     *
     * @return The spawn's ID.
     */
    public String getID() {
        return ID;
    }
    
    /**
     *Sets the spawn's ID (may break things).
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     *
     * @return The User data
     */
    public int getUD() {
        return UD;
    }
    
    /**
     *Sets the User data
     * @param UD
     */
    public void setUD(int UD) {
        this.UD = UD;
    }

    /**
     *Returns the time to wait in frames between spawns.
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *Sets the time to wait in frames between spawns.
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     *Gets the timer's current time.
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     *Sets the timers time.
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
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
