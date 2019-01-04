package ch.entity.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月29日 17:04
 * version 1.0
 */
@Document(indexName = "elasticSearchTest1",type = "elasticSearchTest")
public class ElasticSearchTestEntity implements Serializable {



    @Id
    private int id;
    private String name;
    private String phone;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
