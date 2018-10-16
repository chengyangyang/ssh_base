package ch.controller.mq;

import ch.common.mq.QueueSender;
import ch.common.mq.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月16日 15:46
 * version 1.0
 */
@Controller
@RequestMapping("/mq")
public class ActiveMQController {
    @Resource
    private QueueSender queueSender;

    @Resource
    private TopicSender topicSender;

    @Resource
    @Qualifier("queueDestination")
    private Destination queueDestination;

    @Resource
    @Qualifier("topicDestination")
    private Destination topicDestination;


    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public void testSend() {
        for (int i = 0; i < 5; i++) {
            queueSender.sendMessage(queueDestination, "queue生产者产生消息：" + (i + 1));
        }

        for (int i = 0; i < 5; i++) {
            topicSender.sendMessage(topicDestination, "topic生产者产生消息：" + (i + 1));
        }
    }



}
