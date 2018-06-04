package ch.service.mytest;

import ch.dao.mytest.PersonDao;
import ch.entity.mytest.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
@Transactional(rollbackFor = Exception.class)
public class PersonServiceImpl implements PersonService {

    private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    public boolean savePerson(Person person) {
        logger.info("保存成功");
        return personDao.savePerson(person);
    }
}
