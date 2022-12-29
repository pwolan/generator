package org.example;

import org.example.config.AnimalConfig;
import org.example.config.GrassConfig;
import org.example.helpers.Vector2d;
import org.example.interfaces.IWorldMap;

public class SimulationConfig {
    private Vector2d mapSize = new Vector2d(10, 10); // szerokość i wysokość mapy
    //wariant mapy
    private String mapName = "Globe";


    private AnimalConfig animalConfig = new AnimalConfig();
    private GrassConfig grassConfig = new GrassConfig();

    public Vector2d getMapSize() {
        return mapSize;
    }

    public void setMapSize(Vector2d mapSize) {
        this.mapSize = mapSize;
    }

    public AnimalConfig getAnimalConfig() {
        return animalConfig;
    }

    public void setAnimalConfig(AnimalConfig animalConfig) {
        this.animalConfig = animalConfig;
    }

    public GrassConfig getGrassConfig() {
        return grassConfig;
    }

    public void setGrassConfig(GrassConfig grassConfig) {
        this.grassConfig = grassConfig;
    }
    public void setMapName(String mapName) {this.mapName = mapName; }
    public String getMapName() {return mapName; }



}
