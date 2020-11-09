package ga.mutation;

import ga.model.Individ;

import java.util.List;

public interface Mutator {

    void execute(List<Individ> population,  int initialPos, int finalPos);

}
