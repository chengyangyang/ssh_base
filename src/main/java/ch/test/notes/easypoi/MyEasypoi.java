package ch.test.notes.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2018年11月16日 14:54
 * version 1.0
 */
public class MyEasypoi {

    private String id;
    @Excel(name = "名称",height = 20,needMerge = true)
    private String name;
    @Excel(name = "年龄",height = 20,needMerge = true)
    private Integer age;
    @Excel(name = "金额",height = 20,needMerge = true)
    private Double sal;
    @Excel(name = "创建时间",height = 30,format = "yyyy-MM-dd",needMerge = true)
    private Date createDate;

    @ExcelCollection(name="苹果的里面")
    private List<MyEasypoi1> list;

    public MyEasypoi() {
    }

    public MyEasypoi(String id, String name, Integer age, Double sal, Date createDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sal = sal;
        this.createDate = createDate;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<MyEasypoi1> getList() {
        return list;
    }

    public void setList(List<MyEasypoi1> list) {
        this.list = list;
    }
}
