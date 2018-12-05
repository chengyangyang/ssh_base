package ch.dao.user;

import ch.dao.common.BaseDao;
import ch.entity.user.MyTestEntity;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月05日 16:50
 * version 1.0
 */
@Repository("myTestEntityDao")
public class MyTestEntityDaoImpl extends BaseDao implements MyTestEntityDao {

    @Override
    public boolean createMyTest(MyTestEntity myTestEntity) {
        try {
            this.getHibernateTemplate().save(myTestEntity);
            return true;
        } catch (RuntimeException e) {
            logger.error("Person insert failed", e);
            throw e;
        }
    }
}
