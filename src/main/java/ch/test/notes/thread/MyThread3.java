package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月08日 10:56
 * version 1.0
 */
public class MyThread3 extends Thread {

    private int count = 10;

    @Override
    synchronized public void run() {
        super.run();
        count--;
        System.out.println("由"+this.currentThread().getName()+"线程计算,count="+count);
    }
}
