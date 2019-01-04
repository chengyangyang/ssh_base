package ch.repositories;


import ch.entity.elasticsearch.ElasticSearchTestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*
*
 * Description:
 *
 * @author cy
 * @date 2018年12月29日 17:44
 * version 1.0
*/
@NoRepositoryBean
public interface ElasticSearchTestRepository  extends CrudRepository<ElasticSearchTestEntity,Integer> {


}
