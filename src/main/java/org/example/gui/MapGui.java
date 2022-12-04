package org.example.gui;



import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.example.Square;
import org.example.Squares;
import org.example.helpers.Vector2d;

import java.util.Map;

public class MapGui {

    private Squares squares;
    private GridPane rootPane;
    private int CELL_SIZE = 40;
    public MapGui(Squares squares, GridPane rootPane) {
        this.squares = squares;
        this.rootPane = rootPane;
        render();
    }
    public void render(){
        rootPane.getColumnConstraints().clear();
        rootPane.getRowConstraints().clear();
        rootPane.getChildren().clear();

////        Node gr = rootPane.getChildren().get(0);
//
//        rootPane.getChildren().clear();
//        rootPane.add(gr,0,0);
//        rootPane.setGridLinesVisible(true);
        rootPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        for (Map.Entry<Vector2d, Square> entry : this.squares.getSquaresMap().entrySet()) {
            Vector2d pos = entry.getKey();
            Square sq = entry.getValue();
            GuiElementBox elementBox = new GuiElementBox(sq,pos);
            elementBox.renderElement(this.rootPane);
            rootPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
            rootPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }

    }
}
