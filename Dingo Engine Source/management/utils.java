

package management;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import objects.Entity;
import objects.Skin;

/**
 *A set of utilities.
 * @author Jaca
 */
public class Utils {
    private static final Random r = new Random();

    /**
     *Used as a reference that an object is above or to the left of another.
     */
    public static final int ABOVE_LEFT = 1;

    /**
     *Used as a reference that an object is below or to the right of another.
     */
    public static final int BELOW_RIGHT = 2;

    /**
     *Used as a reference that an object is lined up with another.
     */
    public static final int CENTERED = 3;
    
    /**
     *Calculates the distance between two Nodes
     * @param i1
     * @param i2
     * @return
     */
    public static double getRange(Node i1, Node i2){
        double x1 = i1.getTranslateX();
        double x2 = i2.getTranslateX();
        double y1 = i1.getTranslateY();
        double y2 = i2.getTranslateY();
        return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
    }
    
    /**
     *Gets the closest node from a list in reference to a target.
     * @param ol The list of nodes.
     * @param target The target.
     * @return The closest node to the target out of the nodes in ol.
     */
    public static Node getClosest(ObservableList<Node> ol, Node target){
        Node n = ol.get(0);
        for (Node node : ol) {
            if(Utils.getRange(node, target) < Utils.getRange(n, target)){
                n = node;
            }
        }
        return n;
    }
    
    /**
     *Returns a random integer between min and max.
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     *Places one node on top of another.
     * @param n
     * @param destination
     */
    public static void goTo(Node n, Node destination){
        n.setTranslateX(destination.getTranslateX());
        n.setTranslateY(destination.getTranslateY());
    }
    
    /**
     *Gets the closest Entity from a list in reference to a target.
     * @param ol The list of Entities.
     * @param target The target.
     * @return The closest Entity to the target out of the Entities in ol.
     */
    public static Entity getClosest(ObservableList<Entity> ol, Entity target){
        Entity n = ol.get(0);
        for (Entity node : ol) {
            if(Utils.getRange(node.getIv(), target.getIv()) < Utils.getRange(n.getIv(), target.getIv())){
                n = node;
            }
        }
        return n;
    }
    
    /**
     *Creates an Entity object from the specified ImageView
     * @param iv
     * @return
     */
    public static Entity generateEntity(ImageView iv){
        Skin s = new Skin();
        s.setBaseImage(iv);
        return new Entity(s);
    }
    
    /**
     * Returns the relative position between two Entities. Returned as an array of two
     * values, the first one being position along the X axis, the second along the Y axis.
     * @param e1
     * @param e2
     * @return The relative position of e1 in relation to e2.
     */
    public static int[] getRelative(Entity e1, Entity e2){
        int[] result = new int[2];
        if(e1.getX() > e2.getX()){
            result[0] = 2;
        }else if (e1.getX() < e2.getX()){
            result[0] = 1;
        }else {
            result[0] = 3;
        }
        if(e1.getY() > e2.getY()){
            result[1] = 2;
        }else if (e1.getY() < e2.getY()){
            result[1] = 1;
        }else {
            result[1] = 3;
        }
        return result;
    }
    
    public static ImageView generateImage(String path) throws MalformedURLException{
        return new ImageView(new Image(new File(path).toURI().toURL().toString()));
    }
}