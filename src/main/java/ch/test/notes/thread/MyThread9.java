package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月12日 11:01
 * version 1.0
 */
public class MyThread9 extends Thread {

    private long i = 0;

    @Override
    public void run() {
        super.run();
        while (true){
            i++;
            System.out.println("i="+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
