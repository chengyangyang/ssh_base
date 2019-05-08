package ch.common.ringqueue;

import java.util.Calendar;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 18:05
 * version 1.0
 */
public abstract class AbstractTask implements Runnable  {

    // 圈数
    private int cycle;

    // 当前指针
    private int index;

    // 订单编号
    private String order;

    public AbstractTask(String order, int after) {
        int second = Calendar.getInstance().get(Calendar.MINUTE) * 60 + Calendar.getInstance().get(Calendar.SECOND);
        this.index = (second + after) % 3600;
        this.cycle = after / 3600;
        this.order = order;
    }

    public int getCycle() {
        return this.cycle;
    }

    public void countDown() {
        this.cycle -= 1;
    }

    public int getIndex() {
        return index;
    }

    public String getOrder() {
        return order;
    }

}
