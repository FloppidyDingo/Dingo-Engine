/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Build.Skin;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author James
 */
public class SkinCreator {
    private Error err;

    public SkinCreator() {
        this.err = new Error();
    }
    
    public Skin generateSkin(File f){
        Skin sk = new Skin();
        try {
            BufferedImage image = ImageIO.read(f);
            System.out.println(image.getWidth());
            System.out.println(image.getHeight());
            Rectangle[] rec = new Rectangle[image.getWidth() * image.getHeight() * 3];
            int state = 0;
            int index = 0;
            int previousX = 0;
            int previousY;
            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    switch(state){
                        case 0:{
                            if(image.getRGB(x, y) == Color.BLACK.getRGB()){
                                image.setRGB(x, y, Color.RED.getRGB());
                                rec[index] = new Rectangle();
                                rec[index].setX(x);
                                rec[index].setY(y);
                                previousX = x;
                                state = 1;
                            }
                            break;
                        }
                        case 1:{
                            if(image.getRGB(x, y) == Color.BLACK.getRGB()){
                                previousY = y;
                                image.setRGB(x, y, Color.RED.getRGB());
                                rec[index].setW((x - previousX) + 1);
                                for(int y2 = y; y2 < image.getHeight(); y2++){
                                    if(image.getRGB(x, y2) == Color.BLACK.getRGB()){
                                        image.setRGB(x, y2, Color.RED.getRGB());
                                        rec[index].setH((y2 - previousY) + 1);
                                        index++;
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
            for (Rectangle rec1 : rec) {
                if(rec1 == null){
                    break;
                }
                sk.getRec().add(rec1);
            }
        } catch (IOException ex) {
            err.setVisible(true);
            err.setMessage("Error creating skin");
            err.setStacktrace(ex);
        }
        return sk;
    }
}