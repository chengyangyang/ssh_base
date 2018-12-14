package ch.dao.user;

import ch.common.exceptions.bean.BusinessException;
import ch.entity.user.User;


public interface UserDao {

	 public boolean saveUser(User user) throws BusinessException;
	 
}
