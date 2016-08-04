/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *Event object used by the Trigger class.
 * @author James
 */
public abstract class Event {

    /**
     *Called when associated trigger is fired.
     */
    public abstract void fire();
}
