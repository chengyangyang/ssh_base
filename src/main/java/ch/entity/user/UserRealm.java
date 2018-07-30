package ch.entity.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.common.config.Global;
import ch.util.UserUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import ch.entity.exception.BusinessException;
import ch.service.user.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }
	 /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        // 获取用户名称
        String username = (String) token.getPrincipal();
        User user = null;
		try {
			user = userService.findByUsername(username);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String pwd = user.getPassword();
        
        //加盐处理
        //ByteSource salt = ByteSource.Util.bytes("Shiro");
        //new SimpleAuthenticationInfo(user, pwd,salt, this.getName());

        return new SimpleAuthenticationInfo(user, pwd, this.getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
            Collection<Session> sessions = userService.getSessionDAO().getActiveSessions(true, principal, UserUtils.getSession());
            if (sessions.size() > 0){
                // 如果是登录进来的，则踢出已在线用户
                if (UserUtils.getSubject().isAuthenticated()){
                    for (Session session : sessions){
                        userService.getSessionDAO().delete(session);
                    }
                }// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                else{
                    UserUtils.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }
            }
        }


    	     // 获取身份信息
        String username = (String) principals.getPrimaryPrincipal();
     // 这里使用静态数据模拟
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:*");
        permissions.add("department:*");
        
     // 将权限信息封闭为AuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 模拟数据，添加 manager 角色
        simpleAuthorizationInfo.addRole("manager");
        
        for(String permission:permissions){
            simpleAuthorizationInfo.addStringPermission(permission);
        }
        
        return simpleAuthorizationInfo;
        

    }
}
