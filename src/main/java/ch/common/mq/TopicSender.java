package ch.common.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月16日 15:21
 * version 1.0
 */
public class TopicSender {

    @Resource
    private JmsTemplate jmsTopicTemplate;

    //发送消息
    public void sendMessage(Destination destination, final String message) {
        System.out.println("TopicSender发送消息："+message);
        jmsTopicTemplate.send(destination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                // TODO Auto-generated method stub
                return session.createTextMessage(message);
            }
        });
    }


}
