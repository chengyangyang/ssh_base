package ch.tool.sqltobean;

public class JavaTypeBean {

	private String javaTypeName;//转化后的名称
	private String packageName;//转化后的包名称

	public JavaTypeBean() {
		
	}

	public JavaTypeBean(String javaTypeName, String packageName) {
		this.javaTypeName = javaTypeName;
		this.packageName = packageName;
	}

	public String getJavaTypeName() {
		return javaTypeName;
	}

	public void setJavaTypeName(String javaTypeName) {
		this.javaTypeName = javaTypeName;
	}

	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
