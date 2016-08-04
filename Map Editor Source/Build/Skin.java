/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Build;

import Tools.ImageObject;
import Tools.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class Skin {
    private List<Rectangle> rec;
    private String URL;
    private String ID;
    private ImageObject img;
    private ImageObject buffer;

    public Skin() {
        this.rec = new ArrayList<>();
    }

    public List<Rectangle> getRec() {
        return rec;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ImageObject getImg() {
        return img;
    }

    public void setImg(ImageObject img) {
        this.img = img;
    }
    
    @Override
    public String toString(){
        return ID;
    }

    public void setRec(List<Rectangle> rec) {
        this.rec = rec;
    }

    public ImageObject getBuffer() {
        return buffer;
    }

    public void setBuffer(ImageObject buffer) {
        this.buffer = buffer;
    }
    
}
