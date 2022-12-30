package org.example.classes;

import org.example.SimulationConfig;


import java.io.*;
import java.util.Arrays;

public class SimulationStats {
    private Squares squares;
    private int[] stats;
    private String bestGene;
    SimulationConfig config;

    public SimulationStats(Squares squares, SimulationConfig config) throws IOException {
        this.squares = squares;
        this.config = config;
        stats = new int[]{
                config.getAnimalConfig().getStartAnimals(),
                config.getGrassConfig().getStartGrasses(),
                config.getMapSize().x() * config.getMapSize().y() - config.getAnimalConfig().getStartAnimals() - config.getGrassConfig().getStartGrasses(),
                config.getAnimalConfig().getStartEnergy(),
                config.getAnimalConfig().getStartEnergy(),
        };
        if(config.getSaveToFile()){
            FileWriter fw = new FileWriter("Data.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\"Animals\",\"Grasses\",\"Empty\",\"AvgEnergyAlive\",\"AvgEnergyAll\",\"bestGene\"");
            bw.newLine();
            bw.close();
        }
    }

    public int get(int n){
        return stats[n];
    }
    public void refresh() throws IOException {
        stats[0] = squares.getAnimalsList().size();
        stats[1] = squares.getGrassesList().size();
        stats[2] = squares.getEmptyCount();
        stats[3] = squares.getAvgEnergyAlive();
        stats[4] = squares.getAvgEnergyAll();
        bestGene = squares.getBestGene();
        if(config.getSaveToFile()){
            save();
        }
    }
    public void save() throws IOException {
        FileWriter fw = new FileWriter("Data.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(stats[0] + "," + stats[1] + "," + stats[2] + "," + stats[3] + "," + stats[4] + "," + "\"" + bestGene + "\"");
        bw.newLine();
        bw.close();
    }
    public String getBestGene() {
        return bestGene;
    }
}
