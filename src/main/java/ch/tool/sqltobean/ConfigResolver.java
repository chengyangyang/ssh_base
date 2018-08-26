package ch.tool.sqltobean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigResolver {

	private String sql;
	private ResolverResult result;
	
	//返回原始数据
	public ConfigResolver (String sql) {
		ResolverResult result = new ResolverSql().findFromSqlParam(sql);
		this.sql = sql;
		this.result = result;
	}
	
	//返回类的名称
	public  String getJavaClassName() {
		String className = ResultFormat.tableNameFormat(new ResolverSql().findFromSqlParam(sql),StringFormatConfig.setConfig(false, true, true, true));
		return className;
	}
	
	
	public  ResolverResult getResult() {
		return result;
	}

	//获得参数的结果
	public List<FieldAndComment> getFileds() {
		//数据库类型会自动转化为java类型
		List<FieldAndComment> fileds = ResultFormat.tableFiledFormat(new ResolverSql().findFromSqlParam(sql).getFieldAndCommentList(),StringFormatConfig.setConfig(false, false, true, true));
		return fileds;
	}

	//get方法的结果
	public List<FieldAndComment> getMethods() {
		List<FieldAndComment> methods = ResultFormat.tableFiledFormat(new ResolverSql().findFromSqlParam(sql).getFieldAndCommentList(),StringFormatConfig.setConfig(false, true, true, true));
		return methods;
	}

	//java类型对应的包
	public Set<String> getJavaTypePackages() {
		Set<String> set = new HashSet<>();
		List<FieldAndComment> list = new ResolverSql().findFromSqlParam(sql).getFieldAndCommentList();
		if(list != null && list.size() >0) {
			for (FieldAndComment fieldAndComment : list) {
				String field = fieldAndComment.getField();
				//类型的格式转化,如果为none就不进行转化
				if(!"none".equals( new StringFormatConfig().getDb().toLowerCase())) {
					JavaTypeBean sqlTypeToJava = AddSqlTypeToJava.sqlTypeToJava(fieldAndComment.getType(), new StringFormatConfig().getDb());
					sqlTypeToJava.setSqlTypeName(fieldAndComment.getType());
					String packageName = sqlTypeToJava.getPackageName();
					if(packageName != null && packageName != "") {
						set.add(packageName);
					}
				}	
			}
		}
		return set;
	}
	
	//查询所有可使用的注解
	public List<AnnotationBean> getAnnotationBean(){
		List<AnnotationBean> allAnnotation = AddAnnotation.getAllAnnotation();
		for (AnnotationBean annotationBean : allAnnotation) {
			if(!annotationBean.getIsUse()) {//去除不可用的注解
				allAnnotation.remove(annotationBean);
			}
			
		}
		return allAnnotation;
	}
	
	//得到注解的包名称
	public Set<String> getAnnotationPackageNames(){
		HashSet<String> set = new HashSet<String>();
		List<AnnotationBean> allAnnotation = AddAnnotation.getAllAnnotation();
		for (AnnotationBean annotationBean : allAnnotation) {
			if(!annotationBean.getIsUse()) {//去除不可用的注解
				allAnnotation.remove(annotationBean);
			}else {
				set.add(annotationBean.getPackageName());
			}
		}
		return set;
	}
	
}
