package org.example.interfaces;

import org.example.classes.Squares;
import org.example.helpers.Vector2d;

public interface IWorldMap {
    void place(IMapElement element);

    Squares getSquares();

    Vector2d getRandomPosition();
}
