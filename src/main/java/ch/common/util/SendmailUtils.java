package ch.common.util;

import ch.common.email.MailSenderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月18日 9:43
 * version 1.0
 */
@Component
public class SendmailUtils {

    /**
     * 发送简单的文本邮件
     * @param subject 设置邮箱主题
     * @param to 发送的地址
     * @param text 发送的内容
     * @return
     */
    public  boolean sendTextEmail(String subject,String to,String text)  {
        MailSenderInfo mailInfo = (MailSenderInfo)SpringContextHolder.getBean("mailInfo");
        //创建session实例
        Session session = Session.getDefaultInstance(mailInfo.getProps());
        //创建mimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress(mailInfo.getFromAdress()));

        //设置邮箱主题
        //设置收件人(类型中有抄送,密送人)
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //设置发送时间
        message.setSentDate(new Date());

        //设置纯文本为邮件的正文
        message.setText(text);


        //保存并生成最终的邮件
        message.saveChanges();
        //获得Transport实例
        Transport transport = session.getTransport();
        //打开连接
        transport.connect(mailInfo.getUserName(),mailInfo.getPassword());
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接(如果发送多个,最好在关闭之前就发送)
        transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
