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
@Api(tags = "swagger控制层测试")
public class SwaggerController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "0 成功,其它为错误,返回格式：{code:0,data[{}]},data中的属性参照下方Model") })
    @ApiOperation(httpMethod = "GET", value = "个人信息")//swagger 当前接口注解
    public CommonReponse listCompound(
            @ApiParam(required = true, name = "start", value = "start") int start,
            int limit,
            @ApiParam(required = false, name = "userName", value = "名称模糊查询") String userName) {
        List<Person> data = new ArrayList<Person>();
        String msg = data.size() > 0 ? "" : "没有查询到相关记录";
        CommonReponse result = new CommonReponse();
        result.setMsg(msg);
        result.setCode("200");
        result.setData(data);
        return result;
    }


}
