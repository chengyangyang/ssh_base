package ch.tool.sqltobean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddAnnotation {

	public static List<AnnotationBean> getAllAnnotation(){
		ArrayList<AnnotationBean> list = new ArrayList<AnnotationBean>();
		//添加定义注解
		
		AnnotationBean annotation1 = new AnnotationBean();
		annotation1.setName("@Entity");
		annotation1.setPackageName("javax.persistence.Entity");
		annotation1.setLocation("0");
		annotation1.setIsUse(true);
		annotation1.setParamType("1");
		annotation1.setReturnType(null);
		list.add(annotation1);
		
		AnnotationBean annotation2 = new AnnotationBean();
		annotation2.setName("@Table");
		annotation2.setPackageName("javax.persistence.Table");
		annotation2.setLocation("0");
		annotation2.setIsUse(true);
		annotation2.setParamType("1");
		annotation2.setReturnType(null);
		list.add(annotation2);
		
		AnnotationBean annotation3 = new AnnotationBean();
		annotation3.setName("@Column(name =\"{0}\")");
		annotation3.setPackageName("javax.persistence.Column");
		annotation3.setLocation("1");
		annotation3.setIsUse(true);
		annotation3.setParamType("1");
		annotation3.setReturnType(null);
		list.add(annotation3);		
		
		AnnotationBean annotation4 = new AnnotationBean();
		annotation4.setName("@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")");
		annotation4.setPackageName("com.fasterxml.jackson.annotation.JsonFormat");
		annotation4.setLocation("1");
		annotation4.setIsUse(true);
		annotation4.setParamType("0");
		annotation4.setReturnType("Date");
		list.add(annotation4);		
		return list;
		
	}
	
}
