/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author James
 */
public class Skin {
    
    private Rectangle2D v;
    private ImageView iv;
    private final ObservableList<Rectangle2D> skins;
    private String ID;
    
    public Skin(){
        iv = new ImageView();
        iv.setViewport(v);
        skins = FXCollections.observableArrayList();
    }

    /**
     *
     * @return The skin's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     *Sets the skin's ID (may break things).
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     *Returns the skin's base image.
     * @return
     */
    public ImageView getSkin(){
        return iv;
    }
    
    /**
     *
     * @param x1 The x coordinate of the top left corner
     * @param y1 The y coordinate of the top left corner
     * @param x2 The x coordinate of the bottom right corner
     * @param y2 The y coordinate of the bottom right corner
     */
    public void addFrame(double x1, double y1, double x2, double y2){
        skins.add(new Rectangle2D(x1, y1, x2 - x1, y2 - y1));
    }
    
    /**
     *Removes the specified frame.
     * @param frame
     */
    public void removeFrame(int frame){
        skins.remove(frame);
    }
    
    /**
     *Sets the skin's frame to frame 0.
     */
    public void reset(){
        iv.setViewport(skins.get(0));
        v = skins.get(0);
    }
    
    /**
     *Removes all frames.
     */
    public void clear(){
        skins.clear();
    }
    
    /**
     *Generates skin rectangles from a definition image
     * @param url of the Skin Definitions Image
     * @throws IOException
     */
    public void bufferSkinDef(String url) throws IOException{
        try {
            BufferedImage image = ImageIO.read(new File(url));
            int fx = 0;
            int fy = 0;
            int state = 0;
            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    switch(state){
                        case 0:{
                            if(image.getRGB(x, y) == Color.BLACK.getRGB()){
                                image.setRGB(x, y, Color.RED.getRGB());
                                fx = x;
                                fy = y;
                                state = 1;
                            }
                            break;
                        }
                        case 1:{
                            if(image.getRGB(x, y) == Color.BLACK.getRGB()){
                                image.setRGB(x, y, Color.RED.getRGB());
                                for(int y2 = y; y2 < image.getHeight(); y2++){
                                    if(image.getRGB(x, y2) == Color.BLACK.getRGB()){
                                        image.setRGB(x, y2, Color.RED.getRGB());
                                        this.addFrame(fx, fy, x, y2);
                                        state = 0;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Sets the skin's base image.
     * @param iv
     */
    public void setBaseImage(ImageView iv) {
        this.iv = iv;
    }
    
    /**
     *Sets the skin's current frame.
     * @param frame
     */
    public void setFrame(int frame){
        Rectangle2D r = skins.get(frame);
        v = r;
        iv.setViewport(v);
    }
    
}
