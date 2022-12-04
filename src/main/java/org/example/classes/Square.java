package org.example.classes;

import org.example.interfaces.IMapElement;

import java.util.ArrayList;
import java.util.List;

/**
 * trzyma informacje o pojedynczym kwadracie na planszy
 */
public class Square {
    private List<IMapElement> elements = new ArrayList<>();
    private boolean prefered;
    public Square(){
        this.prefered = false;
    }

    public List<IMapElement> getElements() {
        return elements;
    }

    public Square(boolean prefered) {
        this.prefered = prefered;
    }

    public boolean isPrefered() {
        return prefered;
    }

    public void addElement(IMapElement el){
        elements.add(el);
    }
    public void removeElement(IMapElement el){
        elements.remove(el);
    }
}
