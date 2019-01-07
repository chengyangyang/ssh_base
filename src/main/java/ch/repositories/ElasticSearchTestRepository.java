package ch.repositories;


import ch.entity.elasticsearch.ElasticSearchTestEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

/*
*
 * Description:
 *
 * @author cy
 * @date 2018年12月29日 17:44
 * version 1.0
*/
public interface ElasticSearchTestRepository  extends ElasticsearchRepository<ElasticSearchTestEntity,Integer> {


    //自定义接口
    //在ElasticsearchRepository中我们可以使用Not Add Like Or Between等关键词自动创建查询语句。
    //关键字  And Or  Is Not  Between  LessThanEqual    GreaterThanEqual  Before
    //After  Like  StartingWith    EndingWith   Contains/Containing  In   NotIn  True  False  OrderBy

    /*List<Book> findByNameAndPrice(String name, Integer price);*/

    /*List<Book> findByNameOrPrice(String name, Integer price);*/

    /*Page<Book> findByName(String name,Pageable page);*/

    /*Page<Book> findByNameNot(String name,Pageable page);*/

    /*Page<Book> findByPriceBetween(int price,Pageable page);*/

    /*Page<Book> findByNameLike(String name,Pageable page);*/

   /* @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<Book> findByMessage(String message, Pageable pageable);*/

}
