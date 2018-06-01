package ch.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trm.constants.SystemConstants;
import com.trm.entity.exception.BusinessException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Jackson处理一般的JavaBean和Json之间的转换只要使用ObjectMapper 对象的readValue和writeValueAsString两个方法就能实现。
 * 但是如果要转换复杂类型Collection如 List<YourBean>，那么就需要先反序列化复杂类型 为泛型的Collection Type
 * 
 * @description
 * @author shangwenshu
 * @Time 2016年10月13日
 */
public class JsonConvertUtil {
	
	static ObjectMapper mapper = null;
	
	public static synchronized ObjectMapper getMapInstance() {
			if (mapper == null) {
				mapper = new ObjectMapper();
			}
		return mapper;
	}
	
	/**
	 * 方法功能说明：  将json转化成object
	 * @参数： @param content
	 * @参数： @param valueType
	 * @参数： @return
	 * @参数： @throws JsonParseException
	 * @参数： @throws JsonMappingException
	 * @参数： @throws IOException      
	 * @return Object     
	 * @throws
	 */
	public static Object convertJsonToObject(String content, Class<?> valueType) throws JsonParseException, JsonMappingException, IOException {
		return getMapInstance().readValue(content, valueType);
	}
	
	/**
	 * 方法功能说明：  对象（简单对象 或者 list集合）转成JSON
	 * @参数： @param value
	 * @参数： @return
	 * @参数： @throws JsonProcessingException      
	 * @return String     
	 * @throws
	 */
	public static String convertObjectToJson(Object value) throws JsonProcessingException {
		return getMapInstance().writeValueAsString(value);
	}
	
	/**
	 * @throws BusinessException_Exception_C 
	 * 方法功能说明：  创建公共的
	 * @参数： @param content
	 * @参数： @throws JsonParseException
	 * @参数： @throws JsonMappingException
	 * @参数： @throws IOException      
	 * @return void     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> commonConvert(String content) throws BusinessException {
		try {
			Map<String, String> retMap = new HashMap<String, String>();
			Map<String, Object> mapBean = (Map<String, Object>)getMapInstance().readValue(content, Map.class);
			
			retMap.put("errorCode", mapBean.get("errorCode").toString());
			retMap.put("errorDesc", mapBean.get("errorDesc").toString());
			retMap.put("page_row", mapBean.get("page_row").toString());
			retMap.put("page", mapBean.get("page").toString());
			retMap.put("total", mapBean.get("total").toString());
			retMap.put("sort_column", mapBean.get("sort_column").toString());
			
			String contextJson = mapper.writeValueAsString(mapBean.get("context"));
			Map<String, Object> mapBean1 = (Map<String, Object>)getMapInstance().readValue(contextJson, Map.class);
			
			retMap.put("records", mapper.writeValueAsString(mapBean1.get("records")));
			// sumRecords 目前用于收支明细和虚拟币明细，其他模块没有
			if (mapBean1.get("sumRecords") != null) {
				retMap.put("sumRecords", mapper.writeValueAsString(mapBean1.get("sumRecords")));
			}
			
			return retMap;
		} catch (IOException e) {
			BusinessException bs = new BusinessException();
			bs.setExceptionCode(SystemConstants.REASONCODE_4011);
			bs.setExceptionMsg("调用C标段接口报错，或者C标段接口不可访问！error:" + e.getMessage());
			throw bs;
		}
	}
	
	public static boolean isSenseRecord(String records) {
		return !"[{}]".equals(records) && !"[]".equals(records);
	}
	
	public static Map<String, String> objectToMap(Object obj) {  
		if(obj == null)  
            return null;      
  
        Map<String, String> map = new HashMap<String, String>();   
  
        BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		 PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {    
	            String key = property.getName();    
	            if (key.compareToIgnoreCase("class") == 0) {   
	                continue;  
	            }  
	            Method getter = property.getReadMethod();  
	            Object value = getter!=null ? getter.invoke(obj) : null;  
	            map.put(key, value==null? "" : value.toString());  
	        }    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
       
  
        return map;  
    }    
	
	public static String replaceSpecial(String sourceChar) {
		return sourceChar.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	public static Map<String, String> xssFilterMethod(Object target)
			throws JsonParseException, JsonMappingException, IOException, JsonProcessingException {
		Map<String, String> entityMap = JsonConvertUtil.objectToMap(target);
		for (String key : entityMap.keySet()) {  
			entityMap.put(key, JsonConvertUtil.replaceSpecial(entityMap.get(key)));  
        }  
		return entityMap;
		
	}
	
}
