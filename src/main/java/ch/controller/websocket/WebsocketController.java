package ch.controller.websocket;

import ch.common.bean.Result;
import ch.common.util.weixin.gzh.WXgzhUtils;
import ch.entity.websocket.MemberLoginForm;
import ch.service.websocket.LoginMemberService;
import ch.util.EncryptAndDecryptUtil;
import ch.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月18日 11:58
 * version 1.0
 */
@Controller
@RequestMapping("/websocket")
public class WebsocketController {

    @Autowired
    private LoginMemberService loginMemberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<MemberLoginForm> login(@RequestBody MemberLoginForm memberLoginForm,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user",memberLoginForm.getUserName());
        MemberLoginForm userResult = new MemberLoginForm();
        userResult.setUserName(memberLoginForm.getUserName());
        userResult.setId(session.getId()); // 存放session id
        return new Result<MemberLoginForm>().ok(userResult);
    }


    @RequestMapping(value = "/tishi", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request){
        return loginMemberService.tishi();
    }




}
