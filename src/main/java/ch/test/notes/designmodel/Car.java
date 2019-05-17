package ch.test.notes.designmodel;

/**
 * Description:每个车都具有的相同的属性和行为
 *
 * @author cy
 * @date 2019年05月10日 12:38
 * version 1.0
 */
public class Car implements CarFunction {

    protected String name;            //车名字
    protected String color;            //车颜色

    public Car(String name, String color) {
                 this.name = name;
                 this.color = color;
    }

    @Override
    public void run() {
        System.out.println(color +" " + name  +"在行驶。。。");
    }
}
