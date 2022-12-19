package org.example;

import org.example.classes.Animal;
import org.example.helpers.Vector2d;

import java.util.Random;

public class Hell extends AbstractWorldMap{
    public Hell(Vector2d size) {
        super(size);
    }

    @Override
    public Vector2d move(Animal animal, Vector2d moveVec) {
        Vector2d oldPos = animal.getPosition();
        Vector2d newPos = oldPos.add(moveVec);
        if (newPos.inBoundaries(size)) {
            return newPos;
        } else {
            animal.decreasePortalEnergy();
            Random rand = new Random();
            int x = rand.nextInt(size.x());
            int y = rand.nextInt(size.y());
            System.out.println(new Vector2d(x, y));
            return new Vector2d(x, y);
        }

    }
}
