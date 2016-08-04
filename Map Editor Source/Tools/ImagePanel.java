package Tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    private BufferedImage img;
    private int x;
    private int y;
    private int w;
    private int h;

    public ImagePanel(String url) {
       try {                
          image = ImageIO.read(new File(url));
          img = image.getSubimage(x, y, w, h);
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    public ImagePanel(String url, int x, int y, int w, int h) {
       try {                
          image = ImageIO.read(new File(url));
          img = image.getSubimage(x, y, w, h);
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    public ImagePanel() {
       
    }
    
    public ImagePanel(BufferedImage i){
        image = i;
        img = image.getSubimage(x, y, w, h);
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
        img = image;
    }
    
    public void crop(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        crop();
    }
    
    public void scale(int w, int h){
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int X, Y;
        int ww = image.getWidth();
        int hh = image.getHeight();
        for (X = 0; X < w; X++) {
          for (Y = 0; Y < h; Y++) {
            int col = image.getRGB(X * ww / w, Y * hh / h);
            img.setRGB(X, Y, col);
          }
        }
    }
    
    public void scaleCurrent(int w, int h){
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
    
    public void scaleLocal(){
        int w = this.getWidth();
        int h = this.getHeight();
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
    
    private void crop(){
        img = image.getSubimage(x, y, w, h);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.repaint();
        if(img != null){
            g.drawImage(img, 0, 0, null); // see javadoc for more info on the parameters      
        }
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }

    public BufferedImage getImg() {
        return img;
    }
    
}