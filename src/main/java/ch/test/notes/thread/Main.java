package ch.test.notes.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:读<<[并发]java多线程编程核心技术>>总结
 *
 * @author cy
 * @date 2018年10月08日 10:21
 * version 1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        /*//使用继承的方法
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("继承形式的线程运行结束");

        //使用实现的方法
        MyThread1 myThread1 = new MyThread1();
        Thread myThread2 = new Thread(myThread1);
        myThread2.start();
        System.out.println("实现形式的线程运行结束");

        //数据的不共享测试
        MyThread2 a = new MyThread2("A");
        MyThread2 b = new MyThread2("B");
        MyThread2 c = new MyThread2("C");
        a.start();
        b.start();
        c.start();*/

        //共享数据的测试
        /*MyThread3 myThread3 = new MyThread3();
        Thread a1 = new Thread(myThread3, "A1");
        Thread b1 = new Thread(myThread3, "B1");
        Thread c1 = new Thread(myThread3, "C1");
        Thread d1 = new Thread(myThread3, "D1");
        a1.start();
        b1.start();
        c1.start();
        d1.start();*/

        //线程中断的测试1
        /*Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());*/

        //线程中断的测试2
       /* MyThread4 myThread4 = new MyThread4();
        myThread4.start();
        try {
            myThread4.sleep(2000);
            myThread4.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");*/

        //解决线程中断的测试3,解决线程中断后继续执行的测试(使用抛出异常可解决中断后,继续执行的问题)
       /* MyThread5 myThread5 = new MyThread5();
        myThread5.start();
        try {
            myThread5.sleep(2000);
            myThread5.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");*/

        //线程在沉睡中,被中断(将会进入异常中)
       /* MyThread6 myThread6 = new MyThread6();
        myThread6.start();
        try {
            myThread6.sleep(2000);
            myThread6.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");*/

       //线程的暂停和恢复(过期方法)
       /* MyThread7 myThread7 = new MyThread7();
        myThread7.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread7.suspend();
        System.out.println("线程暂停10秒钟");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        System.out.println("线程的唤醒");
        //如果线程不唤醒,name后面的线程暂停10秒钟,end,线程的唤醒将不能被打印出来,是因为println也是同步方法,里面的线程将会一直占着锁不放
        myThread7.resume();*/

        //yield 的使用(注释里面的Thread.yield();)
        /*MyThread8 myThread8 = new MyThread8();
        myThread8.start();*/

        //守护线程的示例
       /* MyThread9 myThread9 = new MyThread9();
        myThread9.setDaemon(true);//设置线程为守护线程
        myThread9.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开thead将不会再打印");*/

        //线程的等待和唤醒需要加入同步锁
       /* Object lock = new Object();
        MyThread10 myThread10 = new MyThread10(lock);
        myThread10.start();
        Thread.sleep(3000);
        MyThread11 myThread11 = new MyThread11(lock);
        myThread11.start();*/

       //ThreadLocal 为每一个线程存放自己的值
        MyThread12 myThread12 = new MyThread12();
        myThread12.start();

        //InheritableThreadLocal 可以让子线程从父线程中取值,同上面书写
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
        map1.remove("1");

    }

}
