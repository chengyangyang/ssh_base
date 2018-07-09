package ch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class CommonUtil {
	
	public static List<String> sortListwithASCOrder(List<String> strToOrder){
		
		if( null != strToOrder ){
			Object[] obj = strToOrder.toArray();
			Arrays.sort(obj);
			List<String> list = new ArrayList<String>();
			for(Object str : obj){
				list.add(str.toString());
			}
			return list;
		}
		return null;
		
	}
	 public static boolean isEmpty(Object obj)  
	    {  
	        if (obj == null)  
	        {  
	            return true;  
	        }  
	        if ((obj instanceof List))  
	        {  
	            return ((List) obj).size() == 0;  
	        }  
	        if ((obj instanceof String))  
	        {  
	            return ((String) obj).trim().equals("");  
	        }  
	        return false;  
	    }  

}
