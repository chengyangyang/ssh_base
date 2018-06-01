package ch.util;

import com.trm.model.session.SessionBean;
import com.trm.model.session.UserSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

/**
* @description get use session related info
* @author Vani
* @Time 2016年8月9日
*/
public class SessionUtil {
	
	public static UserSession getUserSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// TODO
		if (null != authentication && null != authentication.getPrincipal() ) {
			Object principal = authentication.getPrincipal(); 
			if (principal instanceof UserSession) {
				final UserSession session = (UserSession) principal;
				return session;
			}
		} 
		
		return null;
	}
	
	/**
	 * 方法功能说明：  前端用户登录，更新
	 * @参数： @param userSession
	 * @参数： @param request      
	 * @return void     
	 * @throws
	 */
	public static void memberUpdateSession(UserSession userSession, HttpServletRequest request) {

		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
				userSession, userSession.getPassword(), userSession.getAuthorities());
		if (request != null) {
			authentication.setDetails(new WebAuthenticationDetails(request));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
	
	public static SessionBean getSesssionBean() {
		UserSession	userSession = SessionUtil.getUserSession();
		return null != userSession ? userSession.getSession() : null;
	}
	
	public static int getUserIdFromSession(){
		SessionBean session = getSesssionBean();
		//这个用于用户测试，做好登陆以后需要去掉这行
		return null != session ? session.getUserId() : 0;
	}
	
	public static String getUserNameFromSession(){
		SessionBean session = getSesssionBean();
		return null != session ? session.getUserName() : null;
	}
	
	public static String getUserPhoneFromSession(){
		SessionBean session = getSesssionBean();
		return null != session ? session.getPhone() : null;
	}
	
	public static String getRealNameFromSession(){
		SessionBean session = getSesssionBean();
		return null != session ? session.getRealName() : null;
	}
	
	public static String getEmailFromSession(){
		SessionBean session = getSesssionBean();
		return null != session ? session.getEmail() : null;
	}
	
	public static String getCertTypeFromSession(){
		SessionBean session = getSesssionBean();
		return null != session ? (session.getCert_type() == null ? null : session.getCert_type().toString()) : null;
	}
	
}
