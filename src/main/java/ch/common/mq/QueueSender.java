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
 * @date 2018年10月16日 15:25
 * version 1.0
 */
public class QueueSender {

    @Resource
    private JmsTemplate jmsQueueTemplate;


    //发送消息
    public void sendMessage(Destination destination, final String message) {
        System.out.println("QueueSender发送消息："+message);
        jmsQueueTemplate.send(destination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                // TODO Auto-generated method stub
                return session.createTextMessage(message);
            }
        });
    }

}
