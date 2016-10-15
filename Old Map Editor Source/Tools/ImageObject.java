/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author James
 */
public class ImageObject {
    private Object data;
    private String ID;
    private BufferedImage img;
    private String type;
    private int x;
    private int y;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageObject(Object data, String ID, BufferedImage img, int x, int y) {
        this.data = data;
        this.ID = ID;
        this.img = img;
        this.x = x;
        this.y = y;
    }
    
    public ImageObject(Object data, String ID, BufferedImage img, int x, int y, int sw, int sh) {
        this.data = data;
        this.ID = ID;
        this.img = img;
        this.x = x;
        this.y = y;
        scale(sw, sh);
    }

    public void scale(int w, int h) {
        BufferedImage img2;
        ColorModel cm = img.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = img.copyData(img.getRaster().createCompatibleWritableRaster());
        img2 = new BufferedImage(cm, raster, isAlphaPremultiplied, null).getSubimage(0, 0, img.getWidth(), img.getHeight());
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int X, Y;
        int ww = img2.getWidth();
        int hh = img2.getHeight();
        for (X = 0; X < w; X++) {
          for (Y = 0; Y < h; Y++) {
            int col = img2.getRGB(X * ww / w, Y * hh / h);
            img.setRGB(X, Y, col);
          }
        }
    }
    
    public ImageObject(Object data, String ID, BufferedImage img, int x, int y, int cx, int cy, int cw, int ch) {
        this.data = data;
        this.ID = ID;
        this.x = x;
        this.y = y;
        crop(img, cx, cy, cw, ch);
    }

    private void crop(BufferedImage image, int cx, int cy, int cw, int ch) {
        img = image.getSubimage(cx, cy, cw, ch);
    }
    
    @Override
    public String toString(){
        return ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
