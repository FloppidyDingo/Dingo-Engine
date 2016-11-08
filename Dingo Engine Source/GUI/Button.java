/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author James
 */
public class Button extends ImageView{
    private Image idle;
    private Image hover;
    private Image select;
    private ButtonAction action;

    public Button(Image idle, Image hover, Image select) {
        this.idle = idle;
        this.hover = hover;
        this.select = select;
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(hover);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(idle);
            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(select);
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(hover);
                action.event(event.getButton());
            }
        });
        this.setImage(idle);
    }
    
    public Button() {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(hover);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(idle);
            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(select);
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setActiveImage(hover);
                if(action != null){
                    action.event(event.getButton());
                }
            }
        });
    }

    public Image getIdle() {
        return idle;
    }

    public void setIdle(Image idle) {
        this.idle = idle;
        this.setImage(idle);
    }

    public Image getHover() {
        return hover;
    }

    public void setHover(Image hover) {
        this.hover = hover;
    }

    public Image getSelect() {
        return select;
    }

    public void setSelect(Image select) {
        this.select = select;
    }
    
    public void setActiveImage(Image i){
        this.setImage(i);
    }
    
    public void setOnAction(ButtonAction event){
        this.action = event;
    }
}
