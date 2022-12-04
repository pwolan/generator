package org.example.config;

public class GrassConfig {
    //wariant wzrostu roślin
    private int startGrasses = 5; // startowa liczba roślin
    private int energyPerGrass = 50; // energia zapewniana przez zjedzenie jednej rośliny

    public int getStartGrasses() {
        return startGrasses;
    }

    public void setStartGrasses(int startGrasses) {
        this.startGrasses = startGrasses;
    }

    public int getEnergyPerGrass() {
        return energyPerGrass;
    }

    public void setEnergyPerGrass(int energyPerGrass) {
        this.energyPerGrass = energyPerGrass;
    }

    public int getNewGrassesPerDay() {
        return newGrassesPerDay;
    }

    public void setNewGrassesPerDay(int newGrassesPerDay) {
        this.newGrassesPerDay = newGrassesPerDay;
    }

    private int newGrassesPerDay = 3; // liczba roślin wyrastająca każdego dnia
}
