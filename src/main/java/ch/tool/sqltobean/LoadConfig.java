package ch.tool.sqltobean;

import java.io.InputStream;
import java.util.*;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月27日 10:16
 * version 1.0
 */
public class LoadConfig {


    public static Map<String,String> getConfigParam(){
        Map<String, String> map = new HashMap<String, String>();
        try {
            Properties properties = new Properties();
            InputStream in = LoadConfig.class.getResourceAsStream("config.properties");
            properties.load(in);
            in.close();
            Set<String> strings = properties.stringPropertyNames();
            for (String str : strings) {
                map.put(str,properties.getProperty(str));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

}
