package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月08日 10:23
 * version 1.0
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("my-thread");
    }
}
