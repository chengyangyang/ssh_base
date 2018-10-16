package ch.common.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月16日 15:38
 * version 1.0
 */
public class QueueReceiver2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("QueueReceiver2接收到消息内容是：" + textMessage.getText());
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
