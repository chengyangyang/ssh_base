package ch.common.ringqueue;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 18:14
 * version 1.0
 */
public abstract class Task extends AbstractTask {

    public Task(String order, int after) {
        super(order, after);
    }

}
