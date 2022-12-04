package org.example.gui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.example.classes.Square;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;

public class GuiElementBox {
    private StackPane container;
    private Square square;
    private  Vector2d position;


    public GuiElementBox(Square square, Vector2d position) {
        Label l = new Label(position.toString());
        l.viewOrderProperty().setValue(10);
        StackPane container = new StackPane();

        BackgroundFill bgFill = new BackgroundFill(Color.rgb(167, 212, 100), CornerRadii.EMPTY, Insets.EMPTY);
        if(square.isPrefered()){
            bgFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
        }
        container.setBackground(new Background(bgFill));
        this.container = container;
        this.square = square;
        this.position = position;
        container.getChildren().add(l);
        this.renderElements();
    }

    private void renderElements(){
        this.container.getChildren().clear();
        for(IMapElement el : square.getElements()){
            Node n = el.getGuiElement();
            this.container.getChildren().add(n);
        }
    }

    void renderElement(GridPane rootElement) {

        rootElement.add(this.container, position.x(), position.y());
    }
}
