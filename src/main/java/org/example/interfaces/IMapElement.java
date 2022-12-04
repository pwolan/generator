package org.example.interfaces;

import javafx.scene.Node;
import org.example.helpers.Vector2d;

public interface IMapElement {
    Vector2d getPosition();

    Node getGuiElement();
//    void removeElement(); może??
}
