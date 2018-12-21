package ch.controller.swagger;

import ch.bean.CommonReponse;
import ch.bean.Person;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月19日 9:06
 * version 1.0
 */
@Controller
@RequestMapping("/swagger")
@Api(tags = "swagger控制层测试",value = "api接口类",description = "测试")
public class SwaggerController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "测试接口")
    public CommonReponse listCompound(@ApiParam(value = "用户名称") String userName) {
        List<Person> data = new ArrayList<Person>();
        String msg = data.size() > 0 ? "" : "没有查询到相关记录";
        CommonReponse result = new CommonReponse();
        result.setMsg(msg);
        result.setCode("200");
        result.setData(userName);
        return result;
    }


}
