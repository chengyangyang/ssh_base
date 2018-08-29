package ch.tool.sqltobean;

import java.util.HashSet;
import java.util.List;

public class ResultFormat {


	//表名称进行格式转化
	public static String tableNameFormat(ResolverResult result,StringFormatConfig stringFormatConfig) {
		return new StringFormat().stringFormat(result.getTableName(),stringFormatConfig); 
	}
		
	//表字段进行格式化
	public static void tableFiledFormat(ResolverResult result,StringFormatConfig stringFormatConfig) {
		List<FieldAndComment> list = result.getFieldAndCommentList();

		if(list != null && list.size() >0) {
			HashSet<String> set = new HashSet<>();
			for (FieldAndComment fieldAndComment : list) {
				//字段的转化
				fieldAndComment.setJavaFiledName(new StringFormat().stringFormat(fieldAndComment.getSqlFieldName(),stringFormatConfig));
				
				//类型的格式转化,如果为none就不进行转化
				if(!"none".equals(stringFormatConfig.getDb().toLowerCase())) {
					JavaTypeBean sqlTypeToJava = AddSqlTypeToJava.sqlTypeToJava(fieldAndComment.getSqlType(), stringFormatConfig.getDb());
					fieldAndComment.setJavaType(sqlTypeToJava);

					//导入类型转换的包
					set.add(sqlTypeToJava.getPackageName());

				}else {//如果不进行转化,则让转化后的类型,等于没有转化的
					JavaTypeBean javaTypeBean = new JavaTypeBean();
					javaTypeBean.setJavaTypeName(fieldAndComment.getSqlType());
					fieldAndComment.setJavaType(javaTypeBean);
				}
			}
			//设置类型转换的包
			result.setJavaTypeToPackage(set);
		}


	}
	
}
