package ch.entity.user;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import ch.entity.exception.BusinessException;
import ch.service.user.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
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
        return new SimpleAuthenticationInfo(user, pwd, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection princ) {
        return null;
    }
}
