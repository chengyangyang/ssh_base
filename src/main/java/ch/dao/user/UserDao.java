package ch.dao.user;

import ch.entity.exception.BusinessException;
import ch.entity.user.Person;
import ch.entity.user.User;


public interface UserDao {

	 public boolean saveUser(User user) throws BusinessException;
	 
}
