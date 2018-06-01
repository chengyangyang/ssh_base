package ch.annotation;

import com.trm.constants.BusinessConstants;
import com.trm.constants.ExceptionConstants;
import com.trm.constants.SystemConstants;
import com.trm.entity.exception.ExceptionResponse;
import com.trm.entity.exception.Reason;
import com.trm.model.session.SessionBean;
import com.trm.util.SessionUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    
    /**
     *  preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用。 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        HandlerMethod handler2 = (HandlerMethod) handler;
        //获取注解  
        Login login = handler2.getMethodAnnotation(Login.class);
        
        if (null == login) {
            //木有声明权限，可以放行  
            return true;
        }
        
        // 获取controller注解上的过滤级别
        String loginType = login.value();
        SessionBean user = SessionUtil.getSesssionBean();
        
        // 采用ajax 提交的  
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
        
        logger.info("判断用户是否登录");
        // 如果该action 必须用户登录之后才能使用，则给出提示
        if (BusinessConstants.FRONT_USER.equals(loginType)) {
            ExceptionResponse eResponse = new ExceptionResponse();
            eResponse.setResponsecode(ExceptionConstants.BUSI_ERR_9999);
            eResponse.setErrorType(SystemConstants.ERROR_TYPE_2);
            
            Reason reason = new Reason();
            
            // 1. 没有用户信息 
            if (null == user) {
                reason.setDesc("您未登录,请先登录!");
                reason.setReasoncode(SystemConstants.FRONT_USER_NOT_LOGIN);
                // 2. 有用户信息，直接返回true
            } else {
                return true;
            }
            
            eResponse.setReason(reason);
            JSONObject json = JSONObject.fromObject(eResponse);
            
            pw.println(json.toString());
            pw.flush();
            pw.close();
            return false;
        }
        
        return true;
    }
    
    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。 
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 后， 
     *也就是在Controller方法调用后执行，但是会在DispatcherServlet进行视图的渲染之前执行. 
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    
    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。 
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，主要是用来清理释放资源的 
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    
}
