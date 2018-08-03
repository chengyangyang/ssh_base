package ch.service.user;

import ch.cache.session.SessionDAO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.entity.exception.BusinessException;
import ch.entity.user.User;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService,InitializingBean {

	@Autowired
	private SessionDAO sessionDAO;
	//-- User Service --//
	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}

	public boolean saveUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	public User findByUsername(String userName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
