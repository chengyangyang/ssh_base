package ch.dao.user;

import org.springframework.stereotype.Repository;

import ch.dao.common.BaseDao;
import ch.common.exceptions.bean.BusinessException;
import ch.entity.user.User;

@Repository("user")
public class UserDaoImpl extends BaseDao implements UserDao {

	
	public boolean saveUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
