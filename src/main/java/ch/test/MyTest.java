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

    	MyTest2 myTest2 = new MyTest2();
    	List<MyTest1> list = myTest2.getList();
    	for (MyTest1 myTest1 : list) {
			System.out.println(myTest1+"---"+myTest1.getA());
		}
    	
    	System.out.println("=====");
    	myTest2.changeList();
    	for (MyTest1 myTest1 : list) {
			System.out.println(myTest1 + "---"+myTest1.getA());
		}
    	
    	
    }
}
