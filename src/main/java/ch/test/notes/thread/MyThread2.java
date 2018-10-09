package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月08日 10:47
 * version 1.0
 */
public class MyThread2 extends Thread {

    private int count = 5;

    //创建线程,并设置线程的名称
    public MyThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();
        while(count > 0){
            count--;
            System.out.println("由"+this.currentThread().getName()+"线程计算,count="+count);
        }

    }
}
