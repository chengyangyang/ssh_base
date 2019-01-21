package ch.controller.weixin;

import ch.common.bean.weixingzh.Music;
import ch.common.bean.weixingzh.TextMeaasge;
import ch.common.bean.weixingzh.Video;
import ch.common.util.weixin.gzh.WXResponseMessage;
import ch.common.util.weixin.gzh.WXgzhUtils;
import ch.util.EncryptAndDecryptUtil;
import ch.util.StringUtils;
import ch.common.util.weixin.gzh.WXMessageHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

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


    @Autowired
    WXgzhUtils wXgzhUtils;
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

    /*
     *  本文以文本内容为例,进行消息的回复
     * 处理微信服务器发来的消息
     *
     * */
    @RequestMapping(value = "/validateService", method = RequestMethod.POST)
    public void validateServicePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("开始构造消息");
        Map<String,String> map = WXMessageHandlerUtil.parseXml(request);
        /**
         * 微信服务器进行请求的
         * <xml>
         *     <ToUserName>< ![CDATA[toUser] ]></ToUserName>
         *     <FromUserName>< ![CDATA[fromUser] ]></FromUserName>
         *     <CreateTime>1348831860</CreateTime>
         *     <MsgType>< ![CDATA[text] ]></MsgType>
         *     <Content>< ![CDATA[this is a test] ]></Content>
         *     <MsgId>1234567890123456</MsgId>
         * </xml>
         */
        // 消息发送时间
        String createTime = map.get("CreateTime");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        //消息内容
        String content = map.get("Content");
        // 默认回复一个"success"
        String responseMessage = "success";

        // 对文本消息的处理
        /*if(WXMessageHandlerUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
            //TextMeaasge
            TextMeaasge textMessage=new TextMeaasge();
            textMessage.setMsgType(WXMessageHandlerUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setToUserName(fromUserName);//注意这里的toUserName 是刚才接收xml中的FromUserName
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            String str = new String(content);
            System.out.println("str-------"+str);
            textMessage.setContent("服务端返回:"+str);
            responseMessage = WXMessageHandlerUtil.textMessageToXML(textMessage);
        }else if(WXMessageHandlerUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)){
            //对语音消息进行处理
            responseMessage="你发送了一个消息";
        }*/
        //使用上面的或者当前这个
        switch (content) {
            case "文本":
                String msgText = "欢迎进入我的博客\n" +
                        "<a href=\"https://www.cnblogs.com/chengyangyang/\">书香门第</a>";
                responseMessage = WXResponseMessage.buildTextMessage(map, msgText);
                break;
            case "图片":
                //通过素材管理接口上传图片时得到的media_id
                String imgMediaId = "dSQCiEHYB-pgi7ib5KpeoFlqpg09J31H28rex6xKgwWrln3HY0BTsoxnRV-xC_SQ";
                responseMessage = WXResponseMessage.buildImageMessage(map, imgMediaId);
                break;
            case "语音":
                //通过素材管理接口上传语音文件时得到的media_id
                String voiceMediaId = "h3ul0TnwaRPut6Tl1Xlf0kk_9aUqtQvfM5Oq21unoWqJrwks505pkMGMbHnCHBBZ";
                responseMessage = WXResponseMessage.buildVoiceMessage(map,voiceMediaId);
                break;
            case "图文":
                responseMessage = WXResponseMessage.buildNewsMessage(map);
                break;
            case "音乐":
                Music music = new Music();
                music.setTitle("赵丽颖、许志安 - 乱世俱灭");
                music.setDescription("电视剧《蜀山战纪》插曲");
                music.setMusicUrl("http://gacl.ngrok.natapp.cn/music/music.mp3");
                music.setHqMusicUrl("http://gacl.ngrok.natapp.cn/music/music.mp3");
                responseMessage = WXResponseMessage.buildMusicMessage(map, music);
                break;
            case "视频":
                Video video = new Video();
                video.setMediaId("GqmIGpLu41rtwaY7WCVtJAL3ZbslzKiuLEXfWIKYDnHXGObH1CBH71xtgrGwyCa3");
                video.setTitle("小苹果");
                video.setDescription("小苹果搞笑视频");
                responseMessage = WXResponseMessage.buildVideoMessage(map, video);
                break;
            default:
                TextMeaasge textMessage=new TextMeaasge();
                textMessage.setMsgType(WXMessageHandlerUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setToUserName(fromUserName);//注意这里的toUserName 是刚才接收xml中的FromUserName
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis());
                String str = new String("\n 文本" +
                        "\n 图片" +
                        "\n 语音" +
                        "\n 图文" +
                        "\n 音乐" +
                        "\n 视频" +
                        "\n");
                textMessage.setContent("请输入:"+str);
                responseMessage = WXMessageHandlerUtil.textMessageToXML(textMessage);
                break;

        }
        response.getWriter().print(responseMessage);
    }

    /**
     * 公众号菜单的添加
     * @return
     */
    @RequestMapping(value = "/setMenu", method = RequestMethod.GET)
    @ResponseBody
    public String setMenu(){
        wXgzhUtils.setMenu();
        return "成功!";
    }

    /**
     * 公众号菜单的添加
     * @return
     */
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
    @ResponseBody
    public String deleteMenu(){
        wXgzhUtils.delteMenu();
        return "成功!";
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
