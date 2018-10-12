package ch.test.notes.thread;

/**
 * Description:线程的暂停和恢复
 *
 * @author cy
 * @date 2018年10月12日 9:38
 * version 1.0
 */
public class MyThread7 extends Thread {

    private long i = 0;

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i="+i++);
        }

    }
}
