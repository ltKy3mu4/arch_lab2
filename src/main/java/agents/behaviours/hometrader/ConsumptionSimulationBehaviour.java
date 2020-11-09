package agents.behaviours.hometrader;

import agents.model.TraderData;
import helpers.TimeHelper;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class ConsumptionSimulationBehaviour extends TickerBehaviour {
    private TraderData data;
    private int portions;

    public ConsumptionSimulationBehaviour(Agent a, int portions, TraderData data) {
        super(a, TimeHelper.hourDurationMs / portions);
        this.portions = portions;
        this.data = data;
    }

    @Override
    protected void onTick() {
        data.setCurrentCharge(data.getCurrentCharge() - data.getLoadSchedule()[TimeHelper.getCurrentHour()]/portions);
        System.out.println(getAgent().getLocalName()+" load = " + data.getLoadSchedule()[TimeHelper.getCurrentHour()]+" current charge = "+data.getCurrentCharge());
    }
}
