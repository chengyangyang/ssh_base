package ch.test;

import ch.util.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月06日 15:19
 * version 1.0
 */
public class MyTest {

    public static void main(String[] args) {
    	String str = "a";
		String[] split = str.split("\\|");
		String s = "";
		for (int i = 0; i < split.length; i++) {
			if(i == split.length -1){
				s += "1"+split[i];
			}else {
				s += "1"+split[i]+"|";
			}

		}
		System.out.println("结束了");
	}
}
