package ch.common.bean;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 11:39
 * version 1.0
 */
public class Result<T> {

    private int code;
    private String msg;
    private String msgDes;
    private T data;


    // 成功的返回值
    public Result ok(T t){
        this.code = 200;
        this.msg = "success";
        this.data = t;
        return this;
    }

    // 成功的返回值
    public Result ok(){
        this.code = 200;
        this.msg = "success";
        return this;
    }

    // 成功的返回值
    public Result ok(String msgDes){
        this.code = 200;
        this.msg = "success";
        this.msgDes = msgDes;
        return this;
    }

    // 错误的返回值
    public Result error(String msgDes){
        this.code = 500;
        this.msg = "error";
        this.msgDes = msgDes;
        return this;
    }

    // 错误的返回值
    public Result error(){
        this.code = 500;
        this.msg = "error";
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }
}
