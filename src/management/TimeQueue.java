/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *A mod of EventQueue that is triggered automatically.
 * @author Jaca
 */
public abstract class TimeQueue{
    private ObservableList<Trigger> list;
    private long time;
    private int id;
    private Trigger t;
    private static ObservableList<TimeQueue> masterList = FXCollections.observableArrayList();

    /**
     *Creates a new queue and adds it to the cache.
     */
    public TimeQueue() {
        this.list = FXCollections.observableArrayList();
        id = 0;
        masterList.add(this);
    }
    
    private void check(){          
        for (Trigger tr : list) {
            if(tr.getTriggertime() == time){
                trigger(tr.getId(), tr.getDescription());
            }
        }
        time ++;
    }
    
    /**
     *Adds a new event to the queue
     * @param time The time to wait (in frames) until the event is fired.
     * @param description A string describing the event.
     * @return The event ID.
     */
    public int add(long time, String description){
        Trigger ti = new Trigger(id, time + this.time);
        ti.setDescription(description);
        list.add(ti);
        id ++;
        return id - 1;
    }
    
    /**
     *Removes  the queue from the cache.
     */
    public void destroy(){
        masterList.remove(this);
    }
    
    /**
     *Adds a new event to the queue
     * @param time The time to wait (in frames) until the event is fired.
     * @return The event ID.
     */
    public int add(long time){
        Trigger ti = new Trigger(id, time + this.time);
        list.add(ti);
        id ++;
        return id - 1;
    }
    
    /**
     *Clears the queue of events and resets the timer.
     */
    public void clearData(){
        list.clear();
        time = 0;
    }
    
    protected static void masterCheck(){
        for (TimeQueue timeQueue : masterList) {
            timeQueue.check();
        }
    }
    
    /**
     *Called when an event is fired.
     * @param triggerID The event ID.
     * @param description The string describing the event.
     */
    public abstract void trigger(int triggerID, String description);

    private static class Trigger {
        private int id;
        private long triggertime;
        private String description;
        private boolean triggered;

        public Trigger(int id, long time) {
            triggered = false;
            this.id = id;
            this.triggertime = time;
        }

        public long getTriggertime() {
            return triggertime;
        }

        public void setTriggertime(long triggertime) {
            this.triggertime = triggertime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public boolean isTriggered() {
            return triggered;
        }

        public void setTriggered(boolean triggered) {
            this.triggered = triggered;
        }
        
    }
}
