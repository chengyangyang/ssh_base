package ch.controller.user;

import ch.entity.exception.BusinessException;
import ch.entity.user.Person;
import ch.service.user.PersonService;

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
     * 添加测试信息@cy
     * */
    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    public void savePerson(){
        Person person = new Person();
        person.setCreateBy("测试人");
        personService.savePerson(person);
    }

    /*
     * 查询测试信息@cy
     * */
    @RequestMapping(value = "/getPerson", method = RequestMethod.POST)
    @ResponseBody
    public Person getPerson() throws BusinessException{
        return personService.getPerson("1");
    }
}