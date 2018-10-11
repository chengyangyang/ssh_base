package ch.test.notes.thread;

/**
 * Description:
 *
 * @author cy
 * @date 2018年10月11日 10:42
 * version 1.0
 */
public class MyThread5 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if(this.interrupted()){
                    System.out.println("已经是停止状态了,我要进行停止");
                    throw new InterruptedException();

                }
                System.out.println("i="+i);

            }
            System.out.println("我是for语句外面的语句,线程并未停止");
        }catch (InterruptedException e){
            System.out.println("使用异常解决中断后,继续执行的问题");
        }


    }
}
