package ch.controller.mq;

import ch.common.mq.QueueSender;
import ch.common.mq.TopicSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Description:生产者产生消息后,消费者处理消息
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

    /**
     * 发送的消息,只能被一个消费者接受,@RequestBody接受的是字符串,是针对post
     *
     */
    @RequestMapping(value = "/sendMessageQueue", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessageQueue(@RequestBody Map<String,String> map) {
        queueSender.sendMessage("queue生产者产生消息：" + map.get("message"));
        System.out.println("消息发送完毕");
        return "详情请看控制台";
    }

    /**
     *
     * 发送的消息能被所有的消费者接受RequestParam注解是针对get方法的
     */
    @RequestMapping(value = "/sendMessageTopic", method = RequestMethod.GET)
    @ResponseBody
    public String sendMessageTopic(@RequestParam("message") String message) {
        topicSender.sendMessage("topic生产者产生消息："+message);
        System.out.println("消息发送完毕");
        return "详情请看控制台";
    }



}
