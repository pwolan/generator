package org.example;

import org.example.classes.Animal;
import org.example.config.GrassConfig;
import org.example.helpers.Vector2d;

public class Globe extends AbstractWorldMap {
    public Globe(Vector2d size, GrassConfig grassConfig) {
        super(size,grassConfig);
    }

    @Override
    public Vector2d move(Animal animal, Vector2d moveVec) {
        Vector2d oldPos = animal.getPosition();
        Vector2d newPos = oldPos.add(moveVec);
        if (newPos.inBoundaries(size)) {
            return newPos;
        }
        if (newPos.x() < 0) {
            newPos = newPos.add(new Vector2d(size.x(), 0));
        }
        if (newPos.x() >= size.x()) {
            newPos = newPos.add(new Vector2d(-size.x(), 0));
        }
        if (newPos.y() < 0 || newPos.y() >= size.y()) {
            newPos = new Vector2d(newPos.x(), oldPos.y());
            animal.reverseOrientation();
        }
        return newPos;
    }
}
