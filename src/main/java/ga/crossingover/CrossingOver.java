package ga.crossingover;

import ga.model.Individ;

import java.util.List;

public interface CrossingOver {
    void execute(List<Individ> population, int populationSize);
}
