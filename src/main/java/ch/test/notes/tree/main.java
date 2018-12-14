package ch.test.notes.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月11日 17:16
 * version 1.0
 */
public class main {

    public static void main(String[] args) {
        List<MyServer> list = new MyServer().getList();
        MyServer myServer = new MyServer();
        Map<String,MyServer> map = new HashMap<String,MyServer>();
        for(MyServer server:list){
            map.put(server.getCode(),server);
        }
        Set<Map.Entry<String,MyServer>> set = map.entrySet();
        //组装map
        for(Map.Entry<String,MyServer> ser :set){
            MyServer value = ser.getValue();
            if(null == value.getParentCode() || "0".equals(value.getParentCode())){
                myServer.getList1().add(value);
            }else {
               map.get(value.getParentCode()).getList1().add(value);
            }
        }

        System.out.println("--------------");

    }
}
