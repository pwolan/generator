package org.example.interfaces;

import org.example.Squares;

public interface IWorldMap {
    void place(IMapElement element);

    Squares getSquares();
}
