package org.example.interfaces;

import org.example.helpers.Vector2d;

public interface IMapPositionSubject {
    void addObserver(IMapPositionObserver observer);
    void removeObserver(IMapPositionObserver observer);

    void notifyObservers(Vector2d oldPosition, Vector2d newPosition, IMapElement element);
}
