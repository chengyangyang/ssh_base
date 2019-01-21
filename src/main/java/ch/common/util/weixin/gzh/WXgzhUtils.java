package ch.common.util.weixin.gzh;

import ch.common.bean.weixingzh.AccessToken;
import ch.common.bean.weixingzh.AccessTokenInfo;
import ch.common.http.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description: 微信公众号
 *
 * @author cy
 * @date 2019年01月21日 9:43
 * version 1.0
 */
@Component
public class WXgzhUtils {

    @Autowired
    private HttpClientUtil httpClientUtil;

    private static final String appId = "wx6bc918be210ea557";
    private static final String appsecret = "721a047f6eb7d8477b824935886c9d8d";


    //开启一个线程

    /**
     * 获取token
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * access_token填写client_credential  固定写法
     *
     */
    public AccessToken getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+ appsecret;
        try {
            String result = httpClientUtil.sendHttpGet(url);
            //解析字符串
            JSONObject json = JSON.parseObject(result);
            AccessToken token = new AccessToken();
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresin(json.getInteger("expires_in"));
            System.out.println("微信公众号token============"+token.toString());
            return token;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
     */
    public void setMenu(){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
        try {
            String accessToken = AccessTokenInfo.accessToken.getAccessToken();
            String format = String.format(url, accessToken);
            String munu = "{\n" +
                    "     \"button\":[\n" +
                    "     {    \n" +
                    "          \"type\":\"click\",\n" +
                    "          \"name\":\"今日歌曲\",\n" +
                    "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "           \"name\":\"菜单\",\n" +
                    "           \"sub_button\":[\n" +
                    "           {    \n" +
                    "               \"type\":\"view\",\n" +
                    "               \"name\":\"搜索\",\n" +
                    "               \"url\":\"http://www.soso.com/\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                 \"type\":\"miniprogram\",\n" +
                    "                 \"name\":\"wxa\",\n" +
                    "                 \"url\":\"http://mp.weixin.qq.com\",\n" +
                    "                 \"appid\":\"wx286b93c14bbf93aa\",\n" +
                    "                 \"pagepath\":\"pages/lunar/index\"\n" +
                    "             },\n" +
                    "            {\n" +
                    "               \"type\":\"click\",\n" +
                    "               \"name\":\"赞一下我们\",\n" +
                    "               \"key\":\"V1001_GOOD\"\n" +
                    "            }]\n" +
                    "       }]\n" +
                    " }";
            String result = httpClientUtil.sendHttpPost(format,munu);
            JSONObject json = JSON.parseObject(result);
            Object errmsg = json.get("errmsg");
            System.out.println("创建菜单========>"+errmsg);
            //解析字符串

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void delteMenu() {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
        try {
            String accessToken = AccessTokenInfo.accessToken.getAccessToken();
            String format = String.format(url, accessToken);
            String result = httpClientUtil.sendHttpPost(format);
            JSONObject json = JSON.parseObject(result);
            Object errmsg = json.get("errmsg");
            System.out.println("删除菜单========>"+errmsg);
            //解析字符串
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
