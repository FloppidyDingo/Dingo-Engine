/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editors;

import Tools.ImageObject;
import dingo.Editor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static java.nio.file.StandardCopyOption.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author James
 */
public class ImageEditor extends javax.swing.JPanel {
    private Editor editor;
    private ImageObject imo;
    private File previousFile;
    private File imageFile;

    public ImageEditor(Editor aThis) {
        initComponents();
        editor = aThis;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        image = new Tools.ImagePanel();
        Import = new javax.swing.JButton();
        name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        image.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout imageLayout = new javax.swing.GroupLayout(image);
        image.setLayout(imageLayout);
        imageLayout.setHorizontalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        imageLayout.setVerticalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        Import.setText("Import");
        Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportActionPerformed(evt);
            }
        });

        name.setEditable(false);

        jButton1.setText("Import as Definition");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Import)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addComponent(name))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Import)
                    .addComponent(jButton1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportActionPerformed
        // TODO add your handling code here:
        if(editor.getProjectsFolder() == null){
            return;
        }
        if(editor.getImageBase() != null){
            previousFile = new File(editor.getImageBase());
        }
        JFileChooser FC;
        if(previousFile == null){
            FC = new JFileChooser();
        }else{
            FC = new JFileChooser(previousFile);
        }
        FC.setName("Choose an Image");
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        FC.setFileFilter(imageFilter);
        if(FC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            try {
                File folder = new File(editor.getProjectsFolder() + "/resources/images/");
                if(!folder.exists()){
                    folder.mkdirs();
                }
                File destination = new File(editor.getProjectsFolder() + "/resources/images/" + FC.getSelectedFile().getName());
                destination.createNewFile();
                Files.copy(FC.getSelectedFile().toPath(), destination.toPath(), REPLACE_EXISTING);
                previousFile = new File(FC.getSelectedFile().getParent());
                editor.setImageBase(previousFile.getAbsolutePath());
                imageFile = destination;
                name.setText(FC.getSelectedFile().getName());
                imo.setType("image");
                updateImage();
            } catch (IOException ex) {
                Logger.getLogger(ImageEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ImportActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(editor.getProjectsFolder() == null){
            return;
        }
        JFileChooser FC;
        if(previousFile == null){
            FC = new JFileChooser();
        }else{
            FC = new JFileChooser(previousFile);
        }
        FC.setName("Choose an Image");
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        FC.setFileFilter(imageFilter);
        if(FC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            try {
                File folder = new File(editor.getProjectsFolder() + "/resources/images/");
                if(!folder.exists()){
                    folder.mkdirs();
                }
                File destination = new File(editor.getProjectsFolder() + "/resources/images/" + FC.getSelectedFile().getName());
                destination.createNewFile();
                Files.copy(FC.getSelectedFile().toPath(), destination.toPath(), REPLACE_EXISTING);
                previousFile = new File(FC.getSelectedFile().getParent());
                editor.setImageBase(previousFile.getParent());
                imageFile = destination;
                name.setText(FC.getSelectedFile().getName());
                imo.setType("definition");
                updateImage();
            } catch (IOException ex) {
                Logger.getLogger(ImageEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Import;
    private Tools.ImagePanel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables

    public void setImage(ImageObject io) {
        imo = io;
        loadImage();
    }
    
    private void updateImage(){
        try {
            BufferedImage bi = ImageIO.read(imageFile);
            image.setImage(bi);
            imo.setImg(bi);
            imo.setID(name.getText());
            ImageObject img = new ImageObject(null, imo.getID(), imo.getImg(), imo.getX(), imo.getY());
            img.setType(imo.getType());
            editor.getImages().add(new DefaultMutableTreeNode(img));
            editor.getTreeModel().reload();
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(ImageEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void loadImage() {
        image.setImage(imo.getImg());
        name.setText(imo.getID());
    }
}