package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月12日 10:31
 * version 1.0
 */
public class MyThread8 extends Thread {

    private long i = 0;

    @Override
    public void run() {
        super.run();
        long count = 0;
        long startTime = System.currentTimeMillis();
        while(i < 500000){
            //Thread.yield();
            count += i;
            i++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime -startTime));

    }
}
