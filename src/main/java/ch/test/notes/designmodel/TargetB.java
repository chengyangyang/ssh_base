package ch.test.notes.designmodel;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月10日 11:10
 * version 1.0
 */
public class TargetB implements  ITarget{

    @Override
    public void doSomething(String command) {
        System.out.println("我是员工B,开始做"+command+"工作");
    }
}
