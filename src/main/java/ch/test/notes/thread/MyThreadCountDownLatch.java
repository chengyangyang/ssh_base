package ch.test.notes.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Description: CountDownLatch 的使用
 *
 * @author cy
 * @date 2018年10月16日 10:52
 * version 1.0
 */
public class MyThreadCountDownLatch  {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"正在执行任务");
                    Thread.sleep(3000);
                    System.out.println("线程"+Thread.currentThread().getName()+"结束执行任务");
                    countDownLatch.countDown();
                }catch (Exception e){

                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"正在执行任务");
                    Thread.sleep(15000);
                    System.out.println("线程"+Thread.currentThread().getName()+"结束执行任务");
                    countDownLatch.countDown();
                }catch (Exception e){

                }
            }
        }.start();
        try {
            System.out.println("等待线程执行完毕");
            countDownLatch.await();
            System.out.println("线程执行完毕，继续执行主程序");
        }catch (Exception e){

        }


    }
}
