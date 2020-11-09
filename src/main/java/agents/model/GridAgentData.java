package agents.model;

import java.util.Arrays;
import java.util.List;

public class GridAgentData {

    private List<Double> priceSchedule = Arrays.asList(
            1.5, 1.5, 1.5, 1.5, 1.5, 1.5,
            3.5, 3.5, 3.5, 3.5, 2.5, 2.5,
            3.5, 3.5, 3.5, 3.5, 5.0, 5.0,
            5.0, 5.0, 5.0, 5.0, 3.5, 3.5
    );

    private int energyLimit = 5;
    private int availableEnergy = energyLimit;

    public boolean canSell(){
        return availableEnergy > 0;
    }

    public void reduceAvailableEnergy(){
        availableEnergy--;
    }
    public void resetAvailableEnergy(){
        availableEnergy = energyLimit;
    }

    public List<Double> getPriceSchedule() {
        return priceSchedule;
    }
}
