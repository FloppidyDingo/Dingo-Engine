/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Video;

import Media.AdvancedMedia.Connector;
import javafx.scene.image.ImageView;

/**
 *
 * @author James
 */
public abstract class VideoNode extends ImageView{
    protected int resX;
    protected int resY;
    public Connector input;
    
    public abstract void update();

    public int getResX() {
        return resX;
    }

    public void setResX(int w) {
        this.resX = w;
    }

    public int getResY() {
        return resY;
    }

    public void setResY(int h) {
        this.resY = h;
    }
    
    public void setResolution(int w, int h){
        resX = w;
        resY = h;
    }
}
