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

}
