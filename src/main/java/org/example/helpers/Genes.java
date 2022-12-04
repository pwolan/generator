package org.example.helpers;

public class Genes {
    private int[] genesArray;
    private int activeGene = 0;

    public Genes(int[] genesArray) {
        this.genesArray = genesArray;
    }

    public void nextGene(){
        activeGene = (activeGene + 1) % genesArray.length;
    }
    public int getActiveGene(){
        return genesArray[activeGene];
    }
}
