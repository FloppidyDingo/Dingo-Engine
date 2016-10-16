/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author James
 */
public class Tile {
    
    /**
     *
     */
    protected ImageView iv;
    
    private int userData;
    private String ID;
    private TileSet tileset;

    /**
     *
     * @return The Tile's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     *Sets the Tile's ID
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     * 
     * @param s The TileSet object.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Tile(TileSet s, double x, double y){
        tileset = s;
        iv = tileset.getTile(0);
        userData = 0;
        this.setX(x);
        this.setY(y);
    }
    
    /**
     *Adds the Tile to the given StackPane.
     * @param p
     */
    public void addToParent(StackPane p){
        p.getChildren().add(iv);
    }
    
    /**
     *Removes the Tile from the given StackPane.
     * @param p
     */
    public void removeParent(StackPane p){
        p.getChildren().remove(iv);
    }
    
    /**
     *Sets the x coordinate of the Tile.
     * @param d
     */
    public void setX(double d){
        iv.setTranslateX(d);
    }
    
    /**
     *Sets the y coordinate of the Tile.
     * @param d
     */
    public void setY(double d){
        iv.setTranslateY(d);
    }
    
    /**
     *Sets the Tile's Image.
     * @param number
     */
    public void setTile(int number){
        iv.setImage(tileset.getTile(number).getImage());
    }

    /**
     *
     * @return The Tile's User Data.
     */
    public int getUserData() {
        return userData;
    }

    /**
     *Sets the Tile's User Data
     * @param userData
     */
    public void setUserData(int userData) {
        this.userData = userData;
    }
    
    /**
     *Sets the Tile's visibility.
     * @param b
     */
    public void setVisible(boolean b){
        iv.setVisible(b);
    }

    /**
     *Returns the Tile's base Image.
     * @return
     */
    public ImageView getIv() {
        return iv;
    }

    /**
     *Render the Tile on top of everything else.
     */
    public void toFront() {
        iv.toFront();
    }
    
    /**
     *Render the Tile behind everything else.
     */
    public void toBack() {
        iv.toBack();
    }

    /**
     *
     * @return The x coordinate of the Tile.
     */
    public double getX() {
        return iv.getTranslateX();
    }

    /**
     *
     * @return The y coordinate of the Tile.
     */
    public double getY() {
        return iv.getTranslateY();
    }

    public TileSet getTileset() {
        return tileset;
    }

    public void setTileset(TileSet tileset) {
        this.tileset = tileset;
    }
    
}
