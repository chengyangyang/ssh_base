package ch.test.notes.designmodel;

/**
 * Description: 单例模式 （懒汉模式）线程不安全
 *
 * @author cy
 * @date 2019年05月10日 9:33
 * version 1.0
 */
public class Singelon {

    private static Singelon si = null;

    private Singelon() { // 将构造方法封装为私有化
    }

    public static Singelon getInstance() {
        if(si == null){
            si = new Singelon();
        }
        return si;
    }

    public void print(){
        System.out.println("打印--------->");
    }
}
