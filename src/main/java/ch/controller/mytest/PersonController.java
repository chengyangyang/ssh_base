package ch.controller.mytest;

import ch.entity.mytest.Person;
import ch.service.mytest.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mytest")
public class PersonController {
    @Autowired
    private PersonService personService;

    /*
     * 添加更新财务信息@cy
     * */
    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    public void savePerson(){
        Person person = new Person();
        person.setCreateBy("测试人");
        personService.savePerson(person);
    }
}
