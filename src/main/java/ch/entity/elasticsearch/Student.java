package ch.entity.elasticsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


/**
 * Description:
 *
 * @author cy
 * @date 2019年01月11日 11:08
 * version 1.0
 */
@Document(indexName = "zxmd",type= "student")
public class Student {

    @Id
    private String id;

    @Field(type =FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String studentName;

    private Integer age;

    @Field(type =FieldType.Date,format = DateFormat.year_month_day)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthdayDate;

    @Field(type =FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String introduce;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
