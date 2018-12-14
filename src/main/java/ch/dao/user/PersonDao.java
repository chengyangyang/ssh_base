package ch.dao.user;

import ch.common.exceptions.bean.BusinessException;
import ch.entity.user.Person;

public interface PersonDao {
    public boolean createPerson(Person person);
    public Person getPerson(String id) throws BusinessException;
}
