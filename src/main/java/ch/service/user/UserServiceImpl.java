package ch.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.entity.exception.BusinessException;
import ch.entity.user.User;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

	public boolean saveUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	public User findByUsername(String userName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
