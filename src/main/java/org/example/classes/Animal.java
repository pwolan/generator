package org.example.classes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.config.AnimalConfig;
import org.example.enums.MapDirection;
import org.example.helpers.Vector2d;
import org.example.interfaces.IWorldMap;


public class Animal extends AbstractMapElement implements Comparable<Animal> {

    private MapDirection orientation;

    private int energy;
    private Genes genes;
    private final AnimalConfig animalConfig;
    private int age = 0;
    private int childrenNumber = 0;
    public Animal(Vector2d position, AnimalConfig animalConfig) {
        this(position, animalConfig, animalConfig.getStartEnergy(),new Genes(Genes.getRandomGenotype(animalConfig.getGenesLength())));

    }
    public Animal(Vector2d position, AnimalConfig animalConfig, int startEnergy, Genes genes) {
        super(position);
        this.animalConfig = animalConfig;
        this.genes = genes;
        this.energy = startEnergy;
        this.orientation = MapDirection.getRandom();

    }
    public void nextMove(IWorldMap map){

        int activeGene = genes.getActiveGene();
        orientation = orientation.rotate(activeGene);
        Vector2d moveVec = orientation.toUnitVector();
        Vector2d oldPosition = position;

        position = map.move(this, moveVec);


        energy = Math.max(energy - animalConfig.getForDayEnergy(), 0);
        notifyObservers(oldPosition, position, this);
        genes.nextGene();
        age++;
    }
    @Override
    public Node getGuiElement() {
        Circle circle = new Circle();
        circle.setRadius(20);
        // basic color changes for now
        float mltp = (float) energy / animalConfig.getMaxAnimalEnergy();
        circle.setFill(Color.color(1-mltp, 0.16470589f, 0.16470589f));
        circle.viewOrderProperty().setValue(0);
        return circle;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }
    @Override
    public int compareTo(Animal a2){
        if (energy == a2.getEnergy()) {
            if (age == a2.getAge()) {
                if (childrenNumber== a2.getChildrenNumber()) {
                    return 0;
                }
                return childrenNumber - a2.getChildrenNumber();
            }
            return age - a2.getAge();
        }
        return energy - a2.getEnergy();
    }

    public void eatGrass(Grass grass) {
        int maxEnergy = animalConfig.getMaxAnimalEnergy();
        int newEnergy = energy + grass.getGrassConfig().getEnergyPerGrass();
        energy = Math.min(maxEnergy, newEnergy);
    }

    public boolean canReproduce() {
        return energy >= animalConfig.getReadyToReproduceEnergy();
    }

    public int giveReproduceEnergy() {
        energy = energy - animalConfig.getReproduceEnergy();
        return animalConfig.getReproduceEnergy();
    }

    public Genes getGenes() {
        return genes;
    }
    public void reverseOrientation(){
        orientation = orientation.rotate(4);
    }
}