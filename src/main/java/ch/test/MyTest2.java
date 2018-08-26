package ch.test;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

public class MyTest2 {

	private List<MyTest1> list;

	
	
	
	public MyTest2() {
		List<MyTest1> list = new ArrayList<>();
		list.add(new MyTest1("1","a","A"));
		list.add(new MyTest1("2","b","B"));
		list.add(new MyTest1("3","c","C"));
		this.list = list;	
	}
	
	
	public List<MyTest1> changeList(){
		list.get(0).setA("99999");
		 list.add(new MyTest1("4","d","D"));
		 return null;
	}
	
	public List<MyTest1> getList() {
		return list;
	}

	public void setList(List<MyTest1> list) {
		this.list = list;
	}
	
	
}
