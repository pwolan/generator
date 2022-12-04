package org.example;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.classes.Animal;
import org.example.classes.Grass;
import org.example.classes.Squares;
import org.example.gui.MapGui;
import org.example.helpers.Vector2d;
import org.example.interfaces.IWorldMap;

public class SimulationEngine implements Runnable {
    private IWorldMap map;
    private Squares squares;
    private MapGui mapGui;
    private SimulationConfig config;

    public SimulationEngine( SimulationConfig config, GridPane rootPane) {
        Vector2d mapSize = config.getMapSize();
        IWorldMap map = new WorldMap(mapSize);
        this.map = map;
        this.squares = map.getSquares();
        this.mapGui = new MapGui(squares, rootPane);
        this.config = config;

        this.init();
    }

    private void init(){
        //add Grasses
        for (int i = 0; i < config.getGrassConfig().getStartGrasses(); i++) {
            Vector2d pos = map.getRandomPosition();
            Grass g = new Grass(pos, squares);
            map.place(g);
        }
        //add Animals
        for (int i = 0; i < config.getAnimalConfig().getStartAnimals(); i++) {
            Vector2d pos = map.getRandomPosition();
            Animal an = new Animal(pos, config.getAnimalConfig());
            an.addObserver(squares);
            map.place(an);
        }


    }

    @Override
    public void run() {

        this.mapGui.render();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        day();
        this.mapGui.render();
    }

    private void day(){
//        removeDeadAnimals //na koncu zrobie

        // skret i przemieszczenie kazdego zwierzecia

        for (Animal animal: this.squares.getAnimalsIterable() ){
            animal.nextMove();
        }



//        eatGrasses
//        reproduce
//        growGrasses

    }

}
