package ch.util.paging;

import com.trm.entity.exception.BusinessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class QueryPage_JDBC extends AbstractPage implements Pageable{
	
	private String queryRecordsSql;
	private Object[] parameters;
	
	private JdbcTemplate jdbcTemplate;
	
	public QueryPage_JDBC(String queryRecordsSql, Object[] parameters, int currentPage, 
			int pageSize, JdbcTemplate jdbcTemplate) throws BusinessException{
		
		super(currentPage, pageSize);
		this.queryRecordsSql = queryRecordsSql;
		this.jdbcTemplate = jdbcTemplate;
		this.parameters = parameters;	
		this.count = this.jdbcTemplate.queryForList(queryRecordsSql, parameters).size();
		
		init();
	}
	
	public QueryPage_JDBC(String queryRecordsSql, Object[] parameters, int currentPage, JdbcTemplate jdbcTemplate) throws BusinessException{
		this(queryRecordsSql, parameters, currentPage, Pageable.DEFAULT_PAGESIZE, jdbcTemplate);
	}
	
	@Override
	protected void init() throws BusinessException {
		this.checkPage(getCurrentPage());
		this.result = this.jdbcTemplate.queryForList(queryRecordsSql, parameters).subList(getStartIndex(), getEndIndex());
	}

	@Override
	public int getStartIndex() {
		return (this.getCurrentPage()-1) * this.getPageSize();
	}

	@Override
	public int getEndIndex() {
		return Math.min(this.getStartIndex() + this.getPageSize() ,this.count);
	}

}
