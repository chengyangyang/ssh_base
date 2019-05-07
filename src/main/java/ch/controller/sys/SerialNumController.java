package ch.controller.sys;

import ch.service.sys.SerialNumService;
import ch.service.user.MyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 15:23
 * version 1.0
 */
@Controller
@RequestMapping("/serialNum")
public class SerialNumController {

    @Autowired
    private SerialNumService serialNumService;


    /*
     * 添加测试信息@cy
     * */
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        String p = serialNumService.generateSerialNumberByModelCode("p");
        return p;
    }
}
