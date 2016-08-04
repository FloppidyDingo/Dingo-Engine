/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import motion.Vector;

/**
 *
 * @author Jaca
 */
public class Entity {

    /**
     *
     */
    protected ImageView iv;

    /**
     *
     */
    protected Rectangle r1 = new Rectangle();//top

    /**
     *
     */
    protected Rectangle r2 = new Rectangle();//bottom

    /**
     *
     */
    protected Rectangle r3 = new Rectangle();//left

    /**
     *
     */
    protected Rectangle r4 = new Rectangle();//right

    /**
     *
     */
    protected Rectangle2D currentView;
    
    protected Skin skin;
    private int userData;
    private String ID;
    private Vector dir = new Vector();
    private double mass;
    private boolean active;
    private double bx;
    private double by;
    private static int boundThickness = 5;

    public static int getBoundThickness() {
        return boundThickness;
    }

    public static void setBoundThickness(int boundThickness) {
        Entity.boundThickness = boundThickness;
    }
    
    /**
     *
     * @return true if the Entity is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     *Set's the Entity's Active property. Physics will not apply if not active.
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     *
     * @return The mass of the Entity.
     */
    public double getMass() {
        return mass;
    }

    /**
     *Sets the mass of the Entity.
     * @param mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    
    /**
     *Sets the currently visible frame of the Entity's skin.
     * @param frame
     */
    public void useSkin(int frame){
        skin.setFrame(frame);
        currentView = iv.getViewport();
    }

    /**
     *
     * @return The assigned Skin object of the Entity.
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     *Assigns a Skin object to the Entity.
     * @param skin
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
        iv = skin.getSkin();
    }
    
    /**
     *
     * @return The Entity's motion vector.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     *Set the Entity's motion vector.
     * @param dir
     */
    public void setDir(Vector dir) {
        this.dir = dir;
    }

    /**
     *
     * @return The Entity's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     *Sets the Entity's ID (may break things)
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     *
     * @param s The Skin object.
     */
    public Entity(Skin s){
        skin = s;
        iv = s.getSkin();
        userData = 0;
        this.resetRectangles();
        r1.setFill(Paint.valueOf("red"));
        r2.setFill(Paint.valueOf("red"));
        r3.setFill(Paint.valueOf("red"));
        r4.setFill(Paint.valueOf("red"));
        showRectangles(false);
        
    }

    /**
     *
     * @param s The Skin object.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Entity(Skin s, double x, double y){
        iv = s.getSkin();
        skin = s;
        userData = 0;
        this.resetRectangles();
        r1.setFill(Paint.valueOf("red"));
        r2.setFill(Paint.valueOf("red"));
        r3.setFill(Paint.valueOf("red"));
        r4.setFill(Paint.valueOf("red"));
        showRectangles(false);
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    
    /**
     *
     * @param s The Skin object.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param UD The User Data.
     */
    public Entity(Skin s, double x, double y, int UD){
        iv = s.getSkin();
        userData = UD;
        this.resetRectangles();
        r1.setFill(Paint.valueOf("red"));
        r2.setFill(Paint.valueOf("red"));
        r3.setFill(Paint.valueOf("red"));
        r4.setFill(Paint.valueOf("red"));
        showRectangles(false);
        this.setTranslateX(x);
        this.setTranslateY(y);
        skin = s;
    }
    
    /**
     *Adds the Entity to the given StackPane.
     * @param p
     */
    public void addToParent(StackPane p){
        p.getChildren().addAll(iv, r1, r2, r3, r4);
    }
    
    /**
     *Removes the Entity from the given StackPane.
     * @param p
     */
    public void removeParent(StackPane p){
        p.getChildren().removeAll(iv, r1, r2, r3, r4);
    }
    
    /**
     *Sets the x coordinate of the Entity.
     * @param d
     */
    public void setTranslateX(double d){
        try {
            iv.setTranslateX(d);
            r1.setTranslateX(iv.getTranslateX());
            r2.setTranslateX(iv.getTranslateX());
            r3.setTranslateX(iv.getTranslateX() - (currentView.getWidth() / 2));
            r4.setTranslateX(iv.getTranslateX() + (currentView.getWidth() / 2));
        } catch (NullPointerException e) {
            iv.setTranslateX(d);
            r1.setTranslateX(iv.getTranslateX());
            r2.setTranslateX(iv.getTranslateX());
            r3.setTranslateX(iv.getTranslateX() - (iv.getImage().getWidth() / 2));
            r4.setTranslateX(iv.getTranslateX() + (iv.getImage().getWidth() / 2));
        }
    }
    
    /**
     *Sets the x coordinate of the Entity.
     * @param d
     */
    public void setTranslateY(double d){
        try {
            iv.setTranslateY(d);
            r1.setTranslateY(iv.getTranslateY() - (currentView.getHeight() / 2));
            r2.setTranslateY(iv.getTranslateY() + (currentView.getHeight() / 2));
            r3.setTranslateY(iv.getTranslateY());
            r4.setTranslateY(iv.getTranslateY());
        } catch (Exception e) {
            iv.setTranslateY(d);
            r3.setTranslateY(iv.getTranslateY());
            r4.setTranslateY(iv.getTranslateY());
            r1.setTranslateY(iv.getTranslateY() - (iv.getImage().getHeight() / 2));
            r2.setTranslateY(iv.getTranslateY() + (iv.getImage().getHeight() / 2));
        }
    }
    
