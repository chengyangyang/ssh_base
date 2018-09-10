package ch.test.notes.listener;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月31日 16:14
 * version 1.0
 */
public class EventSource2 implements IEvent {
    private IEventListener ml;
    boolean button;
    boolean mouse;

    @Override
    public void setEventListener(IEventListener arg) {
        ml = arg;
    }

    @Override
    public boolean ClickButton() {
        // TODO Auto-generated method stub
        return button;
    }

    @Override
    public boolean MoveMouse() {
        // TODO Auto-generated method stub
        return mouse;
    }

    // 触发事件
    public void buttonEventHappened() {
        button = true;
        ml.doEvent(this);
    }
}
