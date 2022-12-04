package org.example.classes;

import javafx.scene.Node;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;
import org.example.interfaces.IMapPositionSubject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapElement implements IMapElement, IMapPositionSubject {
    protected Vector2d position;
    List<IMapPositionObserver> observers = new ArrayList<>();

    public AbstractMapElement(Vector2d position) {
        this.position = position;
    }
    public AbstractMapElement(Vector2d position, IMapPositionObserver observer) {
        this.position = position;
        addObserver(observer);
    }


    @Override
    public Vector2d getPosition() {
        return position;
    }
    @Override
    abstract public Node getGuiElement();

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
