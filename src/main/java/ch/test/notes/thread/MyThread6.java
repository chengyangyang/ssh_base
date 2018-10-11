package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月11日 10:51
 * version 1.0
 */
public class MyThread6 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(2000000);
            System.out.println("我是for语句外面的语句,线程并未停止");
        }catch (InterruptedException e){
            System.out.println("在沉睡中被中断"+this.isInterrupted());
        }


    }
}
