/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package motion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import management.Utils;
import objects.Entity;
import objects.Person;
import objects.Spawn;
import objects.Trigger;

/**
 *
 * @author James
 */
public abstract class Physics {
    private ObservableList<Entity> entityList;//solid entities
    private ObservableList<Entity> objectList;//non-solid Entities
    private ObservableList<Person> personList;//moving AIs
    private ObservableList<Person> personList2;//still AIs
    private ObservableList<Person> enemyList;//enemies, follow target
    private ObservableList<Trigger> triggerList;//activate when intersecting target
    private ObservableList<Entity> doorList;//despawn if unlocked and intersected by target
    private ObservableList<Entity> keyList;//key list for doors
    private ObservableList<Spawn> spawnList;//spawn point for entities
    private ObservableList<Entity> trigTargetList;//list of entities of all the targets for a trigger
    private ObservableList<Entity> doorTargetList;//list of entities that can open doors
    private ObservableList<Entity> physList;//list of applied physics
    private ObservableList<Entity> enemyTargets;//list of targets for enemies to follow
    private int mode;
    private int enemyRange;
    private double gravity;
    private double maxGVelocity;

    /**
     * the physics engine will run in platformer mode.
     */
    public static final int PLATFORMER = 0;

    /**
     * the physics engine will run in top-down mode.
     */
    public static final int TOP_DOWN = 1;
    
    private Vector camDir;
    private Vector camPos;
    private boolean trackcam;
    private Entity camTarget;
    private Double AISpeed;
    private int collisionMode = 3;
    public static final int LIVE_COLLISION = 2;
    public static final int PREDICT_COLLISION = 3;

