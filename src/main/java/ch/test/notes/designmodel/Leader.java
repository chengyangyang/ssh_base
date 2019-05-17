package ch.test.notes.designmodel;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 组长
 *
 * @author cy
 * @date 2019年05月10日 11:11
 * version 1.0
 */
public class Leader  implements ITarget  {

    private Map<String,ITarget> map = new HashMap<>();

    public Leader(){
        map.put("打扫",new TargetA());
        map.put("吃饭",new TargetB());
    }


    @Override
    public void doSomething(String command) {
        map.get(command).doSomething(command);
    }

    public static void main(String[] args) {
        new Leader().doSomething("吃饭");
        new Leader().doSomething("打扫");
    }
}
