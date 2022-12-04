package org.example;

import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Squares implements IMapPositionObserver {
    private final Map<Vector2d, Square> squaresMap = new HashMap<>();


    public Map<Vector2d, Square> getSquaresMap() {
        return squaresMap;
    }

    public Squares(Vector2d boundary) {
        int line = boundary.y()/2;
        for(int i = 0; i < boundary.x(); i++){
            for (int j = 0; j < boundary.y(); j++) {
                Vector2d key = new Vector2d(i, j);
                Square sq = new Square(j==line ); // w przyszłości predykat
                squaresMap.put(key, sq);
            }
        }
    }
    public void addElementToSquare(IMapElement element){
        Square sq = squaresMap.get(element.getPosition());
        sq.addElement(element);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element) {
        Square oldSquare = squaresMap.get(oldPosition);
        oldSquare.removeElement(element);
        Square newSquare = squaresMap.get(newPosition);
        newSquare.addElement(element);
    }

}
