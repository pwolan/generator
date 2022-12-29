package org.example.classes;

import org.example.config.AnimalConfig;

public class Reproducer {

    private AnimalConfig animalConfig;
    public Reproducer(AnimalConfig config) {
        this.animalConfig = config;
    }

    public Animal reproduce(Animal an1, Animal an2){
        int an1Energy = an1.getEnergy();
        int an2Energy = an2.getEnergy();
        double an1Percent = (double) an1Energy / (an1Energy + an2Energy);


        int childEnergy = an1.giveReproduceEnergy()+an2.giveReproduceEnergy();
        Genes newGenes = Genes.combine(an1.getGenes(), an2.getGenes(), an1Percent);

        int maxMut = animalConfig.getMaxMutation();
        int minMut = animalConfig.getMinMutation();

        newGenes.mutate(minMut, maxMut,animalConfig);

        return new Animal(an1.getPosition(), animalConfig, childEnergy, newGenes);
    }
}
