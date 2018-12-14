package ch.controller.user;

import ch.entity.user.MyTestEntity;
import ch.service.user.MyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mytest")
public class MyTestController {
    @Autowired
    private MyTestService myTestService;

    /*
     * 添加测试信息@cy
     * */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        MyTestEntity myTestEntity = new MyTestEntity();
        myTestEntity.setName("你好");
        myTestService.saveMyTest(myTestEntity);
        return "成功";
    }

}
