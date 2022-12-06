package org.example.classes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Genes {
    private int[] genesArray;
    private int activeGene;

    public Genes(int[] genesArray) {
        this.genesArray = genesArray;
        activeGene = (int) (Math.random() * genesArray.length);
    }

    public void nextGene(){
        activeGene = (activeGene + 1) % genesArray.length;
    }
    public int getActiveGene(){
        return genesArray[activeGene];
    }

    public void mutate(int minMut, int maxMut) {
        Random rand = new Random();
        int numberOfMutations = rand.nextInt(maxMut - minMut) + minMut;
        Set<Integer> genesToMutate = new HashSet<>();
        while (genesToMutate.size() < numberOfMutations){
            int newIndex = rand.nextInt(genesArray.length);
            genesToMutate.add(newIndex);
        }
        for(int geneIndex : genesToMutate){
            genesArray[geneIndex] = rand.nextInt(8);
        }

    }

    public static Genes combine(Genes g1, Genes g2, double percent) {
        int genesLen = g1.genesArray.length;
        int divisionIndex = (int) (genesLen * percent);
        boolean reverse = new Random().nextBoolean();
        int[] newGenesArray = new int[genesLen];

        for (int i = 0; i < genesLen; i++) {
            // i<divisionIndex XOR reverse
            if ((i < divisionIndex) ^ reverse) {
                newGenesArray[i] = g2.genesArray[i];
            } else {
                newGenesArray[i] = g1.genesArray[i];
            }
        }
        return new Genes(newGenesArray);

    }
    public static int[] getRandomGenotype(int len){
        int[] genotype = new int[len];
        for (int i = 0; i < len; i++) {
            int n = (int)(Math.random()*8);
            genotype[i] = n;
        }
        return genotype;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int g : genesArray) {
            out.append(g);
            out.append(" ");
        }
        return out.toString();
    }
}
