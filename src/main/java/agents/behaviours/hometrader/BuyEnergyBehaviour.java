package agents.behaviours.hometrader;

import agents.model.Protocols;
import agents.model.TraderData;
import helpers.TimeHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BuyEnergyBehaviour extends Behaviour {

    private TraderData data;
    private long timeForWacker;
    private boolean msgReceived = false;

    public BuyEnergyBehaviour(TraderData data) {
        this.data = data;
    }

    @Override
    public void onStart() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setProtocol(Protocols.buy_energy.toString());
        msg.addReceiver(data.getGridAgent());
        getAgent().send(msg);
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol(Protocols.buy_energy.toString()),
                MessageTemplate.or(
                        MessageTemplate.MatchPerformative(ACLMessage.AGREE),
                        MessageTemplate.MatchPerformative(ACLMessage.REFUSE)
                )
        );

        ACLMessage reply = getAgent().receive(mt);
        if (reply != null){
            msgReceived = true;
            if (reply.getPerformative() == ACLMessage.AGREE) {
                String strPrice = reply.getContent();
                double price = Double.parseDouble(strPrice);
                data.changeBalance(-1 * price);
                data.changeCurrentCharge(1000);
                data.reduceAction();
                timeForWacker = 50;
                System.out.println(getAgent().getLocalName()+" - i received agree and bought 1kwt for "+price);
            }else {
                System.out.println(getAgent().getLocalName()+" - i received refuse.");
                timeForWacker = TimeHelper.getMsBeforeNextHour();
            }
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return msgReceived;
    }

    @Override
    public int onEnd() {
        getAgent().addBehaviour(new ActionCheckerBehaviour(getAgent(), timeForWacker, data));
        return super.onEnd();
    }
}
