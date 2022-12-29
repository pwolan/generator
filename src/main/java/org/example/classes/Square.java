package org.example.classes;

import org.example.helpers.Vector2d;
import org.example.interfaces.IMapElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * trzyma informacje o pojedynczym kwadracie na planszy
 */
public class Square {
    private List<IMapElement> elements = new ArrayList<>();
    private boolean prefered;
    Vector2d position;
    public Square(Vector2d position) {
        this(position, false);
    }
    public Square(Vector2d position, boolean prefered){
        this.position = position;
        this.prefered = prefered;
    }

    public Vector2d getPosition() {
        return position;
    }

    public List<IMapElement> getElements() {
        return elements;
    }

    public void updatePrefered(boolean prefered) {this.prefered = prefered;}
    public boolean isPrefered() {
        return prefered;
    }

    public void addElement(IMapElement el){
        elements.add(el);
    }
    public void removeElement(IMapElement el){
        elements.remove(el);
    }

    public Animal getStrongestAnimal() {
        Animal an = elements.stream()
                .filter(el -> el instanceof Animal)
                .map(el -> (Animal) el)
                .max(Animal::compareTo).orElse(null);
        return an;
    }

    /**
     *
     * @param n - number of elements to return
     * @return List of strongest Animals
     */
    public List<Animal> getStrongestAnimals(int n) {
        return elements.stream()
                .filter(el -> el instanceof Animal)
                .map(el -> (Animal) el)
                .sorted(Animal::compareTo).limit(n).collect(Collectors.toList());
    }


    public Grass getGrassElement() {
        for(IMapElement el : elements){
            if(el instanceof Grass gr){
                return gr;
            }
        }
        return null;
    }
}
