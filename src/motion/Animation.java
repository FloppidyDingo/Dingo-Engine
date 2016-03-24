/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package motion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Skin;

/**
 *
 * @author James
 */
public class Animation {
    
    private ObservableList<Integer> idList;
    private int frameSkipping;
    private int currentFrame;
    private int currentSkin;
    private boolean running;
    private String ID;
    private Skin skin;
    private static ObservableList<Animation> masterList = FXCollections.observableArrayList();
    
    /**
     *
     */
    public Animation(){
        frameSkipping = 0;
        currentFrame = 0;
        currentSkin = 0;
        running = false;
        masterList.add(this);
        idList = FXCollections.observableArrayList();
    }

    /**
     *Returns the animation's ID
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *set's the animation's ID (may break functionality)
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     *adds a frame to the animation
     * @param frame the frame number
     */
    public void addFrame(int frame){
        idList.add(frame);
    }
    
    /**
     *removes a frame
     * @param frame the frame number
     */
    public void removeFrame(int frame){
        idList.remove(frame);
    }
    
    /**
     *Goes to the next frame in the animation. Is automatically called by the engine.
     */
    public void nextFrame(){
        if (running) {
            if (currentFrame == frameSkipping) {
                currentFrame = -1;
                currentSkin++;
                if (currentSkin > idList.size() - 1) {
                    currentSkin = 0;
                }
                skin.setFrame(idList.get(currentSkin));
            }
            currentFrame++;
        }
    }

    /**
     *returns the skin object associated with this animation.
     * @return
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     *associates a skin object 
     * @param skin
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    
    /**
     *returns true if the animation is playing.
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     *Plays the animation if true, stops if false.
     * @param running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    /**
     *returns the number of frames to wait until going to the next animation frame.
     * @return
     */
    public int getFrameSkipping() {
        return frameSkipping;
    }

    /**
     *sets the number of frames to wait until going to the next animation frame.
     * @param frameSkipping
     */
    public void setFrameSkipping(int frameSkipping) {
        this.frameSkipping = frameSkipping;
    }
    
    /**
     *Destroys this animation object.
     */
    public void destroy(){
        masterList.remove(this);
    }
    
    /**
     *Do NOT call.
     */
    public static void nextFrames(){
        for (Animation animation : masterList) {
            animation.nextFrame();
        }
    }

    public void setFrame(int i) {
        currentSkin = i;
        skin.setFrame(idList.get(currentSkin));
    }
}
