package agents.behaviours.gridagent;

import agents.model.GridAgentData;
import agents.model.PricesAnswerMsg;
import agents.model.Protocols;
import helpers.JsonHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

public class PriceScheduleResponseBehaviour extends Behaviour {

    private List<Double> priceSchedule;

    public PriceScheduleResponseBehaviour(GridAgentData data) {
        priceSchedule = data.getPriceSchedule();
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchProtocol(Protocols.price_schedule.toString())
        );
        ACLMessage req = getAgent().receive(mt);
        if (req != null){
            ACLMessage reply = req.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(JsonHelper.serialize(new PricesAnswerMsg(priceSchedule)));
            getAgent().send(reply);
        }else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