    /**
     *Shows the bounding boxes for debugging purposes.
     * @param b
     */
    public void showRectangles(boolean b){
        if(b){
            r1.setOpacity(1);
            r2.setOpacity(1);
            r3.setOpacity(1);
            r4.setOpacity(1);
        }else{
            r1.setOpacity(0);
            r2.setOpacity(0);
            r3.setOpacity(0);
            r4.setOpacity(0);
        }
    }
    
    /**
     *Used to arrange the bounding boxes to their functional positions.
     */
    public void resetRectangles(){
        try {
            r1.setTranslateY(iv.getTranslateY() - (currentView.getHeight() / 2));
            r2.setTranslateY(iv.getTranslateY() + (currentView.getHeight() / 2));
            r3.setTranslateY(iv.getTranslateY());
            r4.setTranslateY(iv.getTranslateY());
            r1.setTranslateX(iv.getTranslateX());
            r2.setTranslateX(iv.getTranslateX());
            r3.setTranslateX(iv.getTranslateX() - (currentView.getWidth() / 2));
            r4.setTranslateX(iv.getTranslateX() + (currentView.getHeight() / 2));
            r1.setWidth(iv.getViewport().getWidth() - (boundThickness / 2));
            r1.setHeight(boundThickness);
            r2.setWidth(iv.getViewport().getWidth() - (boundThickness / 2));
            r2.setHeight(boundThickness);
            r3.setWidth(boundThickness);
            r3.setHeight(iv.getViewport().getHeight() - (boundThickness / 2));
            r4.setWidth(boundThickness);
            r4.setHeight(currentView.getHeight() - (boundThickness / 2));
        } catch (NullPointerException e) {
            r1.setTranslateY(iv.getTranslateY() - (iv.getImage().getHeight() / 2));
            r2.setTranslateY(iv.getTranslateY() + (iv.getImage().getHeight() / 2));
            r1.setTranslateX(iv.getTranslateX());
            r2.setTranslateX(iv.getTranslateX());
            r3.setTranslateX(iv.getTranslateX() - (iv.getImage().getWidth() / 2));
            r4.setTranslateX(iv.getTranslateX() + (iv.getImage().getWidth() / 2));
            r3.setTranslateY(iv.getTranslateY());
            r4.setTranslateY(iv.getTranslateY());
            r1.setWidth(iv.getImage().getWidth() - (boundThickness / 2));
            r1.setHeight(boundThickness);
            r2.setWidth(iv.getImage().getWidth() - (boundThickness / 2));
            r2.setHeight(boundThickness);
            r3.setWidth(boundThickness);
            r3.setHeight(iv.getImage().getHeight() - (boundThickness / 2));
            r4.setWidth(boundThickness);
            r4.setHeight(iv.getImage().getHeight() - (boundThickness / 2));
        }
    }
    
    /**
     *Sets the Entity's base Image.
     * @param i
     */
    public void setImage(Image i){
        iv.setImage(i);
        resetRectangles();
    }
    
    /**
     *Returns a list of collision data. The data goes in this order: Top, bottom, left, and right collisions. coll[4] is true if there are no collisions.
     * @param n
     * @return
     */
    public boolean[] intersects(Bounds n){
        boolean[] coll = new boolean[5];
        coll[4] = true;
        if(n.intersects(r1.getBoundsInParent())){
            coll[0] = true;
            coll[4] = false;
        }
        if(n.intersects(r2.getBoundsInParent())){
            coll[1] = true;
            coll[4] = false;
        }
        if(n.intersects(r3.getBoundsInParent())){
            coll[2] = true;
            coll[4] = false;
        }
        if(n.intersects(r4.getBoundsInParent())){
            coll[3] = true;
            coll[4] = false;
        }
        return coll;
    }

    /**
     *
     * @return The Entity's User Data.
     */
    public int getUserData() {
        return userData;
    }

    /**
     *Sets the Entity's User Data
     * @param userData
     */
    public void setUserData(int userData) {
        this.userData = userData;
    }
    
    /**
     *Sets the Entity's visibility.
     * @param b
     */
    public void setVisible(boolean b){
        r1.setVisible(b);
        r2.setVisible(b);
        r3.setVisible(b);
        r4.setVisible(b);
        iv.setVisible(b);
    }

    /**
     *
     * @return
     */
    public Rectangle getR1() {
        return r1;
    }

    /**
     *
     * @return
     */
    public Rectangle getR2() {
        return r2;
    }

    /**
     *
     * @return
     */
    public Rectangle getR3() {
        return r3;
    }

    /**
     *
     * @return
     */
    public Rectangle getR4() {
        return r4;
    }

    /**
     *Returns the Entity's base Image.
     * @return
     */
    public ImageView getIv() {
        return iv;
    }

    /**
     *Render the Entity on top of everything else.
     */
    public void toFront() {
        r1.toFront();
        r2.toFront();
        r3.toFront();
        r4.toFront();
        iv.toFront();
    }
    
    /**
     *Render the Entity behind everything else.
     */
    public void toBack() {
        r1.toBack();
        r2.toBack();
        r3.toBack();
        r4.toBack();
        iv.toBack();
    }

    /**
     *
     * @return The x coordinate of the Entity.
     */
    public double getTranslateX() {
        return iv.getTranslateX();
    }

    /**
     *
     * @return The y coordinate of the Entity.
     */
    public double getTranslateY() {
        return iv.getTranslateY();
    }

    public double getBx() {
        return bx;
    }

    public void setBx(double bx) {
        this.bx = bx;
    }

    public double getBy() {
        return by;
    }

    public void setBy(double by) {
        this.by = by;
    }
    
}
