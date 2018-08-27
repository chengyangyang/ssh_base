package ch.tool.sqltobean;

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
		//获得原始数据
		ResolverResult data = config.getResult();
		//java类的名称
		String className = config.getJavaClassName();		
		//类的注释
		String tableComment = data.getTableComment();
		//获得java类参数的结果
		List<FieldAndComment> fileds = config.getFileds();
		//获得java方法参数的结果
		List<FieldAndComment> methods = config.getMethods();

		//字段类型转化为java包的集合
		Set<String> typePackage = config.getJavaTypePackages();
		//注解的包名称
		Set<String> annotationNames = config.getAnnotationPackageNames();
		//获得注解
		List<AnnotationBean> annotationBeans = config.getAnnotationBean();
		/*构建参数start*/
		
		//类的名称
		
		//类的属性
		//构建方法
		//获得sql字段类型转化为java类型的包
		
		/*构建参数end*/
		
		StringBuilder content = new StringBuilder();
		content.append("package "+configParam.get("packageName")+";\n");//创建包名称
		
		content.append("");
		//创建类型包
		for (String string : typePackage) {
			content.append("\n"+"import "+string+";");
		}
		content.append("\n");
		//创建注解的包
		for (String string : annotationNames) {
			content.append("\n"+"import "+string+";");
		}
		
		content.append("\n");
		if(tableComment != null) {
			content.append(" \n/**\n *\n * "+tableComment+"\n *\n */");//创建表注释
		}
		//遍历头部的注解
		for (AnnotationBean annotationBean : annotationBeans) {
			if("0".equals(annotationBean.getLocation())) {
				content.append(" \n"+annotationBean.getName());//注解名称
			}			
		}
		
		content.append("\n"+"public class "+className+" {\n");//头部完成
		//添加参数
		for (int i = 0; i <fileds.size();i++) {
			FieldAndComment filed = fileds.get(i);
			String comm = "";
			if(filed.getComment() != null && filed.getComment() != "") {//添加注释
				comm = " // "+filed.getComment();
			}
			content.append("\n\t"+"private "+filed.getJavaType().getName()+" "+filed.getField()+";"+comm);//拼接属性
		}
		content.append("\n\n");
		//添加方法
		for (int i = 0; i <methods.size();i++) {
			FieldAndComment method = methods.get(i);
			//添加get方法
			String comm = "";
			if(method.getComment() != null && method.getComment() != "") {
				comm = " \n\t// "+method.getComment();
			}
			content.append(comm);
			
			//get方法的注解
			//遍历头部的注解
			for (AnnotationBean annotationBean : annotationBeans) {
				if("1".equals(annotationBean.getLocation())) {
					if("1".equals(annotationBean.getParamType())) {
						content.append(" \n\t"+MessageFormat.format(annotationBean.getName(),data.getFieldAndCommentList().get(i).getField()));//注解名称
					}
				    if(method.getJavaType().getName().equals(annotationBean.getReturnType())) {
				    	content.append(" \n\t"+annotationBean.getName());//注解名称
				    }
			
				}			
			}
			
			//添加get方法
			content.append("\n\t"+"public "+method.getJavaType().getName()+" get"+method.getField()+"(){");
			content.append("\n\t\t"+"return "+fileds.get(i).getField()+";\n\t}");
			
			//添加set方法
			content.append("\n\t"+"public void set"+method.getField()+"("+method.getJavaType().getName()+" "+fileds.get(i).getField()+"){");
			content.append("\n\t\t"+"return this."+fileds.get(i).getField()+" = "+fileds.get(i).getField()+";\n\t}");	
			content.append("\n");
		}
		content.append("\n}");
		//输出到本地
		File file = new File(configParam.get("exportDisk"));
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
