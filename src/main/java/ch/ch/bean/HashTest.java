package ch.ch.bean;

import org.junit.Test;

public class HashTest {

	@Test
	public void hash(){
	   
		Hashqq a =	new Hashqq(1,2);
		Hashqq b =	a;//new Hashqq(1,2);
		
		String name = "foo";
		String name01 = "你好asfdfasdfafasdfasfasdvsdafasdfasdfadsfaasdfasa";
		int hashCode = name.hashCode();
		int hashCode01 = name.hashCode();
		System.out.println(name.hashCode()&0xf0);
		System.out.println("---------------------");
		System.out.println(a.hashCode()&0xf);
		System.out.println(b.hashCode()&0xf);
		System.out.println("---------------------");
		//System.out.println(null == b);
		System.out.println(a.equals(b));
		System.out.println(a == b);
		System.out.println("---------------------");
	}
}
