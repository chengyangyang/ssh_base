package ch.test.notes.thread;

/**
 * Description:停止线程的测试
 *
 * @author cy
 * @date 2018年10月09日 9:17
 * version 1.0
 */
public class MyThread4 extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if(this.interrupted()){
                System.out.println("已经是停止状态了,我要进行停止");
                break;
            }
            System.out.println("i="+i);

        }
        System.out.println("我是for语句外面的语句,线程并未停止");
    }
}
