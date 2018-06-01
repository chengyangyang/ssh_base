package ch.util.paging;

import com.trm.entity.exception.BusinessException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;


public class QueryPage extends AbstractPage implements Pageable{
	
	private String queryRecordsSql;
	private Object[] parameters;
	
	private HibernateTemplate hibernateTemplate;
	
	public QueryPage(String queryRecordsSql, Object[] parameters, int currentPage, 
			int pageSize, HibernateTemplate hibernateTemplate) throws BusinessException {
		
		super(currentPage, pageSize);
		this.queryRecordsSql = queryRecordsSql;
		this.hibernateTemplate = hibernateTemplate;
		this.parameters = parameters;	
		if(parameters == null || parameters.length == 0){
			this.count = this.hibernateTemplate.find(queryRecordsSql).size();
		}else{
			this.count = this.hibernateTemplate.find(queryRecordsSql, parameters).size();
		}
		
		init();
	}
	
	public QueryPage(String queryRecordsSql, Object[] parameters, int currentPage, HibernateTemplate hibernateTemplate) throws BusinessException {
		this(queryRecordsSql, parameters, currentPage, Pageable.DEFAULT_PAGESIZE, hibernateTemplate);
	}
	
	@Override
	protected void init() throws BusinessException {
		this.checkPage(getCurrentPage());
		//this.result = this.hibernateTemplate.find(queryRecordsSql, parameters).subList(this.getStartIndex(), this.getEndIndex());
		
		//获取当前线程中的Session对象
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		
		Query query = session.createQuery(queryRecordsSql);
		query.setFirstResult(getStartIndex());
		query.setMaxResults(getPageSize());
		
		if (parameters != null) {
			if(0 != parameters.length){
				for(int i = 0; i<parameters.length; i++){
					query.setParameter(i, parameters[i]);
				}
			}
		}
		
		this.result = query.list();
		
	}

	@Override
	public int getStartIndex() {
		//当前页大于大于总页数的时候，把最后一页的数据返回
		int page = this.getCurrentPage();
		
		if(page > this.getPages()) {
			page = this.getPages();
		}
		
		return (page-1) * this.getPageSize();
	}

	@Override
	public int getEndIndex() {
		return Math.min(this.getStartIndex() + this.getPageSize() ,this.count);
	}

}
