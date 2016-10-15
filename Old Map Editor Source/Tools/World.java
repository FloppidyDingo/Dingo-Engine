/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Build.Entity;
import dingo.Editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class World extends JPanel{
    private int camX;
    private int camY;
    private List<Entity> imgs;
    private Entity track;
    private boolean unlocked;
    private Editor e;
    private Entity selected;

    public World() {
        imgs = new ArrayList<>();
    }
    
    public int getCamX() {
        return camX;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }

    public List<Entity> getImgs() {
        return imgs;
    }
    
    public void lock() {
        track = null;
        unlocked = false;
    }
    
    public void addObj(Entity i) {
        track = i;
        imgs.add(i);
        unlocked = true;
    }
    
    public void addLockedObj(Entity i) {
        imgs.add(i);
    }

    public boolean isUnlocked() {
        return unlocked;
    }
    
    public Entity getCoord(int x, int y){
        Entity im = null;
        for (Entity en : imgs) {
            int x1 = en.getX() - camX;
            int y1 = en.getY() - camY;
            int x2 = en.getX() + en.getSkin().getImg().getImg().getWidth() - camX;
            int y2 = en.getY() + en.getSkin().getImg().getImg().getHeight() - camY;
            if((x1 < x) & (x < x2) & (y1 < y) & (y < y2)){
                im = en;
                break;
            }
        }
        return im;
    }

    public Editor getE() {
        return e;
    }

    public void setE(Editor e) {
        this.e = e;
    }

    public void setTrack(Entity track) {
        this.track = track;
        unlocked = true;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.repaint();
        double mx;
        double my;
        double cx;
        double cy;
        double x;
        double y;
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point p2 = this.getLocationOnScreen();
        mx = p.getX();
        my = p.getY();
        cx = p2.getX();
        cy = p2.getY();
        x = (mx - cx) + camX;
        y = (my - cy) + camY;
        if(track != null){
            Rectangle rec = track.getSkin().getRec().get(0);
            BufferedImage base = track.getSkin().getImg().getImg();
            track.setX((int)x - (track.getSkin().getImg().getImg().getWidth() / 2));
            track.setY((int)y - (track.getSkin().getImg().getImg().getHeight() / 2));
            if(e != null){
                e.setDispCoords(track.getX(), track.getY());
            }
        }
        for (Entity img : imgs) {
            try {
                Rectangle rec = img.getSkin().getRec().get(0);
                BufferedImage image = img.getSkin().getImg().getImg().getSubimage(rec.getX(), rec.getY(), rec.getW(), rec.getH());
                BufferedImage base = img.getSkin().getImg().getImg();
                g.drawImage(image, (img.getX() + (base.getWidth()/ 2)) - (rec.getW() / 2) - camX,
                        (img.getY() + (base.getHeight() /2)) - (rec.getH() / 2) - camY, null);
                
            } catch (RasterFormatException ex) {}
            if (selected != null) {
                g.setColor(Color.red);
                Rectangle rec = selected.getSkin().getRec().get(0);
                BufferedImage image = selected.getSkin().getImg().getImg().getSubimage(rec.getX(), rec.getY(), rec.getW(), rec.getH());
                BufferedImage base = selected.getSkin().getImg().getImg();
                g.drawRect((selected.getX() + (base.getWidth()/ 2)) - (rec.getW() / 2) - camX, 
                        (selected.getY() + (base.getHeight() /2)) - (rec.getH() / 2) - camY, 
                        image.getWidth(), image.getHeight());
            }
        }
    }

    public void select(Entity ent) {
        selected = ent;
    }
}
