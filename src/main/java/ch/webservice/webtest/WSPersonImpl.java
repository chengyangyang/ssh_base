package ch.webservice.webtest;

import ch.entity.exception.BusinessException;
import ch.entity.user.Person;
import ch.service.user.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月30日 12:20
 * version 1.0
 */
public class WSPersonImpl implements WSPerson {
    @Autowired
    private PersonService personService;
    @Override
    public Person getPerson(String id) throws BusinessException {
        return personService.getPerson(id);
    }
}
