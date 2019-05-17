package ch.test.notes.designmodel;

/**
 * Description: 人员工厂
 *
 * @author cy
 * @date 2019年05月10日 9:55
 * version 1.0
 */
public class Factory {

    public static Person1 getInstance(String type){
        Person1 person = null;
        if("1".equals(type)){
            person = new Student();
        }else {
            person = new Tearch();
        }
        return person;
    }
}
