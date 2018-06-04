package ch.dao.common;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;


public class BaseDao extends HibernateDaoSupport {
	
	@Resource(name="jt")
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource
	 public void setSessionFacotry(SessionFactory sessionFacotry) {
	         super.setSessionFactory(sessionFacotry);    
	 }

}
