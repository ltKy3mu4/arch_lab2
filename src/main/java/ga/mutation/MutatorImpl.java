package ga.mutation;

import ga.model.Individ;

import java.util.List;
import java.util.Random;

public class MutatorImpl implements  Mutator{
    private Random r = new Random();
    private int mutatedGenesCount = 0;

    @Override
    public void execute(List<Individ> population, int initialPos, int finalPos) {
        int local = mutatedGenesCount;
        if (local ==0)
            local = r.nextInt(3)+1;

        for (int i = initialPos; i < finalPos; i++){
            population.get(i).mutate(local);
        }
    }

    public void setMutatedGenesCount(int mutatedGenesCount) {
        this.mutatedGenesCount = mutatedGenesCount;
    }
}
