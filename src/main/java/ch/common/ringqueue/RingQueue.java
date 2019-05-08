package ch.common.ringqueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Title:环形队列
 * Description:
 * Author:
 * Since:2017/3/17
 * Version:1.1.0
 */
public class RingQueue extends AbstractRingQueue {

    private ScheduledExecutorService stepPool = Executors.newScheduledThreadPool(1);


    public RingQueue() {
        super();

    }

    @Override
    public StepSlot nextStep(int slotIndex) {
        return slot[slotIndex];
    }

    @Override
    public int add(Task task) {
        return slot[task.getIndex()].addTask(task);
    }

    @Override
    public void remove(int slotIndex, String order) {
        slot[slotIndex].reomve(order);
    }

    @Override
    public void replaceSlot(int slotIndex, Task task) {
        remove(slotIndex, task.getOrder());
        add(task);
    }

    @Override
    public void initData() {
        //TODO 初始化数据(根据业务)
    }

    public void start() {
        new Thread(new Steper(this)).start();
    }

}
