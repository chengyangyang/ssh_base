package ch.repositories;

import ch.entity.elasticsearch.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:
 *
 * @author cy
 * @date 2019年01月11日 13:22
 * version 1.0
 */
public interface EsStudentRepository extends ElasticsearchRepository<Student,String> {

}
