/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media;

/**
 *
 * @author James
 */
public class Connector {
    private int[] data;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }
    
}
