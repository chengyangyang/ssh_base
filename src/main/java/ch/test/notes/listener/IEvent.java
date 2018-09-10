package ch.test.notes.listener;

/**
 * Description:定义事件
 *
 * @author cy
 * @date 2018年08月31日 16:06
 * version 1.0
 */
public interface IEvent {

    void setEventListener(IEventListener arg);

    boolean ClickButton();

    boolean MoveMouse();
}
