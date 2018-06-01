package ch.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class ReferTokenInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ReferTokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ReferToken annotation = handlerMethod.getMethodAnnotation(ReferToken.class);
            if (annotation != null) {
                //如果重复相同数据
                if(repeatDataValidator(request)){
                    logger.info("#####重复数据提交########");
                    return false;
                }else{
                    return true;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证同一个url数据是否相同提交  ,相同返回true
     * @param httpServletRequest
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest)
    {
        StringBuffer params = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = httpServletRequest.getReader();
            while((line = reader.readLine()) != null) {
                params.append(line);
            }
        }catch(Exception e) {
            System.out.println(e.toString());
        }

        String url=httpServletRequest.getRequestURI();
        Map<String,String> map=new HashMap<String,String>();
        map.put(url, params.toString());
        String nowUrlParams=map.toString();//

        Object preUrlParams=httpServletRequest.getSession().getAttribute("repeatData");
        //如果上一个数据为null,表示还没有访问页面
        if(preUrlParams==null){
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
            return false;
        }else{
            //否则，已经访问过页面
            if(preUrlParams.toString().equals(nowUrlParams)){
                //如果上次url+数据和本次url+数据相同，则表示城府添加数据
                logger.info("#####重复数据提交new########"+nowUrlParams);
                logger.info("#####重复数据提交old########"+preUrlParams);
                return true;
            }else{
                //如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }
        }
    }
}
