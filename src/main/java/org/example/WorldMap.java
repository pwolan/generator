package org.example;

import org.example.classes.Squares;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IWorldMap;

public  class WorldMap implements IWorldMap {
    private Vector2d size;
    private Squares squares;

    public WorldMap(Vector2d size) {
        this.size = size;
        this.squares = new Squares(size);
    }




   public void place(IMapElement element){
       squares.addElementToSquare(element);
   }

    public Squares getSquares() {
        return squares;
    }

    /**
     * @return random position in map size
     */
    public Vector2d getRandomPosition(){
        int x = (int) (Math.random()*size.x());
        int y = (int) (Math.random()*size.y());
        return new Vector2d(x, y);
    }
}
