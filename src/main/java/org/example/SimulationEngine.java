package org.example;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.gui.MapGui;
import org.example.helpers.Vector2d;
import org.example.interfaces.IWorldMap;

public class SimulationEngine implements Runnable {
    private IWorldMap map;
    private Squares squares;
    private MapGui mapGui;
    private Stage primaryStage;
    public SimulationEngine(IWorldMap map, GridPane rootPane) {
        this.map = map;
        this.squares = map.getSquares();
        this.mapGui = new MapGui(squares, rootPane);

    }

    @Override
    public void run() {


        Animal a1 = new Animal(new Vector2d(2,2));
        a1.addObserver(squares);
        Animal a2 = new Animal(new Vector2d(5,5));
        a2.addObserver(squares);
        map.place(a1);
        map.place(a2);
        this.mapGui.render();
    }
}
