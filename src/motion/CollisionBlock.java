/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motion;

/**
 *
 * @author James
 */
public class CollisionBlock {
    private int xDist;
    private int yDist;
    private int axis;//0 = none, 1 = top, 2 = bottom, 3 = left, 4 = right

    public int getxDist() {
        return xDist;
    }

    public void setxDist(int xDist) {
        this.xDist = xDist;
    }

    public int getyDist() {
        return yDist;
    }

    public void setyDist(int yDist) {
        this.yDist = yDist;
    }

    public int getSide() {
        return axis;
    }

    public void setSide(int side) {
        this.axis = side;
    }
    
}
