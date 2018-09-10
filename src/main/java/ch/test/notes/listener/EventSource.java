package ch.test.notes.listener;

/**
 * Description:事件发生源
 *
 * @author cy
 * @date 2018年08月31日 16:10
 * version 1.0
 */
public class EventSource implements IEvent {
    private IEventListener mEventListener;
    boolean button;
    boolean mouse;


    //注册监听器
    @Override
    public void setEventListener(IEventListener arg) {
        mEventListener = arg;
    }

    //触发事件
    public void mouseEventHappened(){
        mouse = true;
        mEventListener.doEvent(this);
    }

    @Override
    public boolean ClickButton() {
        return button;
    }

    @Override
    public boolean MoveMouse() {
        return mouse;
    }
}