    /**
     *creates a new physics engine object 
     */
    public Physics() {
        this.camDir = new Vector();
        this.camPos = new Vector();
        gravity = 1;
        mode = 0;
        enemyRange = 10;
        entityList = FXCollections.observableArrayList();
        objectList = FXCollections.observableArrayList();
        personList = FXCollections.observableArrayList();
        personList2 = FXCollections.observableArrayList();
        enemyList = FXCollections.observableArrayList();
        triggerList = FXCollections.observableArrayList();
        doorList = FXCollections.observableArrayList();
        keyList = FXCollections.observableArrayList();
        spawnList = FXCollections.observableArrayList();
        trigTargetList = FXCollections.observableArrayList();
        doorTargetList = FXCollections.observableArrayList();
        physList = FXCollections.observableArrayList();
        enemyTargets = FXCollections.observableArrayList();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    /**
     * sets the location of the camera
     * @param camPos
     */
    public void setCamPos(Vector camPos) {    
        this.camPos = camPos;
    }
    
    /**
     * gets the vector storing the camera's location
     * @return the camera's position vector
     */
    public Vector getCamPos() {
        return camPos;
    }
    
    /**
     *
     * @return 
     */
    public Vector getCamDir() {
        return camDir;
    }
    
    /**
     *
     * @return
     */
    public Double getAISpeed() {
        return AISpeed;
    }
    
    /**
     *
     * @param AISpeed
     */
    public void setAISpeed(Double AISpeed) {
        this.AISpeed = AISpeed;
    }
    
    /**
     *
     * @param camDir
     */
    public void setCamDir(Vector camDir) {
        this.camDir = camDir;
    }
    
    /**
     *
     * @return
     */
    public int getMode() {
        return mode;
    }
    
    /**
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getEntityList() {
        return entityList;
        
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getTrigTargetList() {
        return trigTargetList;
    }
    
    /**
     *
     * @param trigTargetList
     */
    public void setTrigTargetList(ObservableList<Entity> trigTargetList) {
        this.trigTargetList = trigTargetList;
    }
    
    /**
     *
     * @param entityList
     */
    public void setEntityList(ObservableList<Entity> entityList) {
        this.entityList = entityList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getObjectList() {
        return objectList;
    }
    
    /**
     *
     * @param objectList
     */
    public void setObjectList(ObservableList<Entity> objectList) {
        this.objectList = objectList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Person> getPersonList() {
        return personList;
    }
    
    /**
     *
     * @param personList
     */
    public void setPersonList(ObservableList<Person> personList) {
        this.personList = personList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Person> getPersonList2() {
        return personList2;
    }
    
    /**
     *
     * @param personList2
     */
    public void setPersonList2(ObservableList<Person> personList2) {
        this.personList2 = personList2;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Person> getEnemyList() {
        return enemyList;
    }
    
    /**
     *
     * @param enemyList
     */
    public void setEnemyList(ObservableList<Person> enemyList) {
        this.enemyList = enemyList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Trigger> getTriggerList() {
        return triggerList;
    }
    
    /**
     *
     * @param triggerList
     */
    public void setTriggerList(ObservableList<Trigger> triggerList) {
        this.triggerList = triggerList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getDoorList() {
        return doorList;
    }
    
    /**
     *
     * @param doorList
     */
    public void setDoorList(ObservableList<Entity> doorList) {
        this.doorList = doorList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getKeyList() {
        return keyList;
    }
    
    /**
     *
     * @param keyList
     */
    public void setKeyList(ObservableList<Entity> keyList) {
        this.keyList = keyList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Spawn> getSpawnList() {
        return spawnList;
    }
    
    /**
     *
     * @param spawnList
     */
    public void setSpawnList(ObservableList<Spawn> spawnList) {
        this.spawnList = spawnList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getDoorTargetList() {
        return doorTargetList;
    }
    
    /**
     *
     * @param doorTargetList
     */
    public void setDoorTargetList(ObservableList<Entity> doorTargetList) {
        this.doorTargetList = doorTargetList;
    }
    
    /**
     *
     * @return
     */
    public boolean isTrackcam() {
        return trackcam;
    }
    
    /**
     *
     * @param trackcam
     */
    public void setTrackcam(boolean trackcam) {
        this.trackcam = trackcam;
    }
    
    /**
     *
     * @return
     */
    public Entity getCamTarget() {
        return camTarget;
    }
    
    /**
     *
     * @param camTarget
     */
    public void setCamTarget(Entity camTarget) {
        this.camTarget = camTarget;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Entity> getEnemyTargets() {
        return enemyTargets;
    }
    
    /**
     *
     * @param enemyTargets
     */
    public void setEnemyTargets(ObservableList<Entity> enemyTargets) {
        this.enemyTargets = enemyTargets;
    }
    
    /**
     *
     * @return
     */
    public double getGravity() {
        return gravity;
    }
    
    /**
     *
     * @param gravity
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    
    /**
     *
     * @return
     */
    public int getEnemyRange() {
        return enemyRange;
    }
    
    /**
     *
     * @param enemyRange
     */
    public void setEnemyRange(int enemyRange) {
        this.enemyRange = enemyRange;
    }
//</editor-fold>
    
    private void update(){
        physList.clear();
        physList.addAll(entityList);
        for (Person e : personList) {
            if(e.isSolid()){
                physList.add(e);
            }
        }
        for (Person e : personList2) {
            if(e.isSolid()){
                physList.add(e);
            }
        }
        for (Person e : enemyList) {
            if(e.isSolid()){
                physList.add(e);
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="map node manipulation">

    /**
     *
     * @param e
     */
        public void unlinkEntity(Entity e){
        boolean b = true;
        while(b){
            b = entityList.remove(e);
        }
        b = true;
        while(b){
            b = objectList.remove(e);
        }
        b = true;
        while(b){
            b = doorList.remove(e);
        }
        b = true;
        while(b){
            b = keyList.remove(e);
        }
    }
    
    /**
     *
     * @param p
     */
    public void unlinkPerson(Person p){
        boolean b;
        b = true;
        while(b){
            b = personList.remove(p);
        }
        b = true;
        while(b){
            b = personList2.remove(p);
        }
        b = true;
        while(b){
            b = enemyList.remove(p);
        }
    }
    
    /**
     *
     * @param s
     */
    public void unlinkSpawn(Spawn s){
        boolean b;
        b = true;
        while(b){
            b = spawnList.remove(s);
        }
    }
    
    /**
     *
     * @param t
     */
    public void unlinkTrigger(Trigger t){
        boolean b;
        b = true;
        while(b){
            b = triggerList.remove(t);
        }
    }
//</editor-fold>
    
    /**
     *
     * @param e1
     * @param e2
     */
    public abstract void collision(Entity e1, Entity e2);
    
    /**
     *
     * @param spawn
     * @param e
     */
    public abstract void spawn(Spawn spawn, Entity e);
    
    /**
     *
     */
    public abstract void preTick();
    
    /**
     *
     */
    public abstract void postTick();

    public double getMaxGVelocity() {
        return maxGVelocity;
    }

    public void setTerminalVelocity(double maxGVelocity) {
        this.maxGVelocity = maxGVelocity;
    }

    public int getCollisionMode() {
        return collisionMode;
    }

    public void setCollisionMode(int collisionMode) {
        this.collisionMode = collisionMode;
    }

    /**
     *
     */
    public void check() {
        update();
        //<editor-fold defaultstate="collapsed" desc="spawner">
        for(Spawn s : spawnList){
            if(s.getTime() > s.getCount()){
                spawn(s, new Entity(null));
                s.setTime(0);
            }
            s.setTime(s.getTime() + 1);
        }
//</editor-fold>
        preTick();
        //<editor-fold defaultstate="collapsed" desc="keys">
        for (Entity e : doorTargetList) {
            for (Entity key : keyList) {
                if((!(key.intersects(e.getIv().getBoundsInParent())[4])) & key.isActive()){
                    key.setActive(false);
                    key.setVisible(false);
                    collision(e, key);
                }
            }
        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="AI">
        for(Person en : enemyList){
            try {
                Entity e = Utils.getClosest(enemyTargets, en);
                if((Utils.getRange(e.getIv(), en.getIv()) < enemyRange) & en.isActive()){
                    if (mode == PLATFORMER) {
                        switch (Utils.getRelative(en, e)[0]) {
                            case Utils.ABOVE_LEFT: {
                                en.getDir().applyForce(AISpeed, 0);
                                break;
                            }
                            case Utils.BELOW_RIGHT: {
                                en.getDir().applyForce(-AISpeed, 0);
                                break;
                            }
                        }
                    } else {
                        switch (Utils.getRelative(en, e)[0]) {
                            case Utils.ABOVE_LEFT: {
                                en.getDir().applyForce(AISpeed, 0);
                                break;
                            }
                            case Utils.BELOW_RIGHT: {
                                en.getDir().applyForce(-AISpeed, 0);
                                break;
                            }
                        }
                        switch (Utils.getRelative(en, e)[1]) {
                            case Utils.ABOVE_LEFT: {
                                en.getDir().applyForce(0, -AISpeed);
                                break;
                            }
                            case Utils.BELOW_RIGHT: {
                                en.getDir().applyForce(0, AISpeed);
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {}
        }
        for (Person p : personList) {
            if(p.isActive()){
                if(Utils.randInt(1, 10) == 1){
                    if(mode == PLATFORMER){
                        p.setUserData(-p.getUserData());
                    }else{
                        p.setUserData(Utils.randInt(1, 4));
                    }
                }
                if(mode == PLATFORMER){
                    p.getDir().applyForce(AISpeed * p.getUserData(), 0);
                }else{
                    switch(p.getUserData()){
                        case 1:{
                            p.getDir().applyForce(-AISpeed, 0);
                            break;
                        }
                        case 2:{
                            p.getDir().applyForce(AISpeed, 0);
                            break;
                        }
                        case 3:{
                            p.getDir().applyForce(0, -AISpeed);
                            break;
                        }
                        case 4:{
                            p.getDir().applyForce(0, AISpeed);
                            break;
                        }
                    }
                }
            }
        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="gravity">
        if (mode == PLATFORMER) {
            for (Entity e : entityList) {
                if (!(e.getDir().getY() > maxGVelocity)) {
                    e.getDir().applyForce(0, gravity * e.getMass());
                }
            }
            for (Entity e : personList) {
                if (!(e.getDir().getY() > maxGVelocity)) {
                    e.getDir().applyForce(0, gravity * e.getMass());
                }
            }
            for (Entity e : personList2) {
                if (!(e.getDir().getY() > maxGVelocity)) {
                    e.getDir().applyForce(0, gravity * e.getMass());
                }
            }
            for (Entity e : enemyList) {
                if (!(e.getDir().getY() > maxGVelocity)) {
                    e.getDir().applyForce(0, gravity * e.getMass());
                }
            }
            for (Entity e : keyList) {
                if (!(e.getDir().getY() > maxGVelocity)) {
                    e.getDir().applyForce(0, gravity * e.getMass());
                }
            }
        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Collisions (triggers, doors, solid entities">
        if(collisionMode == LIVE_COLLISION){
            liveCollision();
        }else if(collisionMode == PREDICT_COLLISION){
            predictionCollision();
        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="camera movement">
        //camPos.setDirection(camPos.getX() + camDir.getX(), camPos.getY() + camDir.getY());
        camPos.applyForce(camDir);
        for(Entity e : entityList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : objectList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : personList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : personList2){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : doorList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : keyList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Entity e : enemyList){
            e.setX(e.getX() - camDir.getX());
            e.setY(e.getY() + camDir.getY());
        }
        for(Trigger e : triggerList){
            e.setTranslateX(e.getTranslateX() - camDir.getX());
            e.setTranslateY(e.getTranslateY() + camDir.getY());
        }
        for(Spawn e : spawnList){
            e.setTranslateX(e.getTranslateX() - camDir.getX());
            e.setTranslateY(e.getTranslateY() + camDir.getY());
        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="movement">
        for (Entity e : entityList) {
            if (e.getMass() != 0) {
                if (e.getDir().getX() != 0) {
                    e.setX((e.getX() + e.getDir().getX()));
                }
                if (e.getDir().getY() != 0) {
                    e.setY((e.getY() + e.getDir().getY()));
                }
            }
        }
        for (Entity e : personList) {
            if (e.getMass() != 0) {
                if (e.getDir().getX() != 0) {
                    e.setX((e.getX() + e.getDir().getX()));
                }
                if (e.getDir().getY() != 0) {
                    e.setY((e.getY() + e.getDir().getY()));
                }
            }
        }
        for (Entity e : personList2) {
            if (e.getMass() != 0) {
                if (e.getDir().getX() != 0) {
                    e.setX((e.getX() + e.getDir().getX()));
                }
                if (e.getDir().getY() != 0) {
                    e.setY((e.getY() + e.getDir().getY()));
                }
            }
        }
        for (Entity e : enemyList) {
            if (e.getMass() != 0) {
                if (e.getDir().getX() != 0) {
                    e.setX((e.getX() + e.getDir().getX()));
                }
                if (e.getDir().getY() != 0) {
                    e.setY((e.getY() + e.getDir().getY()));
                }
            }
        }
        for (Entity e : keyList) {
            if (e.getMass() != 0) {
                if (e.getDir().getX() != 0) {
                    e.setX((e.getX() + e.getDir().getX()));
                }
                if (e.getDir().getY() != 0) {
                    e.setY((e.getY() + e.getDir().getY()));
                }
            }
        }
//</editor-fold>
        postTick();
    }
    
    private void liveCollision(){
        //<editor-fold defaultstate="collapsed" desc="live collision mode">
        for (Entity e : doorTargetList) {
            for (Entity d : doorList) {
                boolean[] c = e.intersects(d.getIv().getBoundsInParent());
                if((!c[4]) & d.isActive()){
                    for (Entity key : keyList) {
                        if((d.getUserData() == key.getUserData()) & !key.isActive()){
                            d.setVisible(false);
                            d.setActive(false);
                        }
                    }
                }
            }
        }
        for (Entity e : physList) {
            for(Entity e2 : physList){
                if((!e.getID().equals(e2.getID()))){
                    boolean[] c = e.intersects(e2.getIv().getBoundsInParent());
                    if(c[0]){
                        if(e.getDir().getY() < 0){
                            e.getDir().setY(0);
                        }
                    }
                    if(c[1]){
                        if(e.getDir().getY() > 0){
                            e.getDir().setY(0);
                        }
                    }
                    if(c[2]){
                        if(e.getDir().getX() < 0){
                            e.getDir().setX(0);
                        }
                    }
                    if(c[3]){
                        if(e.getDir().getX() > 0){
                            e.getDir().setX(0);
                        }
                    }
                    if(!c[4]){
                        collision(e, e2);
                    }
                }
            }
        }
        for (Entity e : physList) {
            for(Entity e2 : doorList){
                boolean intersect = true;
                boolean[] c = e.intersects(e2.getIv().getBoundsInParent());
                if(c[0]){
                    if(e.getDir().getY() < 0){
                        e.getDir().setY(0);
                    }
                }
                if(c[1]){
                    if(e.getDir().getY() > 0){
                        e.getDir().setY(0);
                    }
                }
                if(c[2]){
                    if(e.getDir().getX() < 0){
                        e.getDir().setX(0);
                    }
                }
                if(c[3]){
                    if(e.getDir().getX() > 0){
                        e.getDir().setX(0);
                    }
                }
                if(!c[4]){
                    collision(e, e2);
                }
            }
        }
        for(Trigger t : triggerList){
            for(Entity e : trigTargetList){
                if((!e.intersects(t.getBoundsInParent())[4]) & t.isEnabled()){
                    t.fire();
                }
            }
        }
//</editor-fold>
    }
    
    private void predictionCollision(){
        //<editor-fold defaultstate="collapsed" desc="predict collision">
        for (Entity e : doorTargetList) {
            for (Entity d : doorList) {
                if((predictCollision(e, d).getSide() > 0) & d.isActive()){
                    for (Entity key : keyList) {
                        if((d.getUserData() == key.getUserData()) & !key.isActive()){
                            d.setVisible(false);
                            d.setActive(false);
                        }
                    }
                }
            }
        }
        for (Entity e : physList) {
            for(Entity e2 : physList){
                if((!e.getID().equals(e2.getID()))){
                    CollisionBlock cb = predictCollision(e, e2);
                    switch(cb.getSide()){
                        case 1:{
                            e.getDir().setY(cb.getyDist());
                            collision(e, e2);
                            break;
                        }
                        case 2:{
                            e.getDir().setY(-cb.getyDist());
                            collision(e, e2);
                            break;
                        }
                        case 3:{
                            e.getDir().setX(cb.getxDist());
                            collision(e, e2);
                            break;
                        }
                        case 4:{
                            e.getDir().setX(-cb.getxDist());
                            collision(e, e2);
                            break;
                        }
                    }
                }
            }
        }
        for (Entity e : physList) {
            for(Entity e2 : doorList){
                CollisionBlock cb = predictCollision(e, e2);
                switch(cb.getSide()){
                    case 1:{
                        e.getDir().setY(cb.getyDist());
                        collision(e, e2);
                        break;
                    }
                    case 2:{
                        e.getDir().setY(-cb.getyDist());
                        collision(e, e2);
                        break;
                    }
                    case 3:{
                        e.getDir().setX(cb.getxDist());
                        collision(e, e2);
                        break;
                    }
                    case 4:{
                        e.getDir().setX(-cb.getxDist());
                        collision(e, e2);
                        break;
                    }
                }
            }
        }
        for(Trigger t : triggerList){
            for(Entity e : trigTargetList){
                if((!e.intersects(t.getBoundsInParent())[4]) & t.isEnabled()){
                    t.fire();
                }
            }
        }
//</editor-fold>
    }
    
    public CollisionBlock predictCollision(Entity source, Entity target){
        CollisionBlock cb = new CollisionBlock();
        cb.setSide(0);
        Vector v = source.getDir();
        int YAxis = 0;// 0 none, 1 up, 2 down
        int XAxis = 0;// 0 none, 1 left, 2 right
        double x = source.getIv().getTranslateX();
        double y = source.getIv().getTranslateY();
        
        if(v.getX() == 0){
            XAxis = 0;
        }else if(v.getX() < 0){
            XAxis = 1;
        }else{
            XAxis = 2;
        }
        if(v.getY() == 0){
            YAxis = 0;
        }else if(v.getY() < 0){
            YAxis = 1;
        }else{
            YAxis = 2;
        }
        
        if((XAxis == 0) & (YAxis == 0)){//no movement
            return cb;
        }
        if((XAxis == 1) & (YAxis == 0)){//straight left
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getWidth() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; -i > v.getX(); i++) {
                    double py = y + i2;
                    double px = x - i;
                    double bx1 = target.getR4().getTranslateX() - (target.getR4().getWidth() / 2);
                    double by1 = target.getR4().getTranslateY() - (target.getR4().getHeight() / 2);
                    double bx2 = target.getR4().getTranslateX() + (target.getR4().getWidth() / 2);
                    double by2 = target.getR4().getTranslateY() + (target.getR4().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(4);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 2) & (YAxis == 0)){//straight right
            x = source.getIv().getTranslateX() + (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; i < v.getX(); i++) {
                    double py = y + i2;
                    double px = x + i;
                    double bx1 = target.getR3().getTranslateX() - (target.getR3().getWidth() / 2);
                    double by1 = target.getR3().getTranslateY() - (target.getR3().getHeight() / 2);
                    double bx2 = target.getR3().getTranslateX() + (target.getR3().getWidth() / 2);
                    double by2 = target.getR3().getTranslateY() + (target.getR3().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(3);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 0) & (YAxis == 1)){//straight up
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; -i > v.getY(); i++) {
                    double py = y - i;
                    double px = x + i2;
                    double bx1 = target.getR2().getTranslateX() - (target.getR2().getWidth() / 2);
                    double by1 = target.getR2().getTranslateY() - (target.getR2().getHeight() / 2);
                    double bx2 = target.getR2().getTranslateX() + (target.getR2().getWidth() / 2);
                    double by2 = target.getR2().getTranslateY() + (target.getR2().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(2);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 1) & (YAxis == 1)){//up left
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; -i > v.getY(); i++) {
                    double py = y - i;
                    double px = x + i2;
                    double bx1 = target.getR2().getTranslateX() - (target.getR2().getWidth() / 2);
                    double by1 = target.getR2().getTranslateY() - (target.getR2().getHeight() / 2);
                    double bx2 = target.getR2().getTranslateX() + (target.getR2().getWidth() / 2);
                    double by2 = target.getR2().getTranslateY() + (target.getR2().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(2);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getWidth() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; -i > v.getX(); i++) {
                    double py = y + i2;
                    double px = x - i;
                    double bx1 = target.getR4().getTranslateX() - (target.getR4().getWidth() / 2);
                    double by1 = target.getR4().getTranslateY() - (target.getR4().getHeight() / 2);
                    double bx2 = target.getR4().getTranslateX() + (target.getR4().getWidth() / 2);
                    double by2 = target.getR4().getTranslateY() + (target.getR4().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(4);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 2) & (YAxis == 1)){//up right
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; -i > v.getY(); i++) {
                    double py = y - i;
                    double px = x + i2;
                    double bx1 = target.getR2().getTranslateX() - (target.getR2().getWidth() / 2);
                    double by1 = target.getR2().getTranslateY() - (target.getR2().getHeight() / 2);
                    double bx2 = target.getR2().getTranslateX() + (target.getR2().getWidth() / 2);
                    double by2 = target.getR2().getTranslateY() + (target.getR2().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(2);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
            x = source.getIv().getTranslateX() + (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; i < v.getX(); i++) {
                    double py = y + i2;
                    double px = x + i;
                    double bx1 = target.getR3().getTranslateX() - (target.getR3().getWidth() / 2);
                    double by1 = target.getR3().getTranslateY() - (target.getR3().getHeight() / 2);
                    double bx2 = target.getR3().getTranslateX() + (target.getR3().getWidth() / 2);
                    double by2 = target.getR3().getTranslateY() + (target.getR3().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(3);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 0) & (YAxis == 2)){//straight down
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() + (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; i < v.getY(); i++) {
                    double py = y + i;
                    double px = x + i2;
                    double bx1 = target.getR1().getTranslateX() - (target.getR1().getWidth() / 2);
                    double by1 = target.getR1().getTranslateY() - (target.getR1().getHeight() / 2);
                    double bx2 = target.getR1().getTranslateX() + (target.getR1().getWidth() / 2);
                    double by2 = target.getR1().getTranslateY() + (target.getR1().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(1);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 1) & (YAxis == 2)){//down left
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() + (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; i < v.getY(); i++) {
                    double py = y + i;
                    double px = x + i2;
                    double bx1 = target.getR1().getTranslateX() - (target.getR1().getWidth() / 2);
                    double by1 = target.getR1().getTranslateY() - (target.getR1().getHeight() / 2);
                    double bx2 = target.getR1().getTranslateX() + (target.getR1().getWidth() / 2);
                    double by2 = target.getR1().getTranslateY() + (target.getR1().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(1);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getWidth() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; -i > v.getX(); i++) {
                    double py = y + i2;
                    double px = x - i;
                    double bx1 = target.getR4().getTranslateX() - (target.getR4().getWidth() / 2);
                    double by1 = target.getR4().getTranslateY() - (target.getR4().getHeight() / 2);
                    double bx2 = target.getR4().getTranslateX() + (target.getR4().getWidth() / 2);
                    double by2 = target.getR4().getTranslateY() + (target.getR4().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(4);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        if((XAxis == 2) & (YAxis == 2)){//down right
            x = source.getIv().getTranslateX() - (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() + (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getWidth(); i2++) {
                for (int i = 0; i < v.getY(); i++) {
                    double py = y + i;
                    double px = x + i2;
                    double bx1 = target.getR1().getTranslateX() - (target.getR1().getWidth() / 2);
                    double by1 = target.getR1().getTranslateY() - (target.getR1().getHeight() / 2);
                    double bx2 = target.getR1().getTranslateX() + (target.getR1().getWidth() / 2);
                    double by2 = target.getR1().getTranslateY() + (target.getR1().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(1);
                        cb.setyDist(i);
                        break;
                    }
                }
            }
            x = source.getIv().getTranslateX() + (source.getIv().getViewport().getHeight() / 2);
            y = source.getIv().getTranslateY() - (source.getIv().getViewport().getHeight() / 2);
            for (int i2 = 0; i2 < source.getIv().getViewport().getHeight(); i2++) {
                for (int i = 0; i < v.getX(); i++) {
                    double py = y + i2;
                    double px = x + i;
                    double bx1 = target.getR3().getTranslateX() - (target.getR3().getWidth() / 2);
                    double by1 = target.getR3().getTranslateY() - (target.getR3().getHeight() / 2);
                    double bx2 = target.getR3().getTranslateX() + (target.getR3().getWidth() / 2);
                    double by2 = target.getR3().getTranslateY() + (target.getR3().getHeight() / 2);
                    if (((px > bx1) & (px < bx2) & (py > by1) & (py < by2))) {
                        cb.setSide(3);
                        cb.setxDist(i);
                        break;
                    }
                }
            }
        }
        return cb;
    }
}