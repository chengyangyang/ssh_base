package ch.service.user;

import ch.entity.exception.BusinessException;
import ch.entity.user.Person;

public interface PersonService {

    public boolean savePerson(Person person);
    public Person getPerson(String id) throws BusinessException;
}
