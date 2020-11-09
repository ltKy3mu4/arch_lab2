package agents.behaviours.hometrader;

import agents.model.PricesAnswerMsg;
import agents.model.Protocols;
import agents.model.TraderData;
import helpers.JsonHelper;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Arrays;
import java.util.List;


public class RequestPriceScheduleBehaviour extends TickerBehaviour {
    private final TraderData data;
    private List<Double> l = Arrays.asList(2.5, 1.5, 2.5);

    public RequestPriceScheduleBehaviour(Agent a, TraderData data) {
        super(a, 200);
        this.data = data;
    }

    @Override
    public void onStart() {
        ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
        req.setProtocol(Protocols.price_schedule.toString());
        req.addReceiver(data.getGridAgent());
        getAgent().send(req);
    }

    @Override
    public void onTick() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol(Protocols.price_schedule.toString()),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM)
        );

        ACLMessage resp = getAgent().receive(mt);
        if (resp != null) {
            PricesAnswerMsg ans = JsonHelper.deserialize(resp.getContent(), PricesAnswerMsg.class);
            double[] prices = new double[ans.getPrices().size()];
            for (int i = 0; i < ans.getPrices().size(); i++) {
                prices[i] = ans.getPrices().get(i);
            }
            data.setPriceSchedule(prices);
            System.out.println(getAgent().getLocalName()+ " - i received price, starting update my action schedule");
            stop();
        }
    }

    @Override
    public int onEnd() {
        getAgent().addBehaviour(new ActionScheduleUpdaterBehaviour(getAgent(), data));
        return 0;
    }
}
