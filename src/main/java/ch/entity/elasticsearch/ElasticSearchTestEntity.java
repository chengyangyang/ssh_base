package ch.entity.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月29日 17:04
 * version 1.0
 */
@Document(indexName = "elasticsearchtest1",type = "elasticsearchtest")
public class ElasticSearchTestEntity implements Serializable {

    @Id
    private int id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field
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
