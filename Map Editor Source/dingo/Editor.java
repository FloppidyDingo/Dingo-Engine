/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import Build.*;
import Editors.*;
import Tools.ImageObject;
import Tools.Rectangle;
import Tools.World;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author James
 */
public class Editor extends javax.swing.JFrame {
    
    private String selectedNode;
    private String projectFolder;
    private final DefaultMutableTreeNode triggers;
    private final DefaultMutableTreeNode spawns;
    private final DefaultMutableTreeNode people2;
    private final DefaultMutableTreeNode people;
    private final DefaultMutableTreeNode keys;
    private final DefaultMutableTreeNode enemies;
    private final DefaultMutableTreeNode doors;
    private final DefaultMutableTreeNode objects;
    private final DefaultMutableTreeNode entities;
    private final DefaultMutableTreeNode skins;
    private final DefaultMutableTreeNode animations;
    private final DefaultMutableTreeNode images;
    private final DefaultMutableTreeNode baseNode;
    private int skinCount;
    private int entityCount;
    private int spawnCount;
    private int peopleCount;
    private int people2Count;
    private int triggerCount;
    private int keyCount;
    private int enemyCount;
    private int doorCount;
    private int objectCount;
    private int animationCount;
    private final DefaultTreeModel treeModel;
    private final SkinEditor skinEditor;
    private final ImageEditor imageEditor;
    private String imageBase;
    private String lastProject;
    private File projectFile;
    private final EntityEditor entityEditor;
    private int MX;
    private int MY;

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
        this.setVisible(false);
        
        baseNode = new DefaultMutableTreeNode("Project");
        animations = new DefaultMutableTreeNode("Animations");
        skins = new DefaultMutableTreeNode("Skins");
        entities = new DefaultMutableTreeNode("Entities");
        objects = new DefaultMutableTreeNode("Objects");
        doors = new DefaultMutableTreeNode("Doors");
        enemies = new DefaultMutableTreeNode("Enemies");
        keys = new DefaultMutableTreeNode("Keys");
        people = new DefaultMutableTreeNode("Moving AI");
        people2 = new DefaultMutableTreeNode("Still AI");
        spawns = new DefaultMutableTreeNode("Spawners");
        triggers = new DefaultMutableTreeNode("Triggers");
        images = new DefaultMutableTreeNode("Images");
        treeModel = (DefaultTreeModel) Assets.getModel();
        treeModel.setRoot(baseNode);
        baseNode.add(people);
        baseNode.add(animations);
        baseNode.add(skins);
        baseNode.add(images);
        baseNode.add(entities);
        baseNode.add(objects);
        baseNode.add(doors);
        baseNode.add(enemies);
        baseNode.add(keys);
        baseNode.add(people2);
        baseNode.add(spawns);
        baseNode.add(triggers);
        
        Assets.getSelectionModel().addTreeSelectionListener((TreeSelectionEvent e) -> {
            treeSelectActionPerformed(e);
        });
        
        world.setE(this);
        
        skinEditor = new SkinEditor(this);
        imageEditor = new ImageEditor(this);
        entityEditor = new EntityEditor(this);
        
