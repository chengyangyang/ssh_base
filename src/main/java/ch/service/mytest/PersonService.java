package ch.service.mytest;

import ch.entity.mytest.Person;
import ch.exception.BusinessException;

public interface PersonService {

    public boolean savePerson(Person person);
    public Person getPerson(String id) throws BusinessException;
}
