/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author James
 */
public class ErrorBox {
    private final Stage stage;
    private final StackPane stack;
    private final Scene scene;
    private final Label label;

    public ErrorBox() {
        stage = new Stage();
        stack = new StackPane();
        scene = new Scene(stack, 200, 150);
        label = new Label();
        stage.setScene(scene);
        stage.setResizable(false);
        
        stack.getChildren().add(label);
    }
    
    public void throwError(String message){
        label.setText(message);
        stage.show();
    }
}
