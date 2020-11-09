package ga.crossingover;

import ga.model.Individ;

import java.util.List;
import java.util.Random;

public class CrossingOverImpl implements CrossingOver {
    private final Random r = new Random();

    @Override
    public void execute(List<Individ> population, int populationSize) {

        int goodIndividCount = population.size();

        Individ theBestOne = population.get(0);
        for (int i = 1; i < goodIndividCount; i++) {
            population.add(theBestOne.cross(population.get(i)));
        }
        int newPopulationSize = population.size();

        for (int i =0; i < populationSize - newPopulationSize; i++){
            Individ parent1 = population.get(r.nextInt(goodIndividCount));
            Individ parent2 = population.get(r.nextInt(goodIndividCount));
            population.add(parent1.cross(parent2));
        }
    }
}
