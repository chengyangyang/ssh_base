package ch.tool.sqltobean;

import java.util.List;
import java.util.Set;

public class ResolverResult {
	private String tableName;// 表名称
	private String tableComment;// 表注释
	private List<FieldAndComment> fieldAndCommentList; //内容
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldAndCommentList == null) ? 0 : fieldAndCommentList.hashCode());
		result = prime * result + ((tableComment == null) ? 0 : tableComment.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		ResolverResult other = (ResolverResult) obj;
		if (fieldAndCommentList == null) {
			if (other.fieldAndCommentList != null)
				return false;
		} else if (!fieldAndCommentList.equals(other.fieldAndCommentList))
			return false;
		if (tableComment == null) {
			if (other.tableComment != null)
				return false;
		} else if (!tableComment.equals(other.tableComment))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	
	

}
