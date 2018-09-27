package ch.test;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2018年09月27日 10:02
 * version 1.0
 */
public class MyTest3 {

    public static void main(String[] args) {
        String[] a = {"1","2","3","4"};
        boolean q = ArrayUtils.contains(a, "1");
        System.out.println(q);

        //优雅的创建一个带值的list集合
        List<String> strings = Arrays.asList("1", "2", "3");

        //优雅的创建带值的map集合
        HashMap<String, String> hashMap = new HashMap<String, String>() {
            {
                put("1","2");
                put("2","2");
                put("3","2");
            }
        };

    }
}
