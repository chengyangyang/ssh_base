package ch.dao.mytest;

import ch.entity.mytest.Person;
import ch.exception.BusinessException;

public interface PersonDao {
    public boolean savePerson(Person person);
    public Person getPerson(String id) throws BusinessException;
}
