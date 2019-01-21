package ch.common.bean.weixingzh;

/**
 * Description: 微信公众号token模型
 *
 * @author cy
 * @date 2019年01月21日 9:25
 * version 1.0
 */
public class AccessToken {

    // 获取到的凭证
    private String accessToken;

    //有效时间
    private int expiresin;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresin=" + expiresin +
                '}';
    }
}
