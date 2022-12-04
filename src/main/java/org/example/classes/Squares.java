package org.example.classes;

import org.example.config.AnimalConfig;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Squares implements IMapPositionObserver{
    private final Square[][] squares;
    private final Vector2d size;

    public Vector2d getSize() {
        return size;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void forEachSquare(BiConsumer<Square,Vector2d> fn){
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq = squares[i][j];
                fn.accept(sq, new Vector2d(i, j));
            }
        }
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

    public Squares(Vector2d size) {
        this.size = size;
        squares = new Square[size.x()][size.y()];
        int line = size.y()/2;
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq = new Square(j==line ); // w przyszłości predykat
                squares[i][j] = sq;
            }
        }
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

}
