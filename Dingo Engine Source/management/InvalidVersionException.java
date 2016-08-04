/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

/**
 *
 * @author James
 */
public class InvalidVersionException extends Exception {

    /**
     * Creates a new instance of
     * <code>InvalidVersionException</code> without detail message.
     */
    public InvalidVersionException() {
    }

    /**
     * Constructs an instance of
     * <code>InvalidVersionException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidVersionException(String msg) {
        super(msg);
    }
}
