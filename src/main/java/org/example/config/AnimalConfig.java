package org.example.config;

public class AnimalConfig {
    private int startAnimals = 5; // startowa liczba zwierzaków
    private int startAnimalEnergy = 100; //startowa energia zwierzaków
    private int fullAnimalEnergy = 50; // energia konieczna, by uznać zwierzaka za najedzonego
    private int reproduceEnergy = 30; // energia rodziców zużywana by stworzyć potomka
    private int minMutation = 1; // minimalna liczba mutacji u potomków
    private int maxMutation = 5; // maksymalna liczba mutacji u potomków
    //wariant mutacji
    private int genesLength = 8;

    public int getStartAnimals() {
        return startAnimals;
    }

    public void setStartAnimals(int startAnimals) {
        this.startAnimals = startAnimals;
    }

    public int getStartAnimalEnergy() {
        return startAnimalEnergy;
    }

    public void setStartAnimalEnergy(int startAnimalEnergy) {
        this.startAnimalEnergy = startAnimalEnergy;
    }

    public int getFullAnimalEnergy() {
        return fullAnimalEnergy;
    }

    public void setFullAnimalEnergy(int fullAnimalEnergy) {
        this.fullAnimalEnergy = fullAnimalEnergy;
    }

    public int getReproduceEnergy() {
        return reproduceEnergy;
    }

    public void setReproduceEnergy(int reproduceEnergy) {
        this.reproduceEnergy = reproduceEnergy;
    }

    public int getMinMutation() {
        return minMutation;
    }

    public void setMinMutation(int minMutation) {
        this.minMutation = minMutation;
    }

    public int getMaxMutation() {
        return maxMutation;
    }

    public void setMaxMutation(int maxMutation) {
        this.maxMutation = maxMutation;
    }

    public int getGenesLength() {
        return genesLength;
    }

    public void setGenesLength(int genesLength) {
        this.genesLength = genesLength;
    }
//wariant zachowania
}
