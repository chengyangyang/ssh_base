package ch.dao.user;

import ch.entity.exception.BusinessException;
import ch.entity.user.Person;

public interface PersonDao {
    public boolean savePerson(Person person);
    public Person getPerson(String id) throws BusinessException;
}
