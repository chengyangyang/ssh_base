package ch.tool.sqltobean;

import ch.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqlToBean {
	private static Map<String, String> configParam;
	static {
		 configParam = LoadConfig.getConfigParam();
	}

	public static void sqlToBean(String sql) {
		//初始化数据
		ConfigResolver config = new ConfigResolver(sql);
		//获得数据合集
		ResolverResult data = config.getResult();
		/*构建参数end*/
		
		StringBuilder content = new StringBuilder();
		if(StringUtils.isNotBlank(configParam.get("packageName"))){
			content.append("package "+configParam.get("packageName")+";\n");//创建包名称
		}

		content.append("");

		//创建类型包
		Set<String> javaTypeToPackage = data.getJavaTypeToPackage();
		if(javaTypeToPackage != null && javaTypeToPackage.size() >0){
			for (String string : javaTypeToPackage) {
				if(StringUtils.isNotBlank(string)){
					content.append("\n"+"import "+string+";");
				}
			}
			content.append("\n");
		}

		//创建注解的包
		Set<String> annotationsPackage = data.getAnnotationsPackage();
		if(annotationsPackage != null && annotationsPackage.size() >  0){
			for (String string : annotationsPackage) {
				if(StringUtils.isNotBlank(string)){
					content.append("\n"+"import "+string+";");
				}
			}
			content.append("\n");
		}


		if(data.getTableComment() != null && data.getTableComment() != "") {
			content.append(" \n/**\n *\n * "+data.getTableComment()+"\n *\n */");//创建表注释
		}
		//遍历头部的注解
		if(data.getAnnotations() != null && data.getAnnotations().size() > 0){
			for (AnnotationBean annotationBean : data.getAnnotations()) {
				if("0".equals(annotationBean.getLocation())) {
					content.append(" \n"+annotationBean.getName());//注解名称
				}
			}
		}

		content.append("\n"+"public class "+data.getClassName()+" {\n");//类名称头部完成

		//添加参数
		List<FieldAndComment> fieldAndCommentList = data.getFieldAndCommentList();
		for (int i = 0; i <fieldAndCommentList.size();i++) {
			FieldAndComment filed = fieldAndCommentList.get(i);
			String comm = "";
			if(filed.getComment() != null && filed.getComment() != "") {//添加注释
				comm = " // "+filed.getComment();
			}
			content.append("\n\t"+"private "+filed.getJavaType().getJavaTypeName()+" "+filed.getJavaFiledName()+";"+comm);//拼接属性
		}
		content.append("\n\n");

		//添加方法

		//get添加注释
		for (int i = 0; i <fieldAndCommentList.size();i++) {
			FieldAndComment method = fieldAndCommentList.get(i);
			//添加get方法
			String comm = "";
			if(method.getComment() != null && method.getComment() != "") {
				comm = " \n\t// "+method.getComment();
			}
			content.append(comm);
			
			//get方法的注解
			//遍历头部的注解
			if(data.getAnnotations() != null && data.getAnnotations().size() > 0){
				for (AnnotationBean annotationBean : data.getAnnotations()) {
					if("1".equals(annotationBean.getLocation())) {//遍历get方法注解
						if("1".equals(annotationBean.getParamType())) {//一个参数的进行添加
							content.append(" \n\t"+MessageFormat.format(annotationBean.getName(),data.getFieldAndCommentList().get(i).getSqlFieldName()));//注解名称
						}else if(method.getJavaType().getJavaTypeName().equals(annotationBean.getReturnType())) {//对特定类型的添加
							content.append(" \n\t"+annotationBean.getName());//注解名称

						}else if(method.getJavaFiledName().toLowerCase().equals(annotationBean.getMethodName())) {//对特定方法名称的添加
							content.append(" \n\t"+annotationBean.getName());//注解名称
						}

					}
				}
			}
			//添加get方法
			content.append("\n\t"+"public "+method.getJavaType().getJavaTypeName()+" get"+ StringUtils.capitalize(method.getJavaFiledName())+"(){");
			content.append("\n\t\t"+"return "+method.getJavaFiledName()+";\n\t}");
			
			//添加set方法
			content.append("\n\t"+"public void set"+StringUtils.capitalize(method.getJavaFiledName())+"("+method.getJavaType().getJavaTypeName()+" "+method.getJavaFiledName()+"){");
			content.append("\n\t\t"+"this."+method.getJavaFiledName()+" = "+method.getJavaFiledName()+";\n\t}");
			content.append("\n");
		}
		content.append("\n}");
		//输出到本地
		String path = "c://";
		if(configParam.get("exportDisk") != null){
			path = configParam.get("exportDisk");
		}

		File file = new File(path+data.getClassName()+".java");
		FileOutputStream out;
		try {
			byte[] bytes = content.toString().getBytes();
			out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
