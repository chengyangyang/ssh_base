package ch.tool.sqltobean;

import java.util.List;
import java.util.Set;

public class ResolverResult {
	private String tableName;// 表名称
	private String className;//类的名称
	private String tableComment;// 表注释
	private List<FieldAndComment> fieldAndCommentList; //内容
	private List<AnnotationBean> annotations; //所有注解

	//对应的包
	private Set<String> javaTypeToPackage;//java 类型转化对应的包
	private Set<String> annotationsPackage;//注解对应的包


	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public List<FieldAndComment> getFieldAndCommentList() {
		return fieldAndCommentList;
	}
	public void setFieldAndCommentList(List<FieldAndComment> fieldAndCommentList) {
		this.fieldAndCommentList = fieldAndCommentList;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<AnnotationBean> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationBean> annotations) {
		this.annotations = annotations;
	}

	public Set<String> getJavaTypeToPackage() {
		return javaTypeToPackage;
	}

	public void setJavaTypeToPackage(Set<String> javaTypeToPackage) {
		this.javaTypeToPackage = javaTypeToPackage;
	}

	public Set<String> getAnnotationsPackage() {
		return annotationsPackage;
	}

	public void setAnnotationsPackage(Set<String> annotationsPackage) {
		this.annotationsPackage = annotationsPackage;
	}
}
