package ch.test.notes.designmodel;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月10日 13:09
 * version 1.0
 */
public class SmallCar extends Car {
    public SmallCar(String name, String color) {
        super(name, color);
    }

    @Override
    public void run() {
        System.out.println(color +" " + name  +"在高速的行驶。。。");
    }
}
