package ga.selection;

import ga.model.Individ;

import java.util.Collections;
import java.util.List;

public class SelectorImpl implements Selector{
    @Override
    public void execute(List<Individ> population) {
        Collections.sort(population);
        double maxGoodFFValue = 3000;
        int goodIndividCount = 0;

        for (int i =0; i < population.size() / 2 ; i++){
            if (population.get(i).getFfValue() > maxGoodFFValue){
                break;
            }
            goodIndividCount++;
        }

        if (goodIndividCount < population.size() / 5){
            goodIndividCount = population.size() / 5;
        }

        List<Individ> inidividsToDelete = population.subList(goodIndividCount, population.size() - 1);
        population.removeAll(inidividsToDelete);
    }
}
