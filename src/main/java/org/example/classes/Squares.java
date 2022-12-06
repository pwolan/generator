package org.example.classes;

import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Squares implements IMapPositionObserver{
    private final Square[][] squares;
    private final Vector2d size;

    public Squares(Vector2d size) {
        this.size = size;
        squares = new Square[size.x()][size.y()];
        int line = size.y()/2;
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq = new Square(new Vector2d(i,j), j==line );
                squares[i][j] = sq;
            }
        }
    }

    public Vector2d getSize() {
        return size;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Iterable<Square> getSquaresIterable() {
        List<Square> squaresList = new ArrayList<>();
        for (int i = 0; i < size.x(); i++) {
            for (int j = 0; j < size.y(); j++) {
                Square sq = squares[i][j];
                squaresList.add(sq);
            }
        }
        return squaresList;
    }
    public Iterable<Animal> getAnimalsIterable(){
        List<Animal> animalList = new ArrayList<>();
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq = squares[i][j];
                for(IMapElement el : sq.getElements()){
                    if(el instanceof Animal animal){
                        animalList.add(animal);
                    }
                }
            }
        }
        return animalList;
    }


    public void addElementToSquare(IMapElement element){
        Vector2d pos = element.getPosition();
        Square sq = squares[pos.x()][pos.y()];
        sq.addElement(element);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element) {
        Square oldSquare = squares[oldPosition.x()][oldPosition.y()];
        oldSquare.removeElement(element);
        Square newSquare = squares[newPosition.x()][newPosition.y()];
        newSquare.addElement(element);
    }

    public Iterable<Square> getSquaresToEatIterable() {
        List<Square> filteredSquares = new ArrayList<>();
        for (Square sq : this.getSquaresIterable()) {
            boolean isAnimalIn = sq.getElements().stream().anyMatch(el -> el instanceof Animal);
            boolean isGrassIn = sq.getElements().stream().anyMatch(el -> el instanceof Grass);
            if(isAnimalIn && isGrassIn){
                filteredSquares.add(sq);
            }
        }
        return filteredSquares;
    }
    public Iterable<Square> getSquaresToReproduce(){
        List<Square> filteredSquares = new ArrayList<>();
        for (Square sq : this.getSquaresIterable()) {
            long numOfAnimals = sq.getElements().stream()
                    .filter(el -> el instanceof Animal an && an.canReproduce())
                    .count();
            if(numOfAnimals >= 2){
                filteredSquares.add(sq);
            }
        }
        return filteredSquares;
    }

    public List<Vector2d> getGrassAviablePositions() {
        return StreamSupport.stream(getSquaresIterable().spliterator(), false)
                .filter(sq -> sq.getElements().stream().noneMatch(el -> el instanceof Grass))
                .map(Square::getPosition).collect(Collectors.toList());
    }
}
