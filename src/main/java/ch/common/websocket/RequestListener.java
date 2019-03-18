package ch.common.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 11:01
 * version 1.0
 */

@WebListener
public class RequestListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();
    }
}
