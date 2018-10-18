package ch.common.email;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Description:邮件的类
 *
 * @author cy
 * @date 2018年10月18日 10:09
 * version 1.0
 */
public class MailSenderInfo {

    //发送邮件的端口和ip
    private static String mailServiceHost = "smtp.qq.com";
    private static String mailServicePort = "465";
    // 邮件发送协议
    private static String protocol = "smtp";

    //邮件发送的地址
    private String fromAdress = "390518881@qq.com";

    //登录邮件的发送的用户名和密码
    private static String userName = "390518881@qq.com";
    private static String password = "cqzheorzizgfbggd";
    //是否需要身份验证
    private static String validate = "true";
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private static String debug = "true";

    private static String sslEnable = "true";
    private static Properties props = null;

    static {
        try {
        props = new Properties();
        props.setProperty("mail.transport.protocol",protocol);
        props.setProperty("mail.smtp.host",mailServiceHost);
        props.setProperty("mail.smtp.port",mailServicePort);
        props.setProperty("mail.smtp.auth", validate);
        props.setProperty("mail.debug",debug);
        // 此处填写你的账号
        props.put("mail.user",userName);
        // 16位STMP口令
        props.put("mail.password",password);
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = null;
            sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", sslEnable);
          //  props.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public Properties getProps() {
        return props;
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }
}
