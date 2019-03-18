package ch.common.websocket;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 10:52
 * version 1.0
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 获得当前http链接的session
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        // 将http session信息注入websocket session
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
