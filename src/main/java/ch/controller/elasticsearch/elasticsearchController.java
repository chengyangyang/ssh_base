package ch.controller.elasticsearch;

import ch.entity.elasticsearch.ElasticSearchTestEntity;
import ch.repositories.ElasticSearchTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
*
 * Description:es的练习
 *
 * @author cy
 * @date 2018年12月28日 16:30
 * version 1.0
*/
@Controller
@RequestMapping(value = "/es")
public class elasticsearchController {

    @Autowired
    ElasticsearchTemplate es;
    @Autowired
    ElasticSearchTestRepository elasticSearchTestRepository;


    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String save(){
        ElasticSearchTestEntity myTestEntity = new ElasticSearchTestEntity();
        myTestEntity.setName("es新增的测试数据");
        myTestEntity.setId(2);
        //sr.save(myTestEntity);
        elasticSearchTestRepository.save(myTestEntity);
        //ss.save(myTestEntity);
        return "成功";
    }


}
