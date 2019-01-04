package ch.common.page;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;


import java.util.List;

public class QueryNotPage {

    private String queryRecordsSql;
    private Object[] parameters;
    private HibernateTemplate hibernateTemplate;
    private List result;

    public QueryNotPage(String queryRecordsSql, Object[] parameters, HibernateTemplate hibernateTemplate) {
        this.queryRecordsSql = queryRecordsSql;
        this.parameters = parameters;
        this.hibernateTemplate = hibernateTemplate;
        init();
    }

    protected void init() {
        //获取当前线程中的Session对象
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();

        Query query = session.createQuery(queryRecordsSql);

        if (parameters != null) {
            if(0 != parameters.length){
                for(int i = 0; i<parameters.length; i++){
                    query.setParameter(i, parameters[i]);
                }
            }
        }
        this.result = query.list();
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
