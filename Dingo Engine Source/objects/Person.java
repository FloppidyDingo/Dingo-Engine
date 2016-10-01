/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author Jaca
 */
public class Person extends Entity{
    private String type;
    private int health;
    private boolean solid;
    private Double jumpTimer;

    /**
     *Returns the Jump Force of the person. Only has significance in Enemies. Useless in Top-Down mode.
     * @return
     */
    public Double getJumpForce() {
        return jumpTimer;
    }

    /**
     *Sets the Jump Force of the person. Only has significance in Enemies. Useless in Top-Down mode.
     * @param jumpTimer
     */
    public void setJumpForce(Double jumpTimer) {
        this.jumpTimer = jumpTimer;
    }
    
    /**
     *Returns true if collisions are detected with this object.
     * @return
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     *Set to true if you want collisions to have an effect on this object.
     * @param solid
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    
    /**
     *
     * @param s The skin to associate with this Person
     */
    public Person(Skin s) {
        super(s);
        resetRectangles();
    }

    /**
     *
     * @param s The skin to associate with this Person
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Person(Skin s, double x, double y) {
        super(s);
        resetRectangles();
        this.setX(x);
        this.setY(y);
    }
    
    /**
     *
     * @param s The skin to associate with this Person
     * @param x The x coordinate
     * @param y The y coordinate
     * @param type Ummm... Don't remember why I put this here. Do whatever you want with it.
     */
    public Person(Skin s, double x, double y, String type) {
        super(s);
        resetRectangles();
        this.setX(x);
        this.setY(y);
        this.type = type;
    }
    
    /**
     *
     * @param s The skin to associate with this Person
     * @param x The x coordinate
     * @param y The y coordinate
     * @param type Ummm... Don't remember why I put this here. Do whatever you want with it.
     * @param health
     */
    public Person(Skin s, double x, double y, String type, int health) {
        super(s);
        resetRectangles();
        this.setX(x);
        this.setY(y);
        this.type = type;
        this.health = health;
    }

    /**
     *
     * @return The health of this Person. An arbritrary variable.
     */
    public int getHealth() {
        return health;
    }

    /**
     *Sets the Person's health. An arbritrary variable.
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    /**
     *
     */
    @Override
    public void resetRectangles(){
        r1.setTranslateY(iv.getTranslateY() - (currentView.getHeight() / 2));
        r2.setTranslateY(iv.getTranslateY() + (currentView.getHeight() / 2));
        r1.setTranslateX(iv.getTranslateX());
        r2.setTranslateX(iv.getTranslateX());
        r3.setTranslateX(iv.getTranslateX() - (currentView.getWidth() / 2));
        r4.setTranslateX(iv.getTranslateX() + (currentView.getWidth() / 2));
        r3.setTranslateY(iv.getTranslateY());
        r4.setTranslateY(iv.getTranslateY());
        r1.setWidth(currentView.getWidth());
        r1.setHeight(3);
        r2.setWidth(currentView.getWidth());
        r2.setHeight(3);
        r3.setWidth(3);
        r3.setHeight(currentView.getHeight());
        r4.setWidth(3);
        r4.setHeight(currentView.getHeight());
    }
    
    /**
     *
     * @return The viewport rectangle of this Person.
     */
    public Rectangle2D getViewPort(){
        return iv.getViewport();
    }

    /**
     *Not sure why I put this here. Do whatever you like with it.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *Not sure why I put this here. Do whatever you like with it.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
