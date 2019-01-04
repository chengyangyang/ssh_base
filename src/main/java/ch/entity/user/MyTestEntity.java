package ch.entity.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月05日 16:45
 * version 1.0
 */
@Entity(name = "my_test")
public class MyTestEntity {

    private String id;
    private String name;

    @Id
    @GenericGenerator(name="system",strategy="uuid")
    @GeneratedValue(generator="system")
    @Column(name = "id",nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
