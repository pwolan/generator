package org.example;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.enums.MapDirection;
import org.example.helpers.Genes;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;
import org.example.interfaces.IMapPositionSubject;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement, IMapPositionSubject {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private int energy = 100;
    private Genes genes = new Genes(new int[]{0, 1, 2, 2, 2, 3, 3, 7});
    List<IMapPositionObserver> observers = new ArrayList<>();

    public Animal(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public Node getGuiElement() {
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.BROWN);
        return circle;
    }

    @Override
    public void addObserver(IMapPositionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IMapPositionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Vector2d oldPosition, Vector2d newPosition, IMapElement element) {
        for (IMapPositionObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition, element);
        }
    }






}
