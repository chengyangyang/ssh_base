package ch.tool.sqltobean;


import java.util.*;


public class ConfigResolver {

	private String sql;
	private ResolverResult result;
	private static Map<String, String> configParam;

	static {
		configParam = LoadConfig.getConfigParam();
	}
	
	//数据的初始化
	public ConfigResolver (String sql) {
		ResolverResult result = new ResolverSql().findFromSqlParam(sql);
		this.sql = sql;
		this.result = result;

		putJavaClassName();
		putFileds();
		getAnnotationBean();
	}
	
	//类的名称进行转换
	public  void putJavaClassName() {
		//对类的名称
		StringFormatConfig stringFormatConfig = new StringFormatConfig(false,true,true,true);
		String className = ResultFormat.tableNameFormat(result,stringFormatConfig);
		result.setClassName(className);
	}
	
	
	public  ResolverResult getResult() {
		return result;
	}

	//类的参数进行转换
	public void putFileds() {
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
		ResultFormat.tableFiledFormat(result,stringFormatConfig);

	}


	//查询所有可使用的注解
	public void getAnnotationBean(){
		Boolean common = false;
		Boolean hibernate = false;
		if(Boolean.valueOf(configParam.get("annotation.hibernate"))){
			hibernate = Boolean.valueOf(configParam.get("annotation.hibernate"));
		}
		if(Boolean.valueOf(configParam.get("annotation.common"))){
			common = Boolean.valueOf(configParam.get("annotation.common"));
		}


		List<AnnotationBean> allAnnotation = AddAnnotation.getAllAnnotation();
		List<AnnotationBean> newAllAnnotation = new ArrayList<>();

		Set<String> set = new HashSet<>();
		//使用java8新特性进行过滤
		//String type = hibernateType;
		//List<AnnotationBean> collect = allAnnotation.stream().filter(a -> !a.getType().equals(type) && a.getIsUse()).collect(Collectors.toList());
		for (AnnotationBean annotationBean : allAnnotation) {
			if(!annotationBean.getUse()) {//去除不可用的注解
				continue;
			}
			if(!hibernate && "1".equals(annotationBean.getType())) {//去除不可用的hibernate注解
				continue;
			}
			if(!common && "0".equals(annotationBean.getType())) {//去除通用注解
				continue;
			}
			//添加可用的注解
			newAllAnnotation.add(annotationBean);
			//添加注解的包
			set.add(annotationBean.getPackageName());
		}
		result.setAnnotations(newAllAnnotation);
		result.setAnnotationsPackage(set);
		//return collect;
	}

}
