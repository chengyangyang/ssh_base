package ch.common.util;

import ch.common.email.MailSenderInfo;
import ch.util.DateUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.util.Date;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月18日 9:43
 * version 1.0
 */
public class SendmailUtils {

    private static MailSenderInfo mailInfo = new MailSenderInfo();


    /**
     * 发送简单的文本邮件
     * @param subject 设置邮箱主题
     * @param to 发送的地址
     * @param text 发送的内容
     * @return
     */
    public static boolean sendTextEmail(String subject,String to,String text) throws MessagingException, ParseException {
        //创建session实例
        Session session = Session.getDefaultInstance(mailInfo.getProps());
        //创建mimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(mailInfo.getFromAdress()));
        //设置邮箱主题
        message.setSubject(subject);
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
        transport.connect("smtp.qq.com","390518881@qq.com","cqzheorzizgfbggd");
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
        return true;

    }

    public static void main(String[] args) {
        try {
            sendTextEmail("邮件测试","296421181@qq.com","您的验证码为12123\n你好!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
