/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import management.Engine;
import management.InvalidVersionException;
import motion.Animation;
import motion.Physics;
import objects.Entity;
import objects.Person;
import objects.Skin;
import objects.Spawn;
import objects.Trigger;


/**
 *
 * @author James
 */
public class Loader {
    /**
    *
    * Creates a Map object using the specified map data file.
     * @param url The filename of the map
     * @param referenceURL Specifies the map folder
     * @param imageURL Specifies the images folder
     * @return The generated Map object
     * @throws management.InvalidVersionException 
    */
    public Map generateMap(String url, String referenceURL, String imageURL) throws InvalidVersionException{
        ObservableList<targetBuffer> targets = FXCollections.observableArrayList();
        Map map = new Map();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(referenceURL + url)))) {
            String cmd = br.readLine();
            if((Integer.parseInt(cmd) > Engine.getMapVersion())){
                throw new InvalidVersionException("Map designed for newer version of the Dingo Engine.");
            }
            while(cmd != null){
                switch(cmd){
                    case "entity":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Entity e = new Entity(sk, x, y);
                        e.setID(br.readLine());
                        System.out.println(e.getID());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setMass(Integer.parseInt(br.readLine()));
                        e.setActive(Boolean.parseBoolean(br.readLine()));
                        e.useSkin(0);
                        e.resetRectangles();
                        if(Boolean.parseBoolean(br.readLine())){
                            map.getDoorTargetList().add(e);
                        }
                        map.getEntityList().add(e);
                        break;
                    }
                    case "object":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Entity e = new Entity(sk, x, y);
                        e.setID(br.readLine());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setActive(Boolean.parseBoolean(br.readLine()));
                        e.useSkin(0);
                        e.resetRectangles();
                        map.getObjectList().add(e);
                        break;
                    }
                    case "person":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Person e = new Person(sk, x, y);
                        e.setID(br.readLine());
                        e.setMass(Integer.parseInt(br.readLine()));
                        e.setSolid(Boolean.parseBoolean(br.readLine()));
                        e.setJumpForce(Double.parseDouble(br.readLine()));
                        e.setHealth(Integer.parseInt(br.readLine()));
                        e.setActive(Boolean.parseBoolean(br.readLine()));
                        e.useSkin(0);
                        e.resetRectangles();
                        map.getPersonList().add(e);
                        break;
                    }
                    case "person2":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Person e = new Person(sk, x, y);
                        e.setID(br.readLine());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setSolid(Boolean.parseBoolean(br.readLine()));
                        e.setHealth(Integer.parseInt(br.readLine()));
                        e.useSkin(0);
                        e.resetRectangles();
                        map.getPersonList2().add(e);
                        break;
                    }
                    case "enemy":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Person e = new Person(sk, x, y);
                        e.setID(br.readLine());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setMass(Integer.parseInt(br.readLine()));
                        e.setSolid(Boolean.parseBoolean(br.readLine()));
                        e.setJumpForce(Double.parseDouble(br.readLine()));
                        e.setHealth(Integer.parseInt(br.readLine()));
                        e.setActive(Boolean.parseBoolean(br.readLine()));
                        e.useSkin(0);
                        e.resetRectangles();
                        if(Boolean.parseBoolean(br.readLine())){
                            map.getDoorTargetList().add(e);
                        }
                        map.getEnemyList().add(e);
                        break;
                    }
                    case "trigger":{
                        int x;
                        int y;
                        int w;
                        int h;
                        x = Integer.parseInt(br.readLine());
                        y = Integer.parseInt(br.readLine());
                        w = Integer.parseInt(br.readLine());
                        h = Integer.parseInt(br.readLine());
                        targetBuffer tb = new targetBuffer();
                        tb.setX(x);
                        tb.setY(y);
                        tb.setW(w);
                        tb.setH(h);
                        cmd = br.readLine();
                        while(!"end".equals(cmd)){
                           tb.getIDs().add(cmd);
                           cmd = br.readLine();
                        }
                        tb.setID(br.readLine());
                        tb.setEnabled(Boolean.parseBoolean(br.readLine()));
                        tb.setSingle(Boolean.parseBoolean(br.readLine()));
                        tb.setUD(Integer.parseInt(br.readLine()));
                        targets.add(tb);
                        break;
                    }
                    case "door":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Entity e = new Entity(sk, x, y);
                        e.setID(br.readLine());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setActive(true);
                        e.useSkin(0);
                        e.resetRectangles();
                        map.getDoorList().add(e);
                        break;
                    }
                    case "key":{
                        double x;
                        double y;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        String skin = br.readLine();
                        Skin sk = new Skin();
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                sk = s;
                                sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                            }
                        }
                        Entity e = new Entity(sk, x, y);
                        e.setID(br.readLine());
                        e.setUserData(Integer.parseInt(br.readLine()));
                        e.setMass(Integer.parseInt(br.readLine()));
                        e.setActive(false);
                        e.useSkin(0);
                        e.resetRectangles();
                        map.getKeyList().add(e);
                        break;
                    }
                    case "spawn":{
                        double x;
                        double y;
                        int time;
                        x = Double.parseDouble(br.readLine());
                        y = Double.parseDouble(br.readLine());
                        time = Integer.parseInt(br.readLine());
                        Spawn sp = new Spawn();
                        sp.setUD(Integer.parseInt(br.readLine()));
                        sp.setTranslateX(x);
                        sp.setTranslateY(y);
                        sp.setRadius(1);
                        sp.setTime(time);
                        sp.setID(br.readLine());
                        sp.setVisible(false);
                        map.getSpawnList().add(sp);
                        break;
                    }
                    case "skin":{
                        Skin sk = new Skin();
                        sk.setID(br.readLine());
                        sk.setBaseImage(new ImageView(new Image(new File(imageURL + br.readLine()).toURI().toString())));
                        cmd = br.readLine();
                        while(!"end".equals(cmd)){//skin viewports
                            double x1 = Integer.parseInt(cmd);
                            cmd = br.readLine();
                            double y1 = Integer.parseInt(cmd);
                            cmd = br.readLine();
                            double x2 = Integer.parseInt(cmd);
                            cmd = br.readLine();
                            double y2 = Integer.parseInt(cmd);
                            sk.addFrame(x1, y1, x2, y2);
                            cmd = br.readLine();
                        }
                        sk.reset();
                        map.getSkinList().add(sk);
                        break;
                    }
                    case "animation":{
                        Animation ani = new Animation();
                        ani.setID(br.readLine());
                        String skin = br.readLine();
                        cmd = br.readLine();
                        while(!"end".equals(cmd)){
                            ani.addFrame(Integer.parseInt(cmd));
                            cmd = br.readLine();
                        }
                        ani.setFrameSkipping(Integer.parseInt(br.readLine()));
                        for (Skin s : map.getSkinList()) {
                            if(s.getID().equals(skin)){
                                ani.setSkin(s);
                            }
                        }
                        ani.setRunning(false);
                        map.getAnimationList().add(ani);
                        break;
                    }
                }
                cmd = br.readLine();
            }
            for (targetBuffer tr : targets) {
                int x = tr.getX();
                int y = tr.getY();
                int w = tr.getW();
                int h = tr.getH();
                Trigger t = new Trigger(x, y, w, h);
                for (String id : tr.getIDs()) {
                    for(Entity e : map.getEntityList()){
                        if(e.getID().equals(id)){
                            map.getTrigTargetList().add(e);
                        }
                    }
                }
                t.setID(tr.getID());
                t.setShowing(false);
                t.setEnabled(tr.isEnabled());
                t.setOneshot(tr.isOneShot());
                t.setUD(tr.getUD());
                map.getTriggerList().add(t);
            }
        } catch (Exception ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }
    
    /**
    *
    * registers a Map to the physics engine
     * @param map
     * @param phy
    */
    public void registerMap(Map map, Physics phy){
        phy.setDoorList(map.getDoorList());
        phy.setEnemyList(map.getEnemyList());
        phy.setEntityList(map.getEntityList());
        phy.setKeyList(map.getKeyList());
        phy.setObjectList(map.getObjectList());
        phy.setPersonList(map.getPersonList());
        phy.setPersonList2(map.getPersonList2());
        phy.setSpawnList(map.getSpawnList());
        phy.setTriggerList(map.getTriggerList());
        phy.setTrigTargetList(map.getTrigTargetList());
        phy.setDoorTargetList(map.getDoorTargetList());
    }
    
    private class targetBuffer{
        private int x;
        private int y;
        private int w;
        private int h;
        private ObservableList<String> IDs = FXCollections.observableArrayList();
        private String ID;
        private boolean enabled;
        private boolean single;
        private int UD;

        private int getY() {
            return x;
        }

        private int getX() {
            return y;
        }

        private int getW() {
            return w;
        }

        private int getH() {
            return h;
        }

        private ObservableList<String> getIDs() {
            return IDs;
        }

        private String getID() {
            return ID;
        }

        private boolean isEnabled() {
            return enabled;
        }

        private boolean isOneShot() {
            return single;
        }

        private int getUD() {
            return UD;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setW(int w) {
            this.w = w;
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setIDs(ObservableList<String> IDs) {
            this.IDs = IDs;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void setSingle(boolean single) {
            this.single = single;
        }

        public void setUD(int UD) {
            this.UD = UD;
        }
        
    }
}