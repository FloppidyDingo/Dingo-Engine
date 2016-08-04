/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editors;

import Build.Skin;
import Tools.ImageObject;
import Tools.Rectangle;
import Tools.SkinCreator;
import dingo.Editor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author James
 */
public class SkinEditor extends javax.swing.JPanel {
    private Skin skin;
    private Editor editor;
    private SkinCreator creator;
    private ImageObject def;

    /**
     * Creates new form SkinEditor
     * @param e
     */
    public SkinEditor(Editor e) {
        editor = e;
        creator = new SkinCreator();
        initComponents();
        ID.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeActionPerformed(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeActionPerformed(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        images = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        defs = new javax.swing.JComboBox();
        view = new Tools.ImagePanel();
        frame = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("ID:");

        images.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                imagesItemStateChanged(evt);
            }
        });

        jLabel2.setText("Image:");

        jLabel3.setText("SkinDef:");

        defs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                defsItemStateChanged(evt);
            }
        });

        view.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view);
        view.setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        frame.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        frame.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                framePropertyChange(evt);
            }
        });

        jLabel4.setText("Frame:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defs, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(defs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imagesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_imagesItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            ImageObject item = (ImageObject)evt.getItem();
            skin.setImg(item);
        }
        updateView();
    }//GEN-LAST:event_imagesItemStateChanged

    private void defsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_defsItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            def = (ImageObject)evt.getItem();
            Skin ski = creator.generateSkin(new File(editor.getProjectsFolder() + "/resources/images/" + def.getID()));
            ski.setID(ID.getText());
            skin.setID(ski.getID());
            skin.setURL(ski.getURL());
            skin.setRec(ski.getRec());
            SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, skin.getRec().size() - 1, 1);
            frame.setModel(model1);
            skin.setBuffer((ImageObject)evt.getItem());
        }
        updateView();
    }//GEN-LAST:event_defsItemStateChanged

    private void framePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_framePropertyChange
        // TODO add your handling code here:
        updateView();
    }//GEN-LAST:event_framePropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JComboBox defs;
    private javax.swing.JSpinner frame;
    private javax.swing.JComboBox images;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private Tools.ImagePanel view;
    // End of variables declaration//GEN-END:variables

    public void setSkin(Skin sk) {
        skin = sk;
        ID.setText(skin.getID());
        images.removeAllItems();
        defs.removeAllItems();
        for (ItemListener itemListener : images.getItemListeners()) {
            images.removeItemListener(itemListener);
        }
        for (ItemListener itemListener : defs.getItemListeners()) {
            defs.removeItemListener(itemListener);
        }
        Enumeration e = editor.getImages().children();
        while(true){
            try{
                ImageObject io = ((ImageObject)((DefaultMutableTreeNode)e.nextElement()).getUserObject());
                if(null != io.getType())switch (io.getType()) {
                    case "image":{
                        images.addItem(io);
                        break;
                    }
                    case "definition":{
                        defs.addItem(io);
                        break;
                    }
                }
            }catch(NoSuchElementException ex){
                break;
            }
        }
        try {
            if (skin.getImg() != null) {
                images.setSelectedItem(skin.getImg());
            } else {
                skin.setImg((ImageObject) images.getSelectedItem());
            }
            if (skin.getBuffer() != null) {
                defs.setSelectedItem(skin.getBuffer());
            } else {
                Skin ski = creator.generateSkin(new File(editor.getProjectsFolder() + "/resources/images/" + ((ImageObject) defs.getSelectedItem()).getID()));
                skin.setRec(ski.getRec());
                skin.setBuffer((ImageObject) defs.getSelectedItem());
            }
        } catch (NullPointerException ex) {}
        images.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                imagesItemStateChanged(e);
            }
        });
        defs.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                defsItemStateChanged(e);
            }
        });
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, skin.getRec().size() - 1, 1);
        frame.setModel(model1);
        updateView();
    }
    
    private void updateView(){
        try {
            view.setImage(skin.getImg().getImg());
            Rectangle rec = skin.getRec().get((Integer) frame.getModel().getValue());
            view.crop(rec.getX(), rec.getY(), rec.getW(), rec.getH());
        } catch (Exception e) {}
    }
    
    public void changeActionPerformed(DocumentEvent evt){
        try {
            skin.setID(ID.getText());
            editor.getTreeModel().reload();
            editor.getAssets().expandRow(3);
        } catch (NullPointerException e) {}
    }
}
