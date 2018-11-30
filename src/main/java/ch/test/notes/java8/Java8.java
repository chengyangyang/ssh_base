/*
package ch.test.notes.java8;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

*/
/**
 * Description:
 *
 * @author cy
 * @date 2018年08月29日 9:17
 * version 1.0
 *//*

public class Java8 {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);



        String a = null;
        Optional<String> name = Optional.ofNullable(a);
        System.out.println( "name是否有值 " + name.isPresent() );

    }



    public void lambdaTest(){
        //lambda一般只有一行代码
        // 相当于ArrayList集合
        List<Integer> integers = Arrays.asList(1, 1, 4,5,6,7,8);
        //进行遍历
        Arrays.asList(1, 1, 4,5,6,7,8).forEach(a -> System.out.println(a));
        //引用类的局部变流量,隐式转化为final,这样效率会更高,这个变量str将不能被改变
        String str = ",";           //等价于：final String str = ",";
        Arrays.asList( "a", "b", "c" ).forEach( e -> System.out.print( e + str ) );

        //进行排序
        List<Integer> list = Arrays.asList(4, 2, 1, 3);
        list.sort((a1,b1) -> a1.compareTo(b1));
        list.forEach(a -> System.out.println(a));
    }

}
*/
