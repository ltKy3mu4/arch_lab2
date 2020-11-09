package agents.behaviours.gridagent;

import agents.model.GridAgentData;
import helpers.TimeHelper;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class ResetSellingLimitBehaviour extends TickerBehaviour {
    private GridAgentData data;
    public ResetSellingLimitBehaviour(Agent a, GridAgentData data) {
        super(a, TimeHelper.hourDurationMs);
        this.data = data;
    }

    @Override
    protected void onTick() {
        data.resetAvailableEnergy();
    }
}
