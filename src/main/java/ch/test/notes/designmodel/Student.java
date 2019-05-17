package ch.test.notes.designmodel;

/**
 * Description: 实现类
 *
 * @author cy
 * @date 2019年05月10日 9:52
 * version 1.0
 */
public class Student implements Person1 {

    @Override
    public String getHeight() {
        return "12";
    }

    @Override
    public String getWork() {
        return "我的职位是学生！";
    }
}
