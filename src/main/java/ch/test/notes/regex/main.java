package ch.test.notes.regex;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月14日 17:16
 * version 1.0
 */
public class main {


    public static void main(String[] args) {
        String str =  "qwwe";
        boolean matches = str.matches("^(q)");
        System.out.println("是否匹配成功:"+matches);


    }
}
