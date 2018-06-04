/*
package ch.ch.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

public class ClassTest {

	@Test
	public void classintext() throws Exception{
		try {
			Class<?> forName = Class.forName("com.ch.bean.Test");
			Field[] fields = forName.getFields();//寰楀埌鍏湁鐨勫睘鎬�
			
			System.out.println("ssss"+fields[0].getName());
			Object instance = forName.newInstance();
			Method[] declaredMethods = forName.getDeclaredMethods();//寰楀埌鍏湁鐨勬柟娉�
			for(int i = 0;i < declaredMethods.length;i++){
				System.out.println(declaredMethods[i].getName());
			}
			forName.getName();//寰楀埌鍙嶅皠绫荤殑鍚嶇О
			Constructor<?>[] constructors = forName.getConstructors();//寰楀埌鏋勯�犳柟娉�
			Date date = new Date();
			
			declaredMethods[5].setAccessible(true);//鍙栨秷璁块棶绉佹湁鏂规硶鐨勫悎娉曟�ф鏌� 
			
			System.out.println(declaredMethods[5].invoke(forName.newInstance(), null));//鎵ц鏂规硶
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
*/
