package org.example.interfaces;

import org.example.helpers.Vector2d;

public interface IMapPositionObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element);
}
