package ch.test.notes.designmodel;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月10日 13:11
 * version 1.0
 */
public class BussCar extends Car {

    public BussCar(String name, String color) {
        super(name, color);
    }

    @Override
    public void run() {
        System.out.println(color +" " + name  +"在缓慢的行驶。。。");
    }


}
