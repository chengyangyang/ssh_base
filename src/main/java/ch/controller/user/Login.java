package ch.controller.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import ch.entity.user.User;
import ch.util.StringUtils;

public class Login {

	/**
     * shiro框架登录
     * @param user
     */
    @RequestMapping(value = "/login",method=RequestMethod.POST)
    public ModelAndView login(User user){
        // 表面校验
        if(StringUtils.isNotBlank(user.getUserName()) || StringUtils.isNotBlank(user.getPassword())){
             return new ModelAndView("login")
                     .addObject("message", "账号或密码不能为空")
                     .addObject("failuser", user);
        }
        // 获取主体
             Subject subject = SecurityUtils.getSubject();
        try{
            // 调用安全认证框架的登录方法
            subject.login(new UsernamePasswordToken(user.getUserName(), user.getPassword()));
        }catch(AuthenticationException ex){
            System.out.println("登陆失败: " + ex.getMessage());
            return new ModelAndView("login")
                    .addObject("message", "用户不存在")
                    .addObject("failuser", user);
        }
        // 登录成功后重定向到首页
        return new ModelAndView("redirect:/index");
    }
}
