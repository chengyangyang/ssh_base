package ch.test.notes.thread;


/**
 * Description:线程等待的测试
 *
 * @author cy
 * @date 2018年10月16日 9:26
 * version 1.0
 */
public class MyThread10 extends Thread {

    private Object lock;

    public MyThread10(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("等待执行前(同步代码块之外)");
            synchronized (lock){
                System.out.println("等待上面一条代码(在同步代码块中)");
                lock.wait();
                System.out.println("等待下面一条代码(在同步代码块中)");
            }
            System.out.println("等待执行后(同步代码块之外的)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
