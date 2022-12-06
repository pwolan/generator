package org.example.classes;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.config.GrassConfig;
import org.example.helpers.Vector2d;

public class Grass extends AbstractMapElement{
    private GrassConfig grassConfig;
    public Grass(Vector2d position, GrassConfig grassConfig) {
        super(position);
        this.grassConfig = grassConfig;
    }

    public GrassConfig getGrassConfig() {
        return grassConfig;
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
