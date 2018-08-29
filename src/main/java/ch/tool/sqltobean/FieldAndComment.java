package ch.tool.sqltobean;

import java.util.List;

/**
 * sql解析内容的javaBean
 * @author Administrator
 *
 */
public class FieldAndComment {

	private String sqlFieldName; // 表字段
	private String javaFiledName;// 转化后的名称
    private String sqlType; // 字段类型
    private String comment;  // 字段注释
    private JavaTypeBean javaType; //转化为java的类型


	public String getSqlFieldName() {
		return sqlFieldName;
	}

	public void setSqlFieldName(String sqlFieldName) {
		this.sqlFieldName = sqlFieldName;
	}

	public String getJavaFiledName() {
		return javaFiledName;
	}

	public void setJavaFiledName(String javaFiledName) {
		this.javaFiledName = javaFiledName;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public JavaTypeBean getJavaType() {
		return javaType;
	}
	public void setJavaType(JavaTypeBean javaType) {
		this.javaType = javaType;
	}

}
