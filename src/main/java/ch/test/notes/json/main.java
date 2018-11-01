package ch.test.notes.json;


import ch.util.StringUtils;

/**
 * Description:
 *
 * @author cy
 * @date 2018年11月01日 10:22
 * version 1.0
 */
public class main {

    public static void main(String[] args) {
        String a = null;
        String b = "";
        String c = "   ";
        String d = "1";
        String e = "1";

        System.out.println("isnotblank+null"+ StringUtils.isNotBlank(a));
        System.out.println("isnotblank+空字符串"+ StringUtils.isNotBlank(b));
        System.out.println("isnotblank+空格"+ StringUtils.isNotBlank(c));

        System.out.println("isNotEmpty+null"+ StringUtils.isNotEmpty(a));
        System.out.println("isNotEmpty+空字符串"+ StringUtils.isNotEmpty(b));
        System.out.println("isNotEmpty+空格"+ StringUtils.isNotEmpty(c));

        System.out.println("isNoneBlank+null"+ StringUtils.isAnyBlank(c,d));


    }
}
