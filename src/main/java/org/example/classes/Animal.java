package org.example.classes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.config.AnimalConfig;
import org.example.enums.MapDirection;
import org.example.helpers.Vector2d;

import java.util.Map;


public class Animal extends AbstractMapElement {

    private MapDirection orientation;
    private int energy;
    private Genes genes;
    private final AnimalConfig animalConfig;
    public Animal(Vector2d position, AnimalConfig animalConfig) {
        super(position);
        this.animalConfig = animalConfig;
        this.genes = new Genes(Genes.getRandomGenotype(animalConfig.getGenesLength()));
        this.energy = animalConfig.getStartAnimalEnergy();
        this.orientation = MapDirection.getRandom();

    }
    public void nextMove(){

        int activeGene = genes.getActiveGene();
        orientation = orientation.rotate(activeGene);
        Vector2d moveVec = orientation.toUnitVector();
        Vector2d oldPosition = position;
        position = oldPosition.add(moveVec);
        notifyObservers(oldPosition, position, this);
        genes.nextGene();
    }
    @Override
    public Node getGuiElement() {
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.BROWN);
        circle.viewOrderProperty().setValue(0);
        return circle;
    }



}
