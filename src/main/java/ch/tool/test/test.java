package ch.tool.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\([(^\\))]");
        Matcher matcher = p.matcher("行(aaaaa)政专员(袁文全)");
        if (matcher.find() && matcher.groupCount() >= 1){
            System.out.println(matcher.group(0));
        }

    }
}
