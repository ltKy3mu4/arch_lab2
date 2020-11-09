package agents;

import agents.behaviours.gridagent.PriceScheduleResponseBehaviour;
import agents.behaviours.gridagent.ResetSellingLimitBehaviour;
import agents.behaviours.gridagent.SellEnergyBehaviour;
import agents.model.GridAgentData;
import agents.model.Services;
import helpers.DfHelper;
import jade.core.Agent;

import java.util.Arrays;
import java.util.List;

public class GridAgent extends Agent {
    private GridAgentData data = new GridAgentData();

    @Override
    protected void setup() {
        DfHelper.registerItself(Services.GRID_PRICE.toString(), this);
        addBehaviour(new PriceScheduleResponseBehaviour(data));
        addBehaviour(new SellEnergyBehaviour(data));
        addBehaviour(new ResetSellingLimitBehaviour(this, data));

    }


}
