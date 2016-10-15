/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author James
 */
public class ComboItem {
    private Object value;
    private String label;
    private String type;

    public ComboItem(Object value, String label, String type) {
        this.value = value;
        this.label = label;
        this.type = type;
    }

    public Object getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}