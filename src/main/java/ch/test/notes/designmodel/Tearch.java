package ch.test.notes.designmodel;

/**
 * Description:实现类
 *
 * @author cy
 * @date 2019年05月10日 9:53
 * version 1.0
 */
public class Tearch implements Person1 {

    @Override
    public String getHeight() {
        return "150";
    }

    @Override
    public String getWork() {
        return "我的职位是教师！";
    }
}
