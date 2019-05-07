package ch.dao.sys;

import ch.entity.sys.SystemSerialNumberEntity;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 13:26
 * version 1.0
 */
public interface SerialNumDao {

    void save(SystemSerialNumberEntity entiy);

    void update(SystemSerialNumberEntity entiy);

    SystemSerialNumberEntity findOneByCode(String code);

    SystemSerialNumberEntity find(String code);

}
