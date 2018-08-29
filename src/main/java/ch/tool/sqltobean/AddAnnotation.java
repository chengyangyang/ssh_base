package ch.tool.sqltobean;

import java.util.ArrayList;
import java.util.List;


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
		annotation1.setType("1");
		list.add(annotation1);
		
		AnnotationBean annotation2 = new AnnotationBean();
		annotation2.setName("@Table");
		annotation2.setPackageName("javax.persistence.Table");
		annotation2.setLocation("0");
		annotation2.setIsUse(true);
		annotation2.setParamType("1");
		annotation2.setReturnType(null);
		annotation2.setType("1");
		list.add(annotation2);
		
		AnnotationBean annotation3 = new AnnotationBean();
		annotation3.setName("@Column(name =\"{0}\")");
		annotation3.setPackageName("javax.persistence.Column");
		annotation3.setLocation("1");
		annotation3.setIsUse(true);
		annotation3.setParamType("1");
		annotation3.setReturnType(null);
		annotation3.setType("1");
		list.add(annotation3);		
		
		AnnotationBean annotation4 = new AnnotationBean();
		annotation4.setName("@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")");
		annotation4.setPackageName("com.fasterxml.jackson.annotation.JsonFormat");
		annotation4.setLocation("1");
		annotation4.setIsUse(true);
		annotation4.setParamType("0");
		annotation4.setReturnType("Date");
		annotation4.setType("0");
		list.add(annotation4);

		AnnotationBean annotation5 = new AnnotationBean();
		annotation5.setName("@Id");
		annotation5.setPackageName("javax.persistence.Id");
		annotation5.setLocation("1");
		annotation5.setIsUse(true);
		annotation5.setParamType(null);
		annotation5.setReturnType(null);
		annotation5.setMethodName("id");
		annotation5.setType("1");
		list.add(annotation5);

		AnnotationBean annotation6 = new AnnotationBean();
		annotation6.setName("@GeneratedValue(strategy = IDENTITY)");
		annotation6.setPackageName("static javax.persistence.GenerationType.IDENTITY");
		annotation6.setLocation("1");
		annotation6.setIsUse(true);
		annotation6.setParamType(null);
		annotation6.setReturnType(null);
		annotation6.setMethodName("id");
		annotation6.setType("1");
		list.add(annotation6);


		return list;
		
	}
	
}
