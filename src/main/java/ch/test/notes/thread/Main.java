package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月08日 10:21
 * version 1.0
 */
public class Main {
    public static void main(String[] args) {
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

        //线程中断的测试
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println("ssssss");
    }
}