        try {
            File config  = new File("config.ini");
            if(!config.exists()){
                config.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(config))) {
                    bw.write("null");
                }
            }
            try (BufferedReader br = new BufferedReader(new FileReader(config))) {
                String s = br.readLine();
                File f = new File(s);
                if((!"null".equals(s)) & f.exists()){
                    lastProject = s;
                    recent.setText("Open Recent: " + lastProject);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        world = new Tools.World();
        editorPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        addAsset = new javax.swing.JButton();
        removeAsset = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Assets = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newProject = new javax.swing.JMenuItem();
        openProject = new javax.swing.JMenuItem();
        recent = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        export = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        world.setBackground(new java.awt.Color(0, 0, 0));
        world.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                worldMouseDragged(evt);
            }
        });
        world.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                worldMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout worldLayout = new javax.swing.GroupLayout(world);
        world.setLayout(worldLayout);
        worldLayout.setHorizontalGroup(
            worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        worldLayout.setVerticalGroup(
            worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        editorPanel.setBackground(new java.awt.Color(128, 128, 128));

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        editorPanelLayout.setVerticalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        jLabel1.setText("Project Assets:");

        addAsset.setText("Add");
        addAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAssetActionPerformed(evt);
            }
        });

        removeAsset.setText("Delete");
        removeAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAssetActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(Assets);

        jMenu1.setText("File");

        newProject.setText("New Project");
        newProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectActionPerformed(evt);
            }
        });
        jMenu1.add(newProject);

        openProject.setText("Open Project");
        openProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectActionPerformed(evt);
            }
        });
        jMenu1.add(openProject);

        recent.setText("Open Recent:");
        recent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recentActionPerformed(evt);
            }
        });
        jMenu1.add(recent);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);

        export.setText("Export");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jMenu1.add(export);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(world, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addAsset)
                        .addGap(18, 18, 18)
                        .addComponent(removeAsset))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(world, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeAsset)
                    .addComponent(addAsset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAssetActionPerformed
        // TODO add your handling code here:
        if(selectedNode == null){
            return;
        }
        switch(selectedNode){
            case "Skins":{
                Skin sk = new Skin();
                sk.setID("skin" + skinCount);
                skinCount ++;
                skins.add(new DefaultMutableTreeNode(sk));
                editorPanel.removeAll();
                editorPanel.add(skinEditor);
                skinEditor.setSize(editorPanel.getSize());
                skinEditor.setSkin(sk);
                skinEditor.revalidate();
                break;
            }
            case "Images":{
                ImageObject io = new ImageObject(null, null, null, 0, 0);
                imageEditor.setImage(io);
                editorPanel.removeAll();
                editorPanel.add(imageEditor);
                imageEditor.setSize(editorPanel.getSize());
                imageEditor.revalidate();
                break;
            }
            case "Entities":{
                Entity e = new Entity();
                e.setID("entity" + entityCount);
                entityCount ++;
                entities.add(new DefaultMutableTreeNode(e));
                editorPanel.removeAll();
                editorPanel.add(entityEditor);
                entityEditor.setSize(editorPanel.getSize());
                entityEditor.setEntity(e);
                world.addObj(e);
                entityEditor.revalidate();
                break;
            }
        }
        treeModel.reload();
        editorPanel.revalidate();
        editorPanel.repaint();
    }//GEN-LAST:event_addAssetActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void newProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectActionPerformed
        // TODO add your handling code here:
        ProjectWizard PW = new ProjectWizard();
        PW.start(this);
    }//GEN-LAST:event_newProjectActionPerformed

    private void openProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectActionPerformed
        // TODO add your handling code here:
        JFileChooser FC = new JFileChooser();
        FC.setName("Choose a project");
        FileFilter imageFilter = new FileNameExtensionFilter("Dingo Map Projects \".inx\"", "inx");
        FC.setFileFilter(imageFilter);
        if(FC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            this.openProject(FC.getSelectedFile());
        }
    }//GEN-LAST:event_openProjectActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        this.saveProject();
    }//GEN-LAST:event_saveActionPerformed

    private void worldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_worldMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (world.isUnlocked()) {
                world.lock();
            } else {
                world.setTrack(world.getCoord(evt.getX(), evt.getY()));
            }
        }
    }//GEN-LAST:event_worldMouseClicked

    private void recentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recentActionPerformed
        // TODO add your handling code here:
        openProject(new File(lastProject));
    }//GEN-LAST:event_recentActionPerformed

    private void removeAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAssetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeAssetActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        generateMap();
    }//GEN-LAST:event_exportActionPerformed

    private void worldMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_worldMouseDragged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_worldMouseDragged

    private void treeSelectActionPerformed(TreeSelectionEvent e){
        try {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) Assets.getLastSelectedPathComponent();
            if(n.getUserObject() instanceof String){
                selectedNode = (String) n.getUserObject();
            }else{
                editMode((DefaultMutableTreeNode) Assets.getLastSelectedPathComponent());
            }
        } catch (NullPointerException ex) {}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Editor().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree Assets;
    private javax.swing.JButton addAsset;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JMenuItem export;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem newProject;
    private javax.swing.JMenuItem openProject;
    private javax.swing.JMenuItem recent;
    private javax.swing.JButton removeAsset;
    private javax.swing.JMenuItem save;
    private Tools.World world;
    // End of variables declaration//GEN-END:variables

    public void setDispCoords(int i, int i0) {
        entityEditor.setCoords(i, i0);
    }
    
    //sets the current project to what is defined by the URL (must be a .inx file)
    public void openProject(File URL){
        images.removeAllChildren();
        animations.removeAllChildren();
        skins.removeAllChildren();
        entities.removeAllChildren();
        objects.removeAllChildren();
        doors.removeAllChildren();
        enemies.removeAllChildren();
        keys.removeAllChildren();
        people.removeAllChildren();
        people2.removeAllChildren();
        spawns.removeAllChildren();
        triggers.removeAllChildren();
        projectFolder = URL.getParent();
        projectFile = URL;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(URL))) {
                this.setTitle(br.readLine());
                String s = br.readLine();
                if (!"null".equals(s)) {
                    imageBase = s;
                }
                skinCount = Integer.parseInt(br.readLine());
                entityCount = Integer.parseInt(br.readLine());
                spawnCount = Integer.parseInt(br.readLine());
                peopleCount = Integer.parseInt(br.readLine());
                people2Count = Integer.parseInt(br.readLine());
                triggerCount = Integer.parseInt(br.readLine());
                keyCount = Integer.parseInt(br.readLine());
                enemyCount = Integer.parseInt(br.readLine());
                doorCount = Integer.parseInt(br.readLine());
                objectCount = Integer.parseInt(br.readLine());
                animationCount = Integer.parseInt(br.readLine());
                s = br.readLine();
                while(s != null){
                    switch (s) {
                        case "#IMAGE": {
                            s = br.readLine();
                            ImageObject io = new ImageObject(null, s, ImageIO.read(new File(projectFolder + "/resources/images/" + s)), 0, 0);
                            s = br.readLine();
                            io.setType(s);
                            images.add(new DefaultMutableTreeNode(io));
                            break;
                        }
                        case "#SKIN":{
                            Skin sk = new Skin();
                            sk.setID(br.readLine());
                            s = br.readLine();
                            Enumeration en = images.children();
                            while (en.hasMoreElements()) {
                                ImageObject io = (ImageObject)((DefaultMutableTreeNode)en.nextElement()).getUserObject();
                                if(io.getID() == null ? s == null : io.getID().equals(s)){
                                    sk.setImg(io);
                                    break;
                                }
                            }
                            s = br.readLine();
                            en = images.children();
                            while (en.hasMoreElements()) {
                                ImageObject io = (ImageObject)((DefaultMutableTreeNode)en.nextElement()).getUserObject();
                                if(io.getID() == null ? s == null : io.getID().equals(s)){
                                    sk.setBuffer(io);
                                    break;
                                }
                            }
                            s = br.readLine();
                            while(!"#END".equals(s)){
                                Rectangle r = new Rectangle();
                                r.setW(Integer.parseInt(s));
                                r.setH(Integer.parseInt(br.readLine()));
                                r.setX(Integer.parseInt(br.readLine()));
                                r.setY(Integer.parseInt(br.readLine()));
                                sk.getRec().add(r);
                                s = br.readLine();
                            }
                            skins.add(new DefaultMutableTreeNode(sk));
                            break;
                        }
                        case "#ENTITY":{
                            Entity ent = new Entity();
                            ent.setID(br.readLine());
                            s = br.readLine();
                            Enumeration en = skins.children();
                            while (en.hasMoreElements()) {
                                Skin ski = (Skin)((DefaultMutableTreeNode)en.nextElement()).getUserObject();
                                if(ski.getID() == null ? s == null : ski.getID().equals(s)){
                                    ent.setSkin(ski);
                                    break;
                                }
                            }
                            ent.setActive(Boolean.parseBoolean(br.readLine()));
                            ent.setDoor(Boolean.parseBoolean(br.readLine()));
                            ent.setMass(Integer.parseInt(br.readLine()));
                            ent.setUd(Integer.parseInt(br.readLine()));
                            ent.setX(Integer.parseInt(br.readLine()));
                            ent.setY(Integer.parseInt(br.readLine()));
                            entities.add(new DefaultMutableTreeNode(ent));
                            world.addLockedObj(ent);
                            break;
                        }
                    }
                    s = br.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error finding resource");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
        treeModel.reload();
    }
    
    public String getProjectsFolder() {
        return projectFolder;
    }
    
    private void saveProject() {
        lastProject = projectFile.getAbsolutePath();
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("config.ini")))) {
                bw.write(lastProject);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(projectFile))) {
                bw.write(this.getTitle());
                bw.newLine();
                bw.write(imageBase);
                bw.newLine();
                bw.write(Integer.toString(skinCount));
                bw.newLine();
                bw.write(Integer.toString(entityCount));
                bw.newLine();
                bw.write(Integer.toString(spawnCount));
                bw.newLine();
                bw.write(Integer.toString(peopleCount));
                bw.newLine();
                bw.write(Integer.toString(people2Count));
                bw.newLine();
                bw.write(Integer.toString(triggerCount));
                bw.newLine();
                bw.write(Integer.toString(keyCount));
                bw.newLine();
                bw.write(Integer.toString(enemyCount));
                bw.newLine();
                bw.write(Integer.toString(doorCount));
                bw.newLine();
                bw.write(Integer.toString(objectCount));
                bw.newLine();
                bw.write(Integer.toString(animationCount));
                bw.newLine();
                Enumeration en = images.children();
                while(en.hasMoreElements()){
                    ImageObject io = (ImageObject) ((DefaultMutableTreeNode) en.nextElement()).getUserObject();
                    bw.write("#IMAGE");//Image header
                    bw.newLine();
                    bw.write(io.getID());//image file
                    bw.newLine();
                    bw.write(io.getType());//image type
                    bw.newLine();
                }
                en = skins.children();
                while (en.hasMoreElements()) {
                    Skin skin = (Skin) ((DefaultMutableTreeNode) en.nextElement()).getUserObject();
                    bw.write("#SKIN");//skin header
                    bw.newLine();
                    bw.write(skin.getID());//skin ID
                    bw.newLine();
                    bw.write(skin.getImg().getID());//ImageObject ID
                    bw.newLine();
                    bw.write(skin.getBuffer().getID());//Buffer ID
                    bw.newLine();
                    for (Rectangle rec : skin.getRec()) {//Rectangle lists
                        bw.write(Integer.toString(rec.getW()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getH()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getX()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getY()));
                        bw.newLine();
                    }
                    bw.write("#END");
                    bw.newLine();
                }
                en = entities.children();
                while (en.hasMoreElements()) {
                    Entity ent = (Entity)((DefaultMutableTreeNode) en.nextElement()).getUserObject();
                    bw.write("#ENTITY");//entity header
                    bw.newLine();
                    bw.write(ent.getID());//entity ID
                    bw.newLine();
                    bw.write(ent.getSkin().getID());//skin ID
                    bw.newLine();
                    bw.write(Boolean.toString(ent.getActive()));//is entity active?
                    bw.newLine();
                    bw.write(Boolean.toString(ent.getDoor()));//is entity a door target?
                    bw.newLine();
                    bw.write(Integer.toString(ent.getMass()));//mass
                    bw.newLine();
                    bw.write(Integer.toString(ent.getUd()));//user data
                    bw.newLine();
                    bw.write(Integer.toString(ent.getX()));//X coordinate
                    bw.newLine();
                    bw.write(Integer.toString(ent.getY()));//Y coordinate
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultMutableTreeNode getTriggers() {
        return triggers;
    }
    
    public DefaultMutableTreeNode getSpawns() {
        return spawns;
    }
    
    public DefaultMutableTreeNode getPeople2() {
        return people2;
    }
    
    public DefaultMutableTreeNode getPeople() {
        return people;
    }
    
    public DefaultMutableTreeNode getKeys() {
        return keys;
    }
    
    public DefaultMutableTreeNode getEnemies() {
        return enemies;
    }
    
    public DefaultMutableTreeNode getDoors() {
        return doors;
    }

    public DefaultMutableTreeNode getObjects() {
        return objects;
    }

    public DefaultMutableTreeNode getEntities() {
        return entities;
    }

    public DefaultMutableTreeNode getSkins() {
        return skins;
    }

    public DefaultMutableTreeNode getAnimations() {
        return animations;
    }

    public DefaultMutableTreeNode getImages() {
        return images;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    private void editMode(DefaultMutableTreeNode node) {
        if(node.getUserObject() instanceof ImageObject){
            imageEditor.setImage((ImageObject)node.getUserObject());
            editorPanel.removeAll();
            editorPanel.add(imageEditor);
            imageEditor.setSize(editorPanel.getSize());
            imageEditor.revalidate();
        }else if (node.getUserObject() instanceof Skin){
            editorPanel.removeAll();
            editorPanel.add(skinEditor);
            skinEditor.setSize(editorPanel.getSize());
            skinEditor.setSkin((Skin)node.getUserObject());
            skinEditor.revalidate();
        }else if (node.getUserObject() instanceof Entity){
            editorPanel.removeAll();
            editorPanel.add(entityEditor);
            entityEditor.setSize(editorPanel.getSize());
            entityEditor.setEntity((Entity)node.getUserObject());
            entityEditor.revalidate();
            Entity ent = (Entity)node.getUserObject();
            BufferedImage base = ent.getSkin().getImg().getImg();
            world.setCamX(ent.getX() - (world.getWidth() / 2) + base.getWidth());
            world.setCamY(ent.getY() - (world.getHeight() / 2) + base.getHeight());
            world.select(ent);
        }
        editorPanel.repaint();
    }

    public String getImageBase() {
        return imageBase;
    }

    public void setImageBase(String imageBase) {
        this.imageBase = imageBase;
    }

    public JTree getAssets() {
        return Assets;
    }

    public World getWorld() {
        return world;
    }

    private void generateMap(){
        try {
            File output = new File(projectFolder + "/" + this.getTitle() + ".map");
            if (!output.exists()) {
                output.createNewFile();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
                bw.write("1");
                bw.newLine();
                Enumeration en = skins.children();
                while (en.hasMoreElements()) {
                    Skin skin = (Skin)((DefaultMutableTreeNode) en.nextElement()).getUserObject();
                    bw.write("skin");
                    bw.newLine();
                    bw.write(skin.getID());
                    bw.newLine();
                    bw.write(skin.getImg().getID());
                    bw.newLine();
                    for (Rectangle rec : skin.getRec()) {
                        bw.write(Integer.toString(rec.getX()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getY()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getX() + rec.getW()));
                        bw.newLine();
                        bw.write(Integer.toString(rec.getY() + rec.getH()));
                        bw.newLine();
                    }
                    bw.write("end");
                    bw.newLine();
                }
                en = entities.children();
                while (en.hasMoreElements()) {
                    Entity ent = (Entity)((DefaultMutableTreeNode) en.nextElement()).getUserObject();
                    Rectangle rec = ent.getSkin().getRec().get(0);
                    bw.write("entity");
                    bw.newLine();
                    bw.write(Integer.toString(ent.getX() + (rec.getW() / 2) - (world.getWidth() / 2)));
                    bw.newLine();
                    bw.write(Integer.toString(ent.getY() + (rec.getH() / 2) - (world.getHeight() / 2)));
                    bw.newLine();
                    bw.write(ent.getSkin().getID());
                    bw.newLine();
                    bw.write(ent.getID());
                    bw.newLine();
                    bw.write(Integer.toString(ent.getUd()));
                    bw.newLine();
                    bw.write(Integer.toString(ent.getMass()));
                    bw.newLine();
                    bw.write(Boolean.toString(ent.getActive()));
                    bw.newLine();
                    bw.write(Boolean.toString(ent.getDoor()));
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}