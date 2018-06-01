package ch.util;

import java.util.Random;

public class GenerateCodeUtil {	
	
	public static String generateCode(String prefix){
		return prefix + System.currentTimeMillis()+Math.round((new Random().nextDouble() * Math.pow(10, 2)));   
	}
	
	public static String generateCodeNormal(String prefix){
		return prefix + TrmDateUtil.transferLongToDate(null, System.currentTimeMillis())+Math.round((new Random().nextDouble() * Math.pow(10, 2)));   
	}
	
}
