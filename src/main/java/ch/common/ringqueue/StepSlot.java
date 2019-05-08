package ch.common.ringqueue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 18:04
 * version 1.0
 */
public class StepSlot {

    private ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<Task> getTasks() {
        return tasks;
    }

    /**
     * 向槽内添加任务
     *
     * @param task 任务
     */
    int addTask(Task task) {
        tasks.add(task);
        return task.getIndex();
    }

    /**
     * 删除槽内某个taskId的任务
     *
     * @param order 任务id
     * @return 成功/失败
     */
    void reomve(String order) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().getOrder() == order) {
                it.remove();
            }
        }
    }

}
