package ch.controller.email;

import ch.common.util.SendmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Map;


/**
 * Description:
 *
 * @author cy
 * @date 2018年10月18日 13:57
 * version 1.0
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private SendmailUtils email;
    /**
     * 发送邮件
     *
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(@RequestBody Map<String,String> map) {
        email.sendTextEmail(map.get("subject"),map.get("to"),map.get("content"));
        System.out.println("发送成功");
        return "发送成功";
    }
}
