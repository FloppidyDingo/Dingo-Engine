/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import management.Engine;
import management.InvalidVersionException;
import motion.Animation;
import motion.Physics;
import objects.Entity;
import objects.Person;
import objects.Skin;
import objects.Spawn;
import objects.Trigger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author James
 */
public class XMLLoader {

    /**
     *
     * Creates a Map object using the specified map data file.
     *
     * @param url The filename of the map
     * @param referenceURL Specifies the map folder
     * @param imageURL Specifies the images folder
     * @return The generated Map object
     * @throws management.InvalidVersionException
     */
    public Map generateMap(String url, String referenceURL, String imageURL) throws InvalidVersionException {
        Map map = new Map();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            saxParser.parse(url, handler);
            if(handler.getVersion() > Engine.getMapVersion()){
                throw new InvalidVersionException("Map designed for newer version of the Dingo Engine.");
            }
            map = handler.getMap();
            for (targetBuffer tr : handler.getTargets()) {
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
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    /**
     *
     * registers a Map to the physics engine
     *
     * @param map
     * @param phy
     */
    public void registerMap(Map map, Physics phy) {
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

    public class targetBuffer {

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

    public class SAXHandler extends DefaultHandler {

        private final ObservableList<targetBuffer> targets = FXCollections.observableArrayList();
        private Map map;
        private String element;
        private String imageURL;
        private targetBuffer tb;
        private Skin skinField;
        private Animation ani;
        private int version;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "map":{
                    version = Integer.parseInt(attributes.getValue("version"));
                    break;
                }
                case "entity": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Entity e = new Entity(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    System.out.println(e.getID());
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setMass(Integer.parseInt(attributes.getValue("mass")));
                    e.setActive(Boolean.parseBoolean(attributes.getValue("actve")));
                    e.useSkin(0);
                    e.resetRectangles();
                    if (Boolean.parseBoolean(attributes.getValue("door-target"))) {
                        map.getDoorTargetList().add(e);
                    }
                    map.getEntityList().add(e);
                    break;
                }
                case "object": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Entity e = new Entity(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setActive(Boolean.parseBoolean(attributes.getValue("active")));
                    e.useSkin(0);
                    e.resetRectangles();
                    map.getObjectList().add(e);
                    break;
                }
                case "person": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Person e = new Person(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setMass(Integer.parseInt(attributes.getValue("mass")));
                    e.setSolid(Boolean.parseBoolean(attributes.getValue("solid")));
                    e.setJumpForce(Double.parseDouble(attributes.getValue("jump-force")));
                    e.setHealth(Integer.parseInt(attributes.getValue("health")));
                    e.setActive(Boolean.parseBoolean(attributes.getValue("active")));
                    e.useSkin(0);
                    e.resetRectangles();
                    map.getPersonList().add(e);
                    break;
                }
                case "person2": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Person e = new Person(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setSolid(Boolean.parseBoolean(attributes.getValue("solid")));
                    e.setHealth(Integer.parseInt(attributes.getValue("health")));
                    e.useSkin(0);
                    e.resetRectangles();
                    map.getPersonList2().add(e);
                    break;
                }
                case "enemy": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Person e = new Person(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setMass(Integer.parseInt(attributes.getValue("mass")));
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setSolid(Boolean.parseBoolean(attributes.getValue("solid")));
                    e.setJumpForce(Double.parseDouble(attributes.getValue("jump-force")));
                    e.setHealth(Integer.parseInt(attributes.getValue("health")));
                    e.setActive(Boolean.parseBoolean(attributes.getValue("active")));
                    e.useSkin(0);
                    e.resetRectangles();
                    if (Boolean.parseBoolean(attributes.getValue("door-target"))) {
                        map.getDoorTargetList().add(e);
                    }
                    map.getEnemyList().add(e);
                    break;
                }
                case "trigger": {
                    int x;
                    int y;
                    int w;
                    int h;
                    x = Integer.parseInt(attributes.getValue("xpos"));
                    y = Integer.parseInt(attributes.getValue("ypos"));
                    w = Integer.parseInt(attributes.getValue("width"));
                    h = Integer.parseInt(attributes.getValue("height"));
                    tb = new targetBuffer();
                    tb.setX(x);
                    tb.setY(y);
                    tb.setW(w);
                    tb.setH(h);
                    element = "trigger";
                    tb.setID(attributes.getValue("ID"));
                    tb.setEnabled(Boolean.parseBoolean(attributes.getValue("enabled")));
                    tb.setSingle(Boolean.parseBoolean(attributes.getValue("one-shot")));
                    tb.setUD(Integer.parseInt(attributes.getValue("UD")));
                    break;
                }
                case "door": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Entity e = new Entity(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setActive(true);
                    e.useSkin(0);
                    e.resetRectangles();
                    map.getDoorList().add(e);
                    break;
                }
                case "key": {
                    double x;
                    double y;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    String skin = attributes.getValue("skin");
                    Skin sk = new Skin();
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            sk = s;
                            sk.setBaseImage(new ImageView(s.getSkin().getImage()));
                        }
                    }
                    Entity e = new Entity(sk, x, y);
                    e.setID(attributes.getValue("ID"));
                    e.setUserData(Integer.parseInt(attributes.getValue("UD")));
                    e.setMass(Integer.parseInt(attributes.getValue("mass")));
                    e.setActive(false);
                    e.useSkin(0);
                    e.resetRectangles();
                    map.getKeyList().add(e);
                    break;
                }
                case "spawn": {
                    double x;
                    double y;
                    int time;
                    x = Double.parseDouble(attributes.getValue("xpos"));
                    y = Double.parseDouble(attributes.getValue("ypos"));
                    time = Integer.parseInt(attributes.getValue("time"));
                    Spawn sp = new Spawn();
                    sp.setUD(Integer.parseInt(attributes.getValue("UD")));
                    sp.setTranslateX(x);
                    sp.setTranslateY(y);
                    sp.setRadius(1);
                    sp.setTime(time);
                    sp.setID(attributes.getValue("ID"));
                    sp.setVisible(false);
                    map.getSpawnList().add(sp);
                    break;
                }
                case "skin": {
                    skinField = new Skin();
                    skinField.setID(attributes.getValue("ID"));
                    skinField.setBaseImage(new ImageView(new Image(new File(imageURL + attributes.getValue("URL")).toURI().toString())));
                    element = "skin";
                    break;
                }
                case "animation": {
                    ani = new Animation();
                    ani.setID(attributes.getValue("ID"));
                    String skin = attributes.getValue("skin");
                    element = "animation";
                    ani.setFrameSkipping(Integer.parseInt(attributes.getValue("skip")));
                    for (Skin s : map.getSkinList()) {
                        if (s.getID().equals(skin)) {
                            ani.setSkin(s);
                        }
                    }
                    ani.setRunning(false);
                    break;
                }
                case "viewport":{
                    if ("skin".equals(element)) {
                        double x1 = Integer.parseInt(attributes.getValue("x1"));
                        double y1 = Integer.parseInt(attributes.getValue("y1"));
                        double x2 = Integer.parseInt(attributes.getValue("x2"));
                        double y2 = Integer.parseInt(attributes.getValue("y2"));
                        skinField.addFrame(x1, y1, x2, y2);
                    }
                    break;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch(qName){
                case "trigger":{
                    targets.add(tb);
                    tb = null;
                    break;
                }
                case "skin":{
                    skinField.reset();
                    map.getSkinList().add(skinField);
                    skinField = null;
                    break;
                }
                case "animation":{
                    map.getAnimationList().add(ani);
                    ani = null;
                    break;
                }
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String input = String.copyValueOf(ch);
            List<String> items = Arrays.asList(input.split("\\s*,\\s*"));
            switch(element){
                case "trigger":{
                    for (String item : items) {
                        tb.getIDs().add(item); 
                    }
                    element = "";
                    break;
                }
                case "animation":{
                    for (String item : items) {
                        ani.addFrame(Integer.parseInt(item));
                    }
                    element = "";
                    break;
                }
            }
            
        }

        public ObservableList<targetBuffer> getTargets() {
            return targets;
        }

        public Map getMap() {
            return map;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public int getVersion() {
            return version;
        }
        
    }
}
