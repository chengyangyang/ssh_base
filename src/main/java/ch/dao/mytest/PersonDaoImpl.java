package ch.dao.mytest;

import ch.dao.common.BaseDao;
import ch.entity.mytest.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoImpl extends BaseDao implements PersonDao {
    private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    public boolean savePerson(Person person) {
        try {
            this.getHibernateTemplate().save(person);
            return true;
        } catch (RuntimeException e) {
            logger.error("CreditDetail insert failed", e);
            throw e;
        }
    }
}
