package ch.controller.user;

import ch.common.util.CacheUtils;
import ch.common.exceptions.bean.BusinessException;
import ch.entity.user.Person;
import ch.service.user.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mytest")
public class PersonController {
    @Autowired
    private PersonService personService;

    /*
     * 添加测试信息@cy
     * */
    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        //使用缓存
        CacheUtils.put("aa","你好");
        Person person = new Person();
        person.setCreateBy("测试人");
        personService.savePerson(person);
        return "成功";
    }

    /*
     * 查询测试信息@cy
     * */
    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    @ResponseBody
    public Person getPerson() throws BusinessException{
        System.out.println("缓存测试:"+CacheUtils.get("aa"));
        return personService.getPerson("1");
    }
}
