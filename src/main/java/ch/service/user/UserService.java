package ch.service.user;

import ch.cache.session.SessionDAO;
import ch.entity.exception.BusinessException;
import ch.entity.user.User;

public interface UserService {

	 public boolean saveUser(User user) throws BusinessException;
	 
	 public User findByUsername(String userName) throws BusinessException;

	public SessionDAO getSessionDAO();
}
