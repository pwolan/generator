package org.example.config;

public class AnimalConfig {
    /** startowa liczba zwierzaków */
    private int startAnimals = 6;
    /** startowa energia zwierzaków */
    private int startEnergy = 80;
    /** energia konieczna, by uznać zwierzaka za najedzonego */
    private int readyToReproduceEnergy = 30;
    /** energia zużywana podczas jednego dnia*/
    private int forDayEnergy = 10;
    /** energia rodziców zużywana by stworzyć potomka*/
    private int reproduceEnergy = 20;
    /** maksymalna ilość energii Animala*/
    private int maxAnimalEnergy = 100;
    /** minimalna liczba mutacji u potomków */
    private int minMutation = 1;
    /** maksymalna liczba mutacji u potomków */
    private int maxMutation = 5;
    //wariant mutacji
    private int genesLength = 8;

    private String mutationName = "Random";

    private String behaviorName = "Predestination";

    public int getStartAnimals() {
        return startAnimals;
    }

    public void setStartAnimals(int startAnimals) {
        this.startAnimals = startAnimals;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public void setStartEnergy(int startEnergy) {
        this.startEnergy = startEnergy;
    }

    public int getReadyToReproduceEnergy() {
        return readyToReproduceEnergy;
    }

    public void setReadyToReproduceEnergy(int readyToReproduceEnergy) {
        this.readyToReproduceEnergy = readyToReproduceEnergy;
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

    public int getForDayEnergy() {
        return forDayEnergy;
    }

    public void setForDayEnergy(int forDayEnergy) {
        this.forDayEnergy = forDayEnergy;
    }

    public int getMaxAnimalEnergy() {
        return maxAnimalEnergy;
    }

    public void setMaxAnimalEnergy(int maxAnimalEnergy) {
        this.maxAnimalEnergy = maxAnimalEnergy;
    }
    //wariant zachowania
    public void setMutationName(String mutationName) {this.mutationName = mutationName;}
    public String getMutationName() {return mutationName;}
    public void setBehaviorName(String behaviorName) {this.behaviorName = behaviorName;}
    public String getBehaviorName() {return behaviorName;}

}
