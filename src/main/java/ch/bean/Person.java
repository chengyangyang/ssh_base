package ch.bean;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Description:
 *
 * @author cy
 * @date 2018年11月30日 15:44
 * version 1.0
 */
public class Person {

    private String id;
    private String name;
    @Email(message = "邮件格式不正确")
    @Size(min = 10,max = 20 ,message = "长度在3-4之间")
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
