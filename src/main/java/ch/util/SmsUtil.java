package ch.util;

import com.trm.util.httpclient.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/1.
 */
@Component("smsUtil")
public class SmsUtil {
    //短信发送
    public static final String DEFAULT_SMS_URL = "http://sz.ipyy.com/smsJson.aspx";
    //短信状态
    public static final String SMS_RESULT = "http://sz.ipyy.com/statusJsonApi.aspx";
    
    private static final String MOBILE_SEPARATOR = ",";
    @Autowired
    private HttpClientUtil httpClientUtil;
    
    @Value("#{filePath.sms_signature}")
    private String signature;
    @Value("#{filePath.sms_user_id}")
    private String smsUserId;
    @Value("#{filePath.sms_account}")
    private String smsAccount;
    @Value("#{filePath.sms_password}")
    private String smsPassword;
    
    /**
     *
     * @param mobile
     * @param content 默认发送验证码内容 ，不为空怎么其他内容
     * @param sendTime
     * @return
     */
    public Map buildSms(String mobile, String content, String sendTime) {
        if (null == mobile || !StringUtils.isNotBlank(content)) {
            throw new IllegalArgumentException();
        }
        
        Map<String, String> paramsMap = new HashMap<>();
        content += signature;
        paramsMap.put("userid", null);
        paramsMap.put("account", smsAccount);
        paramsMap.put("password", smsPassword);
        paramsMap.put("action", "send");       // 固定值
        paramsMap.put("mobile", mobile);       // 接收手机号码, "," 分隔
        paramsMap.put("content", content);     // 短信内容
        paramsMap.put("sendTime", sendTime);
        paramsMap.put("extno", "");            // 扩展子号, 不支持留空
        return paramsMap;
    }
    
    public Map buildResult(String taskId) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userid", smsUserId);
        paramsMap.put("account", smsAccount);
        paramsMap.put("password", smsPassword);
        paramsMap.put("action", "query");
        paramsMap.put("taskid", taskId);
        return paramsMap;
    }
    
    public String sendSms(String phone, String content, String sendTime) {
        Map map = buildSms(phone, content, sendTime);
        try {
            String smsReturnString = httpClientUtil.sendHttpPost(DEFAULT_SMS_URL, map);
            return smsReturnString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String sendSmsSendResult(String taskId) {
        Map map = buildResult(taskId);
        try {
            String smsReturnString = httpClientUtil.sendHttpPost(SMS_RESULT, map);
            return smsReturnString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
