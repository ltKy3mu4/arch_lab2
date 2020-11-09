package ga;

import ga.crossingover.CrossingOver;
import ga.crossingover.CrossingOverImpl;
import ga.model.Individ;
import ga.model.TargetFunction;
import ga.mutation.Mutator;
import ga.mutation.MutatorImpl;
import ga.selection.Selector;
import ga.selection.SelectorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GAStarter {
    private int populationSize = 20000;
    private Selector selector = new SelectorImpl();
    private Mutator mutator = new MutatorImpl();
    private CrossingOver crossingOver = new CrossingOverImpl();
    private int maxIterCount = 20;

    public Individ start(double[] loadSchedule, double[] priceSchedule, double currentCharge, double maxCharge){
        List<Individ> population = new ArrayList<>(20000);
        for (int i =0; i < 20000; i++){
            population.add(new Individ(loadSchedule, priceSchedule, currentCharge, maxCharge, new TargetFunction()));
        }

        selector.execute(population);
        Individ localBest = population.get(0);
        double prevFFValue = 0;
        double nextFFValue = localBest.getFfValue();
        int iterCount = 0;
        System.out.println("Iter = "+iterCount+ " FF VAlue = "+nextFFValue+" Action Schedule = "+ Arrays.toString(localBest.getActionSchedule()));
        while (Math.abs(nextFFValue - prevFFValue) > 0.1 && iterCount < maxIterCount) {
            prevFFValue = nextFFValue;
            int selectedSize = population.size();
            crossingOver.execute(population, populationSize);
            mutator.execute(population, 1, selectedSize);
            selector.execute(population);
            localBest = population.get(0);
            nextFFValue = localBest.getFfValue();
            iterCount++;
            System.out.println("Iter = "+iterCount+ " FF VAlue = "+nextFFValue+" Action Schedule = "+ Arrays.toString(localBest.getActionSchedule()));
        }

        return localBest;
    }


}
