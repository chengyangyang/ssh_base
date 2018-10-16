package ch.test.notes.thread;

/**
 * Description:线程唤醒的测试
 *
 * @author cy
 * @date 2018年10月16日 9:55
 * version 1.0
 */
public class MyThread11 extends Thread {

    private Object lock;

    public MyThread11(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock){
            System.out.println("唤醒的开始时间:"+System.currentTimeMillis());
            lock.notify();
            System.out.println("唤醒的结束时间:"+System.currentTimeMillis());
        }

    }
}
