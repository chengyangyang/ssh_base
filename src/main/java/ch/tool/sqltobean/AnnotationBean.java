package ch.tool.sqltobean;

/**
 * 对注解的参数定义
 * @author Administrator
 *
 */
public class AnnotationBean {

	private String name;//注解名称
	private String packageName;//注解表名
	private String location;//注解位置 当前设置两种类型 0:为类头部注解,1为get方法上面注解
	private String returnType;// 注解的类型设置,对特定返回类型的设置,用在java类型中
	private Boolean isUse = false;//是否使用该注解
	private String paramType;// 0:表示,不在注解中传递参数,1 表示使用原始字段名称传递,2 使用类中字段名称传递
	private String methodName;//特定方法名称上面加注解

	private String type = "1";//当前只设置为1,代表hibernate的注解

	public Boolean getUse() {
		return isUse;
	}

	public void setUse(Boolean use) {
		isUse = use;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public Boolean getIsUse() {
		return isUse;
	}
	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
}
