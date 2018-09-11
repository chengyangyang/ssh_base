package ch.dao.user;

import ch.dao.common.BaseDao;
import ch.entity.exception.BusinessException;
import ch.entity.user.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoImpl extends BaseDao implements PersonDao {
    private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    public boolean createPerson(Person person) {
        try {
            this.getHibernateTemplate().save(person);
            return true;
        } catch (RuntimeException e) {
            logger.error("Person insert failed", e);
            throw e;
        }
    }

    public Person getPerson(String id) throws BusinessException {
        String hql = "from Person where id = ?";
        Person person = (Person) this.currentSession().createQuery(hql).setParameter(0,id).uniqueResult();
        return person;
    }
}
