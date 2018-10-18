package ch.common.email;

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
    private String mailServiceHost;
    private String mailServicePort;
    // 邮件发送协议
    private String protocol;

    //邮件发送的地址
    private String fromAdress;

    //登录邮件的发送的用户名和密码
    private String userName;
    private String password;
    //是否需要身份验证
    private String validate;
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private String debug;

    private String sslEnable;
    private Properties props;


    public String getMailServiceHost() {
        return mailServiceHost;
    }

    public void setMailServiceHost(String mailServiceHost) {
        this.mailServiceHost = mailServiceHost;
    }

    public String getMailServicePort() {
        return mailServicePort;
    }

    public void setMailServicePort(String mailServicePort) {
        this.mailServicePort = mailServicePort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getSslEnable() {
        return sslEnable;
    }

    public void setSslEnable(String sslEnable) {
        this.sslEnable = sslEnable;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
