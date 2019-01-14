package ch.controller.elasticsearch;

import ch.common.util.ElasticSearchUtils;
import ch.entity.elasticsearch.ElasticSearchTestEntity;
import ch.entity.elasticsearch.Student;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/*
*
 * Description:es的模板的使用,它是对repository的补充
 *
 * @author cy
 * @date 2018年12月28日 16:30
 * version 1.0
*/
@Controller
@RequestMapping(value = "/es")
public class elasticsearchTemplateController {

    @Autowired
    ElasticsearchTemplate te;


    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/saveTe", method = RequestMethod.GET)
    @ResponseBody
    public String saveTe(){
        ElasticSearchTestEntity myTestEntity = new ElasticSearchTestEntity();
        myTestEntity.setName("es新增的测试数据");
        myTestEntity.setId(3);
        IndexQuery build = new IndexQueryBuilder()
                .withId(myTestEntity.getId() + "")
                .withIndexName("mubanindexname")
                .withType("mubantype")
                .withObject(myTestEntity).build();
        String index = te.index(build);
        return index;
    }

    /**
     * 删除索引
     * @return
     */
    @RequestMapping(value = "/deleteIndex", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteIndex(){
        boolean b = te.deleteIndex(".kibana_1");
        return b;
    }
    /**
     * 分页 分词查询 查询  下面的组合并没有进行高亮的显示
     * @return
     */
    @RequestMapping(value = "/Search", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> Search(@RequestBody Map<String,Object> map){
        HighlightBuilder.Field introduce = new HighlightBuilder.Field("introduce").preTags("<em style='color:red'>").postTags("</em>").fragmentSize(100);
        NativeSearchQuery search = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(Integer.valueOf(map.get("pageNum")+""),Integer.valueOf(map.get("num")+"")))
                .withFields("introduce","age","studentName","birthdayDate")
                .withQuery(new MatchQueryBuilder("introduce",map.get("introduce")))
                //.withQuery(new MatchQueryBuilder("age",map.get("age")))   不能写两个,要不然后面会覆盖前面的
                .withHighlightBuilder(new HighlightBuilder().field(new HighlightBuilder.Field("introduce").preTags("<em style='color:red'>").postTags("</em>").fragmentSize(100)))
                .withHighlightFields(introduce)
                .withSourceFilter(new FetchSourceFilter(new String[]{"introduce","age", "birthdayDate","studentName"},new String[]{})) //这个是可以
                .build();
        AggregatedPage<Student> students = te.queryForPage(search, Student.class);
        List<Student> students1 = students.toList();
        return students1;
    }

    /**
     * 高亮的显示
     * @param map
     * @return
     */
    @RequestMapping(value = "/heightSearch", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> heightSearch(@RequestBody Map<String,Object> map){
        // 如果使用复杂的查询就需要使用 BoolQueryBuilder()
        //下面只是简单的查询
      NativeSearchQuery search = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(Integer.valueOf(map.get("pageNum")+""),Integer.valueOf(map.get("num")+"")))
                .withFields("introduce","age","studentName","birthdayDate")   //查询的时候的过滤 当有这个的时候,下面那个不起作用
                .withQuery(new MatchQueryBuilder("introduce",map.get("introduce")))
                //.withQuery(new MatchQueryBuilder("age",map.get("age")))   //不能写相同的两个,即使里面不相同,也不能重复 要不然后面会覆盖前面的
                .withHighlightBuilder(new HighlightBuilder().field(new HighlightBuilder.Field("introduce").preTags("<em style='color:red'>").postTags("</em>").fragmentSize(100)))
                //.withHighlightFields(introduce)
                .withSourceFilter(new FetchSourceFilter(new String[]{"introduce", "birthdayDate","studentName"},new String[]{})) //这个是可以
                .build();
        //查看查询的语句,相当于日志
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(search.getQuery());
        System.out.println("DSL语句------>"+query.toString());

        AggregatedPage<Student> students = te.queryForPage(search, Student.class, ElasticSearchUtils.highSearchResultMapper());
        if(null == students){
            return null;
        }
        List<Student> students1 = students.toList();
        return students1;
    }

}
