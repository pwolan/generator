package org.example.classes;

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

    public static int[] getRandomGenotype(int len){
        int[] genotype = new int[len];
        for (int i = 0; i < len; i++) {
            int n = (int)(Math.random()*8);
            genotype[i] = n;
        }
        return genotype;
    }
}
