package org.example;

import org.example.classes.Animal;
import org.example.classes.Square;
import org.example.classes.Squares;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IWorldMap;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d size;
    protected Squares squares;

    public AbstractWorldMap(Vector2d size) {
        this.size = size;
        this.squares = new Squares(size);
    }

    @Override
    public abstract Vector2d move(Animal animal, Vector2d moveVec);

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

    /**
     * @param n max number of positions to return
     * @return random positions that are not occupied by grass
     */
    public Iterable<Vector2d> getRandomGrassPositions(int n){
        List<Square> aviableSquares = squares.getGrassAviableSquares();

        if(aviableSquares.size() <= n){
            return aviableSquares.stream().map(Square::getPosition).toList();
        }
        // divide to preferredList or notPreferredList
        List<Vector2d> preferredList = new ArrayList<>();
        List<Vector2d> notPreferredList = new ArrayList<>();
        for(Square sq : aviableSquares) {
            if (sq.isPrefered()) {
                preferredList.add(sq.getPosition());
            } else {
                notPreferredList.add(sq.getPosition());
            }
        }

        Set<Vector2d> randomGrassPositions = new HashSet<>();
        Random rand = new Random();
        while (randomGrassPositions.size() < n){
            // draw from which list positions will be taken
            boolean usePreferred = rand.nextInt(5) != 0;

            if(usePreferred && preferredList.size() > 0){
                int index = rand.nextInt(preferredList.size());
                randomGrassPositions.add(preferredList.get(index));
            } else {
                int index = rand.nextInt(notPreferredList.size());
                randomGrassPositions.add(notPreferredList.get(index));
            }

        }
        return randomGrassPositions;
    }
}
