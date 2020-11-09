package agents.behaviours.hometrader;

import agents.model.TraderData;
import helpers.TimeHelper;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class ActionCheckerBehaviour extends WakerBehaviour {
    private TraderData data;
    public ActionCheckerBehaviour(Agent a, long timeout, TraderData data) {
        super(a, timeout);
        this.data = data;
    }

    @Override
    protected void onWake() {
        int[] currentActions = data.getActualSchedule();
        if (currentActions == null){
            System.out.println(getAgent().getLocalName() +" - current actions are nod defined. try to repeat in some time");
            getAgent().addBehaviour( new ActionCheckerBehaviour(getAgent(), 500, data));
        } else {
            int currentHour = TimeHelper.getCurrentHour();
            int requiredAction = currentActions[currentHour];
            if (requiredAction > 0) {
                /** значит, что необхзодимо провести закупку*/
                System.out.println(getAgent().getLocalName()+ " - my plan is to buy "+requiredAction+" kwth");
                getAgent().addBehaviour(new BuyEnergyBehaviour(data));

            } else {
                getAgent().addBehaviour(new ActionCheckerBehaviour(getAgent(), TimeHelper.getMsBeforeNextHour(), data));
            }
        }
    }
}
