package ch.common.util;

import ch.common.email.MailSenderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
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

    private static MailSenderInfo mailInfo = null;
    private static Session session = null;


    /**
     *
     * @param subject 主题
     * @param to 发送给什么人
     * @return
     */
    public MimeMessage baseInfoStart(String subject,String to){
        mailInfo =(MailSenderInfo)SpringContextHolder.getBean("mailInfo");
        //创建session实例
        session = Session.getDefaultInstance(mailInfo.getProps());

        //创建mimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress(mailInfo.getFromAdress()));

            //设置邮箱主题
            message.setSubject(subject, "utf-8");
            //设置收件人(类型中有抄送,密送人)
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //设置发送时间
            message.setSentDate(new Date());

            //设置自定义发件人昵称
            String nick="";
            try {
                nick=javax.mail.internet.MimeUtility.encodeText("我的java测试");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(nick != ""){
                message.setFrom(new InternetAddress(nick+" <"+mailInfo.getUserName()+">"));
            }

            // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
            message.setHeader("Disposition-Notification-To", mailInfo.getUserName());


        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
        return message;

    }

    /**
     *
     * @param message 对邮件发送结束的提取
     * @return
     */
    public boolean baseEnd(MimeMessage message){
        try {
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
            return true;
        }catch (MessagingException e){
            e.printStackTrace();
            return false;
        }


    }

    /**
     * 发送简单的文本邮件
     * @param subject 设置邮箱主题
     * @param to 发送的地址
     * @param text 发送的内容
     * @return
     */
    public  boolean sendTextEmail(String subject,String to,String text)  {
        MimeMessage message = baseInfoStart(subject,to);
        if(message == null){
            return false;
        }
        //发送内容
        try {
            message.setText(text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return baseEnd(message);
    }


    /**
     * 发送普通的html类型
     * @param subject 设置邮箱主题
     * @param to 发送的地址
     * @param text 发送的内容
     * @return
     */
    public  boolean sendHtmlEmail(String subject,String to,String text)  {
        MimeMessage message = baseInfoStart(subject,to);
        if(message == null){
            return false;
        }
        //发送内容
        try {
            message.setContent(text,"text/html;charset=utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return baseEnd(message);
    }

    /**
     * 发送镶嵌图片的html类型
     * @param subject 设置邮箱主题
     * @param to 发送的地址
     * @param
     * @return
     */
    public  boolean sendHtmlAndImgEmail(String subject,String to)  {
        MimeMessage message = baseInfoStart(subject,to);
        if(message == null){
            return false;
        }
        //发送内容
        try {
            // 准备邮件数据
            // 准备邮件正文数据
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
            // 准备图片数据
            MimeBodyPart image = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(" C:\\cyy\\Desert.jpg"));
            image.setDataHandler(dh);
            image.setContentID("xxx.jpg");
            // 描述数据关系
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text);
            mm.addBodyPart(image);
            mm.setSubType("related");

            message.setContent(mm);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return baseEnd(message);
    }






}
