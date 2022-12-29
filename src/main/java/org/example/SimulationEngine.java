package org.example;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.classes.*;
import org.example.gui.MapGui;
import org.example.gui.StatsGui;
import org.example.helpers.Vector2d;
import org.example.interfaces.IWorldMap;

import java.util.List;

public class SimulationEngine implements Runnable {
    private IWorldMap map;
    private Squares squares;
    private SimulationStats simulationStats;
    private MapGui mapGui;
    private StatsGui statsGui;
    private SimulationConfig config;

    public SimulationEngine(SimulationConfig config, GridPane rootPane, Pane rootStats) {
        Vector2d mapSize = config.getMapSize();
        IWorldMap map;
        if(config.getMapName().equals("Hell")){
            map = new Hell(mapSize,config.getGrassConfig());
        }
        else{
            map = new Globe(mapSize,config.getGrassConfig());
        }
        this.map = map;
        this.squares = map.getSquares();
        this.mapGui = new MapGui(squares, rootPane);
        this.simulationStats = new SimulationStats(squares, config);
        this.statsGui = new StatsGui(rootStats,simulationStats);
        this.config = config;

        this.init();
    }

    private void init(){
        //add Grasses
        int startGrasses = config.getGrassConfig().getStartGrasses();
        for(Vector2d pos : this.map.getRandomGrassPositions(startGrasses)){
            Grass g = new Grass(pos, config.getGrassConfig());
            g.addObserver(squares);
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
        while (true){
            this.mapGui.render();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            day();
        }

    }

    private void day(){
        // usunięcie martwych zwierząt z mapy
        for(Animal animal: this.squares.getDeadAnimals()){
            animal.remove();
        }

        // skret i przemieszczenie kazdego zwierzecia
        for (Animal animal: this.squares.getAnimalsList() ){
            animal.nextMove(this.map);
        }

        //  konsumpcja roślin na których pola weszły zwierzęta

        for(Square sq : this.squares.getSquaresToEatIterable()){
            Animal strongestAnimal = sq.getStrongestAnimal();
            Grass grassToEat = sq.getGrassElement();
            strongestAnimal.eatGrass(grassToEat);
            sq.getElements().remove(grassToEat);

        }

        // rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu
        Reproducer reproducer = new Reproducer( config.getAnimalConfig());
        for(Square sq : this.squares.getSquaresToReproduce()){
            List<Animal> strongestAnimals = sq.getStrongestAnimals(2);
            Animal an1 = strongestAnimals.get(0);
            Animal an2 = strongestAnimals.get(1);

            Animal child = reproducer.reproduce(an1, an2);
            child.addObserver(squares);
        }


        // wzrastanie nowych roślin na wybranych polach mapy
        int grassesPerDay = config.getGrassConfig().getNewGrassesPerDay();
        for(Vector2d pos : this.map.getRandomGrassPositions(grassesPerDay)){
            Grass g = new Grass(pos, config.getGrassConfig());
            g.addObserver(squares);
            map.place(g);
        }
        // liczenie statsystyk

        this.simulationStats.refresh();
        statsGui.render();

    }

}
