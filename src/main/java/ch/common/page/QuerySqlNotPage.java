package ch.common.page;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;


import java.util.List;

public class QuerySqlNotPage {

    private String queryRecordsSql;
    private Object[] parameters;
    private HibernateTemplate hibernateTemplate;
    private Class transform;
    private List result;

    public QuerySqlNotPage(String queryRecordsSql, Object[] parameters, HibernateTemplate hibernateTemplate, Class transform) {
        this.queryRecordsSql = queryRecordsSql;
        this.parameters = parameters;
        this.hibernateTemplate = hibernateTemplate;
        this.transform = transform;
        init();
    }

    protected void init()  {
        //获取当前线程中的Session对象
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();

        Query query = session.createSQLQuery(queryRecordsSql);

        if (parameters != null) {
            if(0 != parameters.length){
                for(int i = 0; i<parameters.length; i++){
                    query.setParameter(i, parameters[i]);
                }
            }
        }

        if (transform != null) {
            query.setResultTransformer(Transformers.aliasToBean(transform));
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
