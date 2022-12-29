package org.example.classes;

import org.example.SimulationConfig;
import org.example.config.GrassConfig;
import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IMapPositionObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class Squares implements IMapPositionObserver{
    private final Square[][] squares;
    private final Vector2d size;
    private String grassName;
    private int animalDeathCount = 0;

    private int[][] deathMatrix;

    public Squares(Vector2d size, GrassConfig grassConfig) {
        this.grassName = grassConfig.getGrassName();
        this.deathMatrix =  new int[size.x()][size.y()];
        this.size = size;
        squares = new Square[size.x()][size.y()];
        int line = size.y()/2;
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq;
                if(this.grassName.equals("Toxic")){
                    sq = new Square(new Vector2d(i,j), true );
                }
                else{
                    sq = new Square(new Vector2d(i,j), j==line || j==(line-1) );
                }
                squares[i][j] = sq;
                //przy okazji
                deathMatrix[i][j] = 0;
            }
        }
    }
    public Vector2d getSize() {
        return size;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Square> getSquaresList() {
        List<Square> squaresList = new ArrayList<>();
        for (int i = 0; i < size.x(); i++) {
            for (int j = 0; j < size.y(); j++) {
                Square sq = squares[i][j];
                squaresList.add(sq);
            }
        }
        return squaresList;
    }
    public List<Animal> getAnimalsList(){
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
    public List<Grass> getGrassesList(){
        List<Grass> grassList = new ArrayList<>();
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                Square sq = squares[i][j];
                for(IMapElement el : sq.getElements()){
                    if(el instanceof Grass grass){
                        grassList.add(grass);
                    }
                }
            }
        }
        return grassList;
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
        if(newPosition != null){
            Square newSquare = squares[newPosition.x()][newPosition.y()];
            newSquare.addElement(element);
        } else {
            animalDeathCount++;
            //informacje o śmierciach
            deathMatrix[oldPosition.x()][oldPosition.y()]++;
            if(this.grassName.equals("Toxic")){
                updatePrefered();
            }
        }
    }
    public void updatePrefered(){
        int preferablenumber = deathMatrix[0][0];
        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                preferablenumber = min(preferablenumber,deathMatrix[i][j]);
            }
        }

        for(int i = 0; i < size.x(); i++){
            for (int j = 0; j < size.y(); j++) {
                squares[i][j].updatePrefered(deathMatrix[i][j] == preferablenumber);
            }
        }
    }
    public Iterable<Square> getSquaresToEatIterable() {
        List<Square> filteredSquares = new ArrayList<>();
        for (Square sq : this.getSquaresList()) {
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
        for (Square sq : this.getSquaresList()) {
            long numOfAnimals = sq.getElements().stream()
                    .filter(el -> el instanceof Animal an && an.canReproduce())
                    .count();
            if(numOfAnimals >= 2){
                filteredSquares.add(sq);
            }
        }
        return filteredSquares;
    }

    public List<Square> getGrassAviableSquares() {
        return getSquaresList().stream()
                .filter(sq -> sq.getElements().stream().noneMatch(el -> el instanceof Grass))
                .collect(Collectors.toList());
    }

    public Iterable<Animal> getDeadAnimals() {
        return getAnimalsList().stream()
                .filter(a -> a.getEnergy() <= 0).collect(Collectors.toList());
    }

    public int getEmptyCount() {
        return (int) getSquaresList().stream()
                .filter(square -> square.getElements().size() == 0).count();
    }
    public int getAvgEnergyAlive(){
        return (int) getAnimalsList().stream()
                .mapToDouble(Animal::getEnergy)
                .average().orElse(0);
    }

    public int getAvgEnergyAll() {
        if(animalDeathCount == 0){
            return getAvgEnergyAlive();
        }
        double allEnergy = getAnimalsList().stream()
                .mapToDouble(Animal::getEnergy)
                .sum();
        return (int) (allEnergy / animalDeathCount);

    }

    public String getBestGene() {
        Map<String, Long> counters = getAnimalsList().stream()
                .map(Animal::getGenes)
                .collect(Collectors.groupingBy(Genes::toString, Collectors.counting()));
        return counters.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }
}
