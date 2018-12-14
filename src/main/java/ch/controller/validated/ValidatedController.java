package ch.controller.validated;

import ch.bean.Person;
import ch.common.util.SendmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Description:
 *
 * @author cy
 * @date 2018年10月18日 13:57
 * version 1.0
 */
@Controller
@RequestMapping("/validate")
public class ValidatedController {


    /**
     * 测试校验(自动抛出异常)
     *
     */
    @ResponseBody
    @RequestMapping(value = "/autoExpre", method = RequestMethod.POST)
    public String sendEmail(@RequestBody @Validated Person person) {
      return "请求成功";
    }

    /**
     * 测试校验
     *
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<String> sendEmail(@RequestBody @Validated Person person, BindingResult bindingResult) {
        ArrayList<String> strings = new ArrayList<>();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if(!allErrors.isEmpty() && allErrors.size() > 0){
                for (ObjectError error : allErrors) {
                    strings.add(error.getDefaultMessage());
                }

            }
            return strings;
        }
        strings.add("请求成功!");
        return strings;
    }
}
