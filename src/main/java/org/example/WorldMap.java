package org.example;

import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IWorldMap;

public  class WorldMap implements IWorldMap {
    private Vector2d boundary;
    private Squares squares;

    public WorldMap(Vector2d boundary) {
        this.boundary = boundary;
        this.squares = new Squares(boundary);
    }




   public void place(IMapElement element){
       squares.addElementToSquare(element);
   }

    public Squares getSquares() {
        return squares;
    }
}
