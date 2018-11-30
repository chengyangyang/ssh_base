package ch.test.notes.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Description:
 *
 * @author cy
 * @date 2018年11月16日 15:52
 * version 1.0
 */
public class MyEasypoi1 {

    @Excel(name = "苹果核")
    private String name;
    @Excel(name = "苹果核大小")
    private Integer age;

    public MyEasypoi1() {
    }

    public MyEasypoi1(String name, Integer age) {
        this.name = name;
        this.age = age;
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
}
