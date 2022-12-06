package org.example.classes;

import org.example.SimulationConfig;

public class SimulationStats {
    private Squares squares;
    private int[] stats;
    private String bestGene;



    public SimulationStats(Squares squares, SimulationConfig config) {
        this.squares = squares;

        stats = new int[]{
                config.getAnimalConfig().getStartAnimals(),
                config.getGrassConfig().getStartGrasses(),
                config.getMapSize().x() * config.getMapSize().y() - config.getAnimalConfig().getStartAnimals() - config.getGrassConfig().getStartGrasses(),
                config.getAnimalConfig().getStartEnergy(),
                config.getAnimalConfig().getStartEnergy(),
        };
    }

    public int get(int n){
        return stats[n];
    }
    public void refresh(){
        stats[0] = squares.getAnimalsList().size();
        stats[1] = squares.getGrassesList().size();
        stats[2] = squares.getEmptyCount();
        stats[3] = squares.getAvgEnergyAlive();
        stats[4] = squares.getAvgEnergyAll();
        bestGene = squares.getBestGene();
    }

    public String getBestGene() {
        return bestGene;
    }
}
