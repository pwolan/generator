package org.example.classes;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapPositionObserver;


public class Grass extends AbstractMapElement{

    public Grass(Vector2d position) {
        super(position);
    }

    public Grass(Vector2d position, IMapPositionObserver observer) {
        super(position, observer);
    }

    @Override
    public Node getGuiElement() {
        Color grassColor = Color.rgb(56, 195, 28);

        Rectangle rectangle = new Rectangle(0, 0, 40, 40);
        rectangle.setFill(grassColor);
        rectangle.viewOrderProperty().setValue(1);
        return rectangle;
    }


}
