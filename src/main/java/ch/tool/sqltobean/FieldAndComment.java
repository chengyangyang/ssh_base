package ch.tool.sqltobean;

import java.util.List;

/**
 * sql解析内容的javaBean
 * @author Administrator
 *
 */
public class FieldAndComment {

	private String field; // 表字段
    private String type; // 字段类型
    private String comment;  // 字段注释
    
    private JavaTypeBean javaType; //转化为java的类型
    private List<AnnotationBean> annotations; //方法对应的注解
    
    
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
	public List<AnnotationBean> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<AnnotationBean> annotations) {
		this.annotations = annotations;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldAndComment other = (FieldAndComment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
    
	
    
}
