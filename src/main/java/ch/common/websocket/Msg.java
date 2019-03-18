package ch.common.websocket;

import java.util.Date;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 10:43
 * version 1.0
 */
public class Msg {

    // 推送人id
    private String fromId;
    // 推送给人的id
    private String toId;
    // 推送消息内容
    private String data;
    // 推送时间
    private Date createDate;
    // 消息状态
    private Integer flag;


    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}
