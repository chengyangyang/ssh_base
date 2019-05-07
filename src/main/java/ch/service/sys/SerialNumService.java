package ch.service.sys;

import ch.entity.sys.SystemSerialNumberEntity;

import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 13:17
 * version 1.0
 */
public interface SerialNumService {

    public SystemSerialNumberEntity find(SystemSerialNumberEntity entity);

    public String generateSerialNumberByModelCode(String moduleCode);

}
