package agents;

import agents.behaviours.hometrader.ActionCheckerBehaviour;
import agents.behaviours.hometrader.ConsumptionSimulationBehaviour;
import agents.behaviours.hometrader.RequestPriceScheduleBehaviour;
import agents.model.TraderData;
import jade.core.Agent;

public class HomeTraderAgent extends Agent {


    @Override
    protected void setup() {
        TraderData data = new TraderData(this);
        data.setLoadSchedule(generateLoadSchedule(350));
        data.setCurrentCharge(3500);
        data.setMaxCharge(12000);

        addBehaviour(new ConsumptionSimulationBehaviour(this, 6, data));
        addBehaviour(new RequestPriceScheduleBehaviour(this, data));
        addBehaviour(new ActionCheckerBehaviour(this, 100, data));

    }


    private double[] generateLoadSchedule(double basicVal){
        double[] coefs = new double[] {
                1,   1,   1,   1.2, 1.2, 1.5,
                1.5, 2,   2.5, 2.5, 2,   2.5,
                3,   3.5, 3,   3.5, 4,   4,
                4.5, 5,   6,   6.5, 5,   3.5
        };
        double[] loads = new double[coefs.length];
        for (int i =0; i < loads.length; i++){
            loads[i] = coefs[i]*basicVal;
        }
        return loads;

    }
}
