package ch.service.websocket;

import ch.common.bean.Result;
import ch.common.websocket.Msg;
import ch.common.websocket.WSServer;
import ch.entity.websocket.MemberLoginForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 11:49
 * version 1.0
 */
@Service("loginMemberService")
@Transactional(rollbackFor = Exception.class)
public class LoginMemberServiceImpl implements LoginMemberService {


    @Override
    public Result toLogin(MemberLoginForm loginForm) {

        return new Result().ok("调用成功！");
    }

    @Override
    public Result tishi() {
        Msg msg=new Msg();
        msg.setToId("ppp");
        WSServer.pushBySys(msg);
        return new Result().ok();

    }
}
