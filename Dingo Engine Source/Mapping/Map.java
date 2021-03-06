/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import motion.Animation;
import objects.Entity;
import objects.Person;
import objects.Skin;
import objects.Spawn;
import objects.Tile;
import objects.TileSet;
import objects.Trigger;

/**
 *
 * @author James
 */
public class Map {
    private ObservableList<Entity> entityList;//solid entities
    private ObservableList<Entity> objectList;//non-solid Entities
    private ObservableList<Person> personList;//moving AIs
    private ObservableList<Person> personList2;//still AIs
    private ObservableList<Person> enemyList;//enemies, follow target
    private ObservableList<Trigger> triggerList;//activate when intersecting target
    private ObservableList<Entity> doorList;//despawn if unlocked and intersected by target
    private ObservableList<Entity> keyList;//key list for doors
    private ObservableList<Spawn> spawnList;//spawn point for entities
    private ObservableList<Skin> skinList;//skins for entities
    private ObservableList<Animation> animationList;//list of animations
    private ObservableList<Entity> trigTargetList;//list of names of all the targets for a trigger
    private ObservableList<Entity> doorTargetList;//list of entities that can open doors
    private ObservableList<TileSet> tileSetList;//list of entities that can open doors
    private ObservableList<Tile> tileList;//list of entities that can open doors
    private Scene scene;
    private StackPane sp;

    public ObservableList<Entity> getTrigTargetList() {
        return trigTargetList;
    }

    public ObservableList<Entity> getDoorTargetList() {
        return doorTargetList;
    }

    public void setDoorTargetList(ObservableList<Entity> doorTargetList) {
        this.doorTargetList = doorTargetList;
    }
    
    public void setTrigTargetList(ObservableList<Entity> tigTargetList) {
        this.trigTargetList = tigTargetList;
    }
    
    public ObservableList<Skin> getSkinList() {
        return skinList;
    }

    public void setSkinList(ObservableList<Skin> skinList) {
        this.skinList = skinList;
    }

    public ObservableList<Animation> getAnimationList() {
        return animationList;
    }

    public void setAnimationList(ObservableList<Animation> animationList) {
        this.animationList = animationList;
    }

    public ObservableList<TileSet> getTileSetList() {
        return tileSetList;
    }

    public void setTileSetList(ObservableList<TileSet> tileList) {
        this.tileSetList = tileList;
    }

    public ObservableList<Tile> getTileList() {
        return tileList;
    }

    public void setTileList(ObservableList<Tile> tileList) {
        this.tileList = tileList;
    }
    
    public Map() {
        this.entityList = FXCollections.observableArrayList();
        this.objectList = FXCollections.observableArrayList();
        this.personList = FXCollections.observableArrayList();
        this.personList2 = FXCollections.observableArrayList();
        this.enemyList = FXCollections.observableArrayList();
        this.triggerList = FXCollections.observableArrayList();
        this.doorList = FXCollections.observableArrayList();
        this.keyList = FXCollections.observableArrayList();
        this.spawnList = FXCollections.observableArrayList();
        this.trigTargetList = FXCollections.observableArrayList();
        this.skinList = FXCollections.observableArrayList();
        this.animationList = FXCollections.observableArrayList();
        this.doorList = FXCollections.observableArrayList();
        this.doorTargetList = FXCollections.observableArrayList();
        this.tileSetList = FXCollections.observableArrayList();
        this.tileList = FXCollections.observableArrayList();
    }
    
    public Entity getEntity(String ID){
        for (Entity col : entityList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Entity getObject(String ID){
        for (Entity col : objectList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Person getPerson(String ID){
        for (Person col : personList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Person getPerson2(String ID){
        for (Person col : personList2) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Person getEnemy(String ID){
        for (Person col : enemyList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Trigger getTrigger(String ID){
        for (Trigger col : triggerList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Entity getDoor(String ID){
        for (Entity col : doorList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Entity getKey(String ID){
        for (Entity col : keyList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Spawn getSpawn(String ID){
        for (Spawn col : spawnList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Skin getSkin(String ID){
        for (Skin col : skinList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Animation getAnimation(String ID){
        for (Animation col : animationList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Entity getTrigTarget(String ID){
        for (Entity col : trigTargetList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Entity getDoorTarget(String ID){
        for (Entity col : doorTargetList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public TileSet getTileSet(String ID){
        for (TileSet col : tileSetList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    public Tile getTile(String ID){
        for (Tile col : tileList) {
            if (col.getID().equals(ID)) {
                return col;
            }
        }
        return null;
    }
    
    /**
     * returns the map's scene (must call compileScene() first, or you will get null)
     * @return the scene generated from a map
     */
    public Scene getScene() {
        return scene;
    }
    
    public ObservableList<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<Entity> entityList) {
        this.entityList = entityList;
    }

    public ObservableList<Entity> getObjectList() {
        return objectList;
    }

    public void setObjectList(ObservableList<Entity> objectList) {
        this.objectList = objectList;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ObservableList<Person> personList) {
        this.personList = personList;
    }

    public ObservableList<Person> getPersonList2() {
        return personList2;
    }

    public void setPersonList2(ObservableList<Person> personList2) {
        this.personList2 = personList2;
    }

    public ObservableList<Person> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ObservableList<Person> enemyList) {
        this.enemyList = enemyList;
    }

    public ObservableList<Trigger> getTriggerList() {
        return triggerList;
    }

    public void setTriggerList(ObservableList<Trigger> triggerList) {
        this.triggerList = triggerList;
    }

    public ObservableList<Entity> getDoorList() {
        return doorList;
    }

    public void setDoorList(ObservableList<Entity> doorList) {
        this.doorList = doorList;
    }

    public ObservableList<Entity> getKeyList() {
        return keyList;
    }

    public void setKeyList(ObservableList<Entity> keyList) {
        this.keyList = keyList;
    }

    public ObservableList<Spawn> getSpawnList() {
        return spawnList;
    }

    public void setSpawnList(ObservableList<Spawn> spawnList) {
        this.spawnList = spawnList;
    }
    
    
    
    /**
     * generates a scene with the map data
     * @param WindowSizeX
     * @param WindowSizeY
     * @return The compiled scene
     */
    public Scene compileScene(int WindowSizeX, int WindowSizeY){
        sp = new StackPane();
        for (Entity e : entityList) {
            e.addToParent(sp);
        }
        for (Entity e : doorList) {
            e.addToParent(sp);
        }
        for (Entity e : enemyList) {
            e.addToParent(sp);
        }
        for (Entity e : keyList) {
            e.addToParent(sp);
        }
        for (Entity e : personList) {
            e.addToParent(sp);
        }
        for (Entity e : personList2) {
            e.addToParent(sp);
        }
        for (Entity e : objectList) {
            e.addToParent(sp);
        }
        for (Circle e : spawnList) {
            sp.getChildren().add(e);
        }
        for (Trigger e : triggerList) {
            sp.getChildren().add(e);
        }
        for (Tile e : tileList) {
            e.addToParent(sp);
        }
        scene = new Scene(sp, WindowSizeX, WindowSizeY);
        return scene;
    }
}