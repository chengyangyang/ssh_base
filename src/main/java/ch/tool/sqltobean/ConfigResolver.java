package ch.tool.sqltobean;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigResolver {

	private String sql;
	private ResolverResult result;
	private static Map<String, String> configParam;

	static {
		configParam = LoadConfig.getConfigParam();
	}
	
	//返回原始数据
	public ConfigResolver (String sql) {
		ResolverResult result = new ResolverSql().findFromSqlParam(sql);
		this.sql = sql;
		this.result = result;
	}
	
	//返回类的名称
	public  String getJavaClassName() {
		//对类的名称
		StringFormatConfig stringFormatConfig = new StringFormatConfig();
		if(configParam.get("head.stype.capital") != null){
			stringFormatConfig.setCapital(Boolean.valueOf(configParam.get("head.stype.capital")));
		}
		if(configParam.get("head.stype.toLowerCase") != null){
			stringFormatConfig.setToLowerCase(Boolean.valueOf(configParam.get("head.stype.toLowerCase")));
		}
		if(configParam.get("head.stype.hump") != null){
			stringFormatConfig.setHump(Boolean.valueOf(configParam.get("head.stype.hump")));
		}
		if(configParam.get("head.stype.remove_") != null){
			stringFormatConfig.setRemove_(Boolean.valueOf(configParam.get("head.stype.remove_")));
		}
		String className = ResultFormat.tableNameFormat(new ResolverSql().findFromSqlParam(sql),stringFormatConfig);
		return className;
	}
	
	
	public  ResolverResult getResult() {
		return result;
	}

	//获得参数的结果
	public List<FieldAndComment> getFileds() {
		//数据库类型会自动转化为java类型
		StringFormatConfig stringFormatConfig = new StringFormatConfig();
		if(configParam.get("filed.stype.capital") != null){
			stringFormatConfig.setCapital(Boolean.valueOf(configParam.get("filed.stype.capital")));
		}
		if(configParam.get("filed.stype.toLowerCase") != null){
			stringFormatConfig.setToLowerCase(Boolean.valueOf(configParam.get("filed.stype.toLowerCase")));
		}
		if(configParam.get("filed.stype.hump") != null){
			stringFormatConfig.setHump(Boolean.valueOf(configParam.get("filed.stype.hump")));
		}
		if(configParam.get("filed.stype.remove_") != null){
			stringFormatConfig.setRemove_(Boolean.valueOf(configParam.get("filed.stype.remove_")));
		}
		if(configParam.get("filed.db") != null){
			stringFormatConfig.setDb(configParam.get("filed.db"));
		}
		List<FieldAndComment> fileds = ResultFormat.tableFiledFormat(new ResolverSql().findFromSqlParam(sql).getFieldAndCommentList(),stringFormatConfig);
		return fileds;
	}

	//get方法的结果
	public List<FieldAndComment> getMethods() {
		//数据库类型会自动转化为java类型
		StringFormatConfig stringFormatConfig = new StringFormatConfig();
		if(configParam.get("getMethod.stype.capital") != null){
			stringFormatConfig.setCapital(Boolean.valueOf(configParam.get("getMethod.stype.capital")));
		}
		if(configParam.get("getMethod.stype.toLowerCase") != null){
			stringFormatConfig.setToLowerCase(Boolean.valueOf(configParam.get("getMethod.stype.toLowerCase")));
		}
		if(configParam.get("getMethod.stype.hump") != null){
			stringFormatConfig.setHump(Boolean.valueOf(configParam.get("getMethod.stype.hump")));
		}
		if(configParam.get("getMethod.stype.remove_") != null){
			stringFormatConfig.setRemove_(Boolean.valueOf(configParam.get("getMethod.stype.remove_")));
		}
		if(configParam.get("getMethod.db") != null){
			stringFormatConfig.setDb(configParam.get("getMethod.db"));
		}

		List<FieldAndComment> methods = ResultFormat.tableFiledFormat(new ResolverSql().findFromSqlParam(sql).getFieldAndCommentList(),stringFormatConfig);
		return methods;
	}

	//java类型对应的包
	public Set<String> getJavaTypePackages() {
		Set<String> set = new HashSet<>();
		if(!Boolean.valueOf(configParam.get("filed.auto.importPackage"))){
			return set;
		}
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

		String hibernateType = "0";
		if(!Boolean.valueOf(configParam.get("annotation.hibernate"))){
			hibernateType = "1";
		}

		//使用java8新特性进行过滤
		String type = hibernateType;
		List<AnnotationBean> collect = allAnnotation.stream().filter(a -> !a.getType().equals(type) && a.getIsUse()).collect(Collectors.toList());
		/*for (AnnotationBean annotationBean : allAnnotation) {
			if(!annotationBean.getIsUse()) {//去除不可用的注解
				allAnnotation.remove(annotationBean);
			}
		}*/
		return collect;
	}
	
	//得到注解的包名称
	public Set<String> getAnnotationPackageNames(){
		//HashSet<String> set = new HashSet<String>();

		String hibernateType = "0";
		if(!Boolean.valueOf(configParam.get("annotation.importPackage"))){
			hibernateType = "1";
		}
		//使用java8新特性进行过滤
		String type = hibernateType;
		List<AnnotationBean> allAnnotation = AddAnnotation.getAllAnnotation();
		Set<String> collect = allAnnotation.stream().filter(a -> !a.getType().equals(type) && a.getUse()).collect(Collectors.toList()).stream().map(AnnotationBean::getPackageName).collect(Collectors.toSet());

		/*for (AnnotationBean annotationBean : allAnnotation) {
			if(!annotationBean.getIsUse()) {//去除不可用的注解
				allAnnotation.remove(annotationBean);
			}else {
				set.add(annotationBean.getPackageName());
			}
		}*/
		return collect;
	}
	
}
