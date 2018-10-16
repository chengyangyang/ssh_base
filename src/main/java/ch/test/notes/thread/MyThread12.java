package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月16日 10:52
 * version 1.0
 */
public class MyThread12 extends Thread {

    private static ThreadLocal t = new ThreadLocal();

    @Override
    public void run() {
        super.run();
        if(t.get() == null){
            System.out.println("我没有存放过值");
            t.set("我的值");
        }
        System.out.println(t.get());
    }
}
