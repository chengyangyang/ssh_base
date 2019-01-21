package ch.common.bean.weixingzh;

/**
 * Description: 微信公众号开发  文本消息类
 *
 * @author cy
 * @date 2019年01月21日 14:20
 * version 1.0
 */
public class TextMeaasge {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
