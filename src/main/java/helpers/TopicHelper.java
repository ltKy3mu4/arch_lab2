package helpers;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;

public class TopicHelper {

    public static AID registerTopic(Agent myAgent, String topicName){
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper) myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topic = topicHelper.createTopic(topicName);
            topicHelper.register(topic);
            return topic;

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException("Can not create topic with name : "+topicName);
        }
    }
}
