package ga.selection;

import ga.model.Individ;

import java.util.List;

public interface Selector {

    void execute(List<Individ> population);

}
