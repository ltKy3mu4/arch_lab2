package agents.behaviours.gridagent;

import agents.model.GridAgentData;
import agents.model.Protocols;
import helpers.TimeHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SellEnergyBehaviour extends Behaviour {

    private GridAgentData data;

    public SellEnergyBehaviour(GridAgentData data) {
        this.data = data;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol(Protocols.buy_energy.toString()),
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
        );
        ACLMessage req = getAgent().receive(mt);
        if (req !=null){
            ACLMessage reply = req.createReply();
            if (data.canSell()){
                reply.setPerformative(ACLMessage.AGREE);
                data.reduceAvailableEnergy();
                reply.setContent(data.getPriceSchedule().get(TimeHelper.getCurrentHour())+"");
            } else {
                reply.setPerformative(ACLMessage.REFUSE);
            }
            getAgent().send(reply);
        }else{
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
