package ch.service.websocket;

import ch.common.bean.Result;
import ch.entity.websocket.MemberLoginForm;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 11:37
 * version 1.0
 */
public interface LoginMemberService {

    public Result toLogin(MemberLoginForm loginForm);

    public Result tishi();

}
