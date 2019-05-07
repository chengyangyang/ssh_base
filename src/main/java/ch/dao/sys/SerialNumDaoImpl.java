package ch.dao.sys;

import ch.dao.common.BaseDao;
import ch.entity.sys.SystemSerialNumberEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 13:29
 * version 1.0
 */
@Repository
public class SerialNumDaoImpl extends BaseDao implements SerialNumDao {

    @Override
    public void save(SystemSerialNumberEntity entiy) {

    }

    @Override
    public SystemSerialNumberEntity findOneByCode(String code) {
        String hql = " from SystemSerialNumberEntity where moduleCode = ?0 ";
        SystemSerialNumberEntity result =(SystemSerialNumberEntity)  this.currentSession().createQuery(hql).setParameter(0,code).uniqueResult();
        return result;
    }

    @Override
    public SystemSerialNumberEntity find(String code) {
        return null;
    }

    @Override
    public void update(SystemSerialNumberEntity entiy) {
        this.getHibernateTemplate().update(entiy);
    }
}
