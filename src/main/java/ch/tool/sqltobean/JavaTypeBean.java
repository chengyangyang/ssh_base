package ch.tool.sqltobean;

public class JavaTypeBean {

	private String name;
	private String packageName;
	private String sqlTypeName;


	public JavaTypeBean() {
		
	}
	public JavaTypeBean(String name, String packageName) {
		this.name = name;
		this.packageName = packageName;
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
	public String getSqlTypeName() {
		return sqlTypeName;
	}
	public void setSqlTypeName(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}
	
	
	
	
	
}
