package ch.service.user;

import ch.dao.user.MyTestEntityDao;
import ch.dao.user.PersonDao;
import ch.entity.exception.BusinessException;
import ch.entity.user.MyTestEntity;
import ch.entity.user.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("MyTestService")
@Transactional(rollbackFor = Exception.class)
public class MyTestServiceImpl implements MyTestService {

    private static Logger logger = LoggerFactory.getLogger(MyTestServiceImpl.class);

    @Autowired
    private MyTestEntityDao myTestEntityDao;

    @Override
    public boolean saveMyTest(MyTestEntity myTestEntity) {
        myTestEntityDao.createMyTest(myTestEntity);
        return true;
    }
}
