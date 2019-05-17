package ch.test.notes.thread;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月16日 10:52
 * version 1.0
 */
public class MyThread12 extends Thread {

    public static void main(String [] args) throws InterruptedException {
        // 有界队列 ArrayBlockingQueue
        // 无界队列 LinkedBlockingQueue
        // 优先队列 PriorityBlockingQueue
        // 拒绝策略 AbortPolicy 直接抛出异常
        // 拒绝策略 CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
        // 拒绝策略 DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交
        // 拒绝策略 该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
        // 拒绝策略  也可以自己设置拒绝策略 new RejectedExecutionHandler()
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, // 指定线程池中，线程数量
                5, // 指定最大的线程数量 只有当workQueue满时，才创建新的线程
                1000, // 多长时间销毁空闲的线程数量，多于corePoolSize的销毁
                TimeUnit.MILLISECONDS, // 时间的单位
                new ArrayBlockingQueue<Runnable>(20),  // 有界队列 无界队列  有限队列
                Executors.defaultThreadFactory(), // 用于创建线程  默认即可
                new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略，用户任务来不及处理，如何拒绝
        );
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new ThreadJoinTest(i+"------>"));
        }

    }

}
class ThreadJoinTest implements Runnable{
    private String name;
    public ThreadJoinTest(String name){
        this.name = name;
    }
    @Override
    public void run(){
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<1000;i++){
            System.out.println(this.name + ":" + i);
        }
    }
}
