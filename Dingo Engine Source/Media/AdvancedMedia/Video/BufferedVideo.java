/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia.Video;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author James
 */
public class BufferedVideo extends VideoNode{
    
    @Override
    public void update(){
        WritableImage wr = new WritableImage(resX, resY);
        PixelWriter pw = wr.getPixelWriter();
        for (int y = 0; y < wr.getWidth(); y++) {
            for (int x = 0; x < wr.getHeight(); x++) {
                pw.setArgb(x, y, input.getData()[(y * resX) + x]);
            }
        }
        this.setImage(wr);
    }

    @Override
    public void stop() {
        
    }
}
