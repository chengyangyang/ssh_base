package ch.controller.elasticsearch;


import ch.entity.elasticsearch.Student;
import ch.repositories.EsStudentRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * Description:
 *
 * @author cy
 * @date 2019年01月11日 10:47
 * version 1.0
 */
@Controller
@RequestMapping(value = "/es/repository/student")
public class EsStudentRepositoryController {

    @Autowired
    private EsStudentRepository esStudentRepository;

    /**
     *方法都有
     * 1.单个保存  save方法能够进行更新操作  如果不传id,elasticsearch会自动创建id
     * 2.批量保存
     * 3.按照id单个查询
     * 4.index  方法和save方法相同
     */

   /* QueryBuilders

    QueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  // bool语句的封装  组合语句  and not  or
        QueryBuilders.termQuery(null,null);                 //精确查询  完全匹配
        QueryBuilders.termsQuery(null,1,2);              // 精确查询 批量匹配
        QueryBuilders.matchQuery(null,null);                //单个匹配 field不支持通配符, 前缀具高级特性
        QueryBuilders.matchAllQuery();                                  //查询所有
        QueryBuilders.multiMatchQuery("text","","");   //匹配多个字段, field有通配符忒行
        QueryBuilders.idsQuery();                                       //根据id查询
        QueryBuilders.constantScoreQuery(boolQueryBuilder).boost(12.12f); //包裹查询, 高于设定分数, 不计算相关性
        QueryBuilders.disMaxQuery();                                      // 对子查询的结果做union, score沿用子查询score的最大值,
        QueryBuilders.fuzzyQuery("","");                     //模糊查询 不能用通配符
        QueryBuilders.moreLikeThisQuery(new String[2]);                 //基于内容的查询
        QueryBuilders.boostingQuery();//它接受一个positive查询和一个negative查询。只有匹配了positive查询的文档才会被包含到结果集中，但是同时匹配了negative查询的文档会被降低其相关度，通过将文档原本的_score和negative_boost参数进行相乘来得到新的_score
        QueryBuilders.functionScoreQuery();                             //根据权重分查询
        QueryBuilders.rangeQuery();                                     //范围查询
        QueryBuilders.spanNearQuery()                                   //跨度查询
        QueryBuilders.wildcardQuery("user", "ki*hy")                    //通配符查询
        QueryBuilders.nestedQuery()                                     //嵌套查询
*/

    /**
     * 单表的保存,单表的保存   更新
     * @return
     */
    @RequestMapping(value = "/saveStudentTest", method = RequestMethod.GET)
    @ResponseBody
    public String saveTest(){
        Student student = new Student();
        student.setId("1");
        student.setAge(25);
        student.setBirthdayDate(new Date());
        student.setStudentName("张三");
        student.setIntroduce("张三是一个三好学生,孝敬父母,尊敬长辈,爱护同学.是班级的楷模,是值得学习的对象!");
        esStudentRepository.save(student);
        return "成功";
    }

    /**
     * 单表的保存,单表的保存  更新
     * @return
     */
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(@RequestBody Student student){
        esStudentRepository.save(student);
        return "成功";
    }

    /**
     * 根据id查询
     * @return
     */
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Student studentById(@PathVariable("id") String id){
        Student student = esStudentRepository.findById(id).get();
        return student;
    }

    /**
     * 查询 高亮显示没有效果
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST  )
    @ResponseBody
    public Page<Student> search(@RequestBody Map<String,Object> map){
        HighlightBuilder.Field introduce = new HighlightBuilder.Field("introduce").preTags("<em style='color:red'>").postTags("</em>").fragmentSize(100);
        NativeSearchQuery search = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(Integer.valueOf(map.get("pageNum")+""),Integer.valueOf(map.get("num")+"")))
                .withFields("introduce","age","studentName","birthdayDate")
                .withQuery(new MatchQueryBuilder("introduce",map.get("introduce")))
                //.withQuery(new MatchQueryBuilder("age",map.get("age")))
                .withHighlightBuilder(new HighlightBuilder().field(new HighlightBuilder.Field("introduce").preTags("<em style='color:red'>").postTags("</em>").fragmentSize(100)))
                .withHighlightFields(introduce)
                .withSourceFilter(new FetchSourceFilter(new String[]{"introduce","age", "birthdayDate","studentName"},new String[]{})) //这个是可以
                .build();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(search.getQuery());
        Page<Student> search1 = esStudentRepository.search(search);
        return search1;

    }

}
