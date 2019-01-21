package ch.common.runner;

import ch.common.bean.weixingzh.AccessTokenInfo;
import ch.common.util.weixin.gzh.WXgzhUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 只执行一次
 *
 * @author cy
 * @date 2019年01月21日 10:17
 * version 1.0
 */
@Component  //如果是springboot项目,可以使用  implements ApplicationRunner
public class WXAccessToken implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WXgzhUtils wXgzhUtils;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            getWXToken();
        }
    }

    /**
     *
     * 相当于一个定时器
     */
    public void getWXToken(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("=================获取微信公众号token开始=================");
                    try {
                        //获取accessToken
                        AccessTokenInfo.accessToken = wXgzhUtils.getAccessToken();
                        if (AccessTokenInfo.accessToken != null){
                            //获取到access_token 休眠7000秒,大约2个小时左右  有获取次数限制
                            Thread.sleep(7000 * 1000);
                        }else {
                            //获取失败
                            Thread.sleep(1000 * 3); //获取的access_token为空 休眠3秒
                        }
                    }catch (Exception e){
                        System.out.println("发生异常!");
                        e.printStackTrace();
                        try {
                            Thread.sleep(1000 * 10); //发生异常休眠1秒
                        }catch (Exception e1){

                        }
                    }

                }
            }
        }).start();

    }

}
