package ch.tool.sqltobean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResultFormat {


	//表名称进行格式转化
	public static String tableNameFormat(ResolverResult result,StringFormatConfig stringFormatConfig) {
		return new StringFormat().stringFormat(result.getTableName(),stringFormatConfig); 
	}
		
	//表字段进行格式化
	public static List<FieldAndComment> tableFiledFormat(List<FieldAndComment> list,StringFormatConfig stringFormatConfig) {
		if(list != null && list.size() >0) {
			for (FieldAndComment fieldAndComment : list) {
				String field = fieldAndComment.getField();
				fieldAndComment.setField(new StringFormat().stringFormat(field,stringFormatConfig));
				
				//类型的格式转化,如果为none就不进行转化
				if(!"none".equals(stringFormatConfig.getDb().toLowerCase())) {
					JavaTypeBean sqlTypeToJava = AddSqlTypeToJava.sqlTypeToJava(fieldAndComment.getType(), stringFormatConfig.getDb());
					sqlTypeToJava.setSqlTypeName(fieldAndComment.getType());
					fieldAndComment.setJavaType(sqlTypeToJava);
				}	
			}
		}
		return list; 
	}
	
}
