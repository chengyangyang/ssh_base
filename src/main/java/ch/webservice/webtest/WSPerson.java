package ch.webservice.webtest;

import ch.common.exceptions.bean.BusinessException;
import ch.entity.user.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月30日 12:19
 * version 1.0
 */
@WebService
public interface WSPerson {
    @WebMethod
    public Person getPerson(String id) throws BusinessException;
}
