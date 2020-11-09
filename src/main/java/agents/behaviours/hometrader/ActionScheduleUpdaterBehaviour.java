package agents.behaviours.hometrader;

import agents.model.TraderData;
import ga.GAStarter;
import helpers.TimeHelper;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.Arrays;

public class ActionScheduleUpdaterBehaviour extends TickerBehaviour {
    private TraderData data;

    public ActionScheduleUpdaterBehaviour(Agent a, TraderData data) {
        super(a, TimeHelper.hourDurationMs*24);
        this.data = data;
    }

    @Override
    public void onStart() {
    }

    @Override
    protected void onTick() {
        data.updateActionSchedule();
        System.out.println(getAgent().getLocalName()+ " - i updated action schedule : "+ Arrays.toString(data.getActualSchedule()));
    }

}
