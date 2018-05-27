package ch.ch.bean;

import java.util.UUID;

import org.junit.Test;

public class Uuid {

	@Test
	public void uuid(){
		String string = UUID.randomUUID().toString();
		System.out.println(string.replace("-", ""));
	}
}
