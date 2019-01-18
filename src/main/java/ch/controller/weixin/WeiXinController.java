package ch.controller.weixin;

import ch.util.EncryptAndDecryptUtil;
import ch.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Description:微信小程序的开发(微信服务校验)
 *
 * @author cy
 * @date 2019年01月18日 17:09
 * version 1.0
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {


    private final String TOKEN = "gacl";

    /*
     * 验证服务器地址的有效性
     * 微信服务器会发送一个http请求,并且携带4个参数
     * signature  微信加密签名
     *  timestamp 时间戳
     *  nonce  随机数
     *  echostr 随机字符串
     *   我们要原样返回echostr 数据,则接入生效 否则接入失败
     * */
    @RequestMapping(value = "/validateService", method = RequestMethod.GET)
    @ResponseBody
    public String validateService(HttpServletRequest request){
        // 开始微信的校验
        // 加密
        //先进行排序
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密算法
        String mySignature = EncryptAndDecryptUtil.sha1(sortString);
        //验证签名的正确性
        if(StringUtils.isNoneBlank(signature) && mySignature.equals(signature)){
            System.out.println("签名校验成功.");
            return echostr;
        }else {
            System.out.println("签名校验失败.");
        }
        return null;
    }

    /**
     * 进行排序
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    private String sort(String token, String timestamp, String nonce){
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }

}
