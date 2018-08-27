package ch.tool.sqltobean;

import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

public class AddSqlTypeToJava {
	public static JavaTypeBean sqlTypeToJava(String type,String db) {
		if("mysql".equals(db.toLowerCase())) {
			Map<String, JavaTypeBean> map = getMysqlList();
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				if(type.toLowerCase().startsWith(string)) {
					return map.get(string);
				}
			}
			
		}
		return new JavaTypeBean(type,null);
	}
	
	public static Map<String,JavaTypeBean> getMysqlList(){
		Map<String,JavaTypeBean> map = new HashedMap<String,JavaTypeBean>();
		map.put("varchar", new JavaTypeBean("String",null)); 
		map.put("text", new JavaTypeBean("String",null));
		map.put("longtext", new JavaTypeBean("String",null));
		map.put("int", new JavaTypeBean("Integer",null));
		map.put("datetime", new JavaTypeBean("Date","java.util.Date"));
		return map;
	}
	
	
}
