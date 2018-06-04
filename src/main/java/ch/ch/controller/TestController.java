/*
package ch.ch.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.ch.service.TestService;
import ch.ch.service.TestServiceImpl;

@Controller
@RequestMapping("/t")
public class TestController {

	@Autowired
	TestServiceImpl test;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
		System.out.println("nihao");
//        实际返回的是views/test.jsp ,spring-mvc.xml中配置过前后缀
        return "test";
	}
	
	@RequestMapping(value = "/springtest", method = RequestMethod.GET)
    public String springtest(){
		System.out.println(test.test());
        return test.test();
	}
	
	@RequestMapping(value = "/trantest", method = RequestMethod.GET)
    public String trantest(){
		test.gettest();
        return "test";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upLoad(HttpServletRequest request){
		FileOutputStream out = null;
		InputStream in = null;
		FileItem fileItem = null;
		//定义上传文件的拓展
		String extName = "gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";
		//获得上传的路径，不允许直接访问该路径
		String path = "E:\\Eclipse\\chengxu\\maven_ssh\\src\\main\\webapp\\WEB-INF\\upload";
		
		String realPath = request.getSession().getServletContext().getRealPath("WEB-INF/upload");
		System.out.println(realPath+"============");
		
		//创建一个文件
		File file = new File(path);
		//判断文件是否存在
		if(!file.exists()){
			//创建文件夹
			file.mkdirs();
		}
		
		//上传时生成临时的文件保存目录
		String temPath = "E:\\Eclipse\\chengxu\\maven_ssh\\src\\main\\webapp\\WEB-INF\\tem";// request.getSession().getServletContext().getRealPath("WEB-INF/tem");
		//创建一个文件
		File temfile = new File(temPath);
		//判断文件是否存在
		if(!temfile.exists()){
			//创建文件夹
			temfile.mkdirs();
		}
		
		String message = "";	
		//使用Apache 文件的上传组件步骤：
		//1.创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置缓存大小默认值为10kB
		factory.setSizeThreshold(1024 * 10);
		//设置上传文件生成的临时文件的目录
		factory.setRepository(temfile);
		
		//创建一个上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		//监听文件上传进度
		upload.setProgressListener(new ProgressListener() {
			
			public void update(long pBytesRead, long pContentLength, int pItems) {
				System.out.println("当前已处理："+pBytesRead+"------文件大小为："+pContentLength+"----"+pItems);
				
			}
		});
		
		//解决上传文件名的乱码问题
		upload.setHeaderEncoding("utf-8");
		 // 3.判断提交上来的数据是否是上传表单的数据
		             if (!ServletFileUpload.isMultipartContent(request)) {
		                // 按照传统方式获取数据
		                 return null;
		             }
		//设置单个文件的最大值
		upload.setFileSizeMax(1024 * 1024 * 1);
		//设置本次上传文件的最大值
		upload.setSizeMax(1024 * 1024 * 10);
		
		try {
			List item = upload.parseRequest(request);
			for(int i = 0;i < item.size();i++){
				fileItem = (FileItem)item.get(i);
				if(fileItem.isFormField()){//如果它封装的是普通的输入数据
					String name = fileItem.getName();
					 // 解决普通输入项数据中文乱码问题
					String value = fileItem.getString();
					 // value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name+ "=" +value);
					
				}else{
					//得到上传文件的名称
					String fileName = fileItem.getName();
					System.out.println("文件名："+fileName);
					if (fileName == null && fileName.trim().length() == 0) {
						continue;
					} 
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					//检查扩展名是否合法
					if(!extName.contains(fileExt)){
						System.out.println("上传文件扩展名是不允许的扩展名：" + fileExt);
						message = message + "文件：" + fileName + "，上传文件扩展名是不允许的扩展名：" + fileExt+ "<br/>";
						break;
					}
					 // 检查文件大小
					if(fileItem.getSize() == 0) continue;
					if(fileItem.getSize() > upload.getFileSizeMax()){
					System.out.println("上传文件大小：" + fileItem.getSize());
					message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax() + "<br/>";
					break;
					}
					//得到存文件的文件名称
					String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
					//保存方法1获得item中上传文件的输入流
					in = fileItem.getInputStream();
					//创建一个文件输出流
					out = new FileOutputStream(path + "\\" + saveFileName);
					System.out.println(path + "\\" + saveFileName);
					//创建一个缓冲区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完
					int len = 0;
					while((len = in.read(buffer)) > 0){
						out.write(buffer,0,len);
					}
					 //关闭输出流
					//out.close();
					//关闭输入流
					//in.close();
					//删除临时文件
					//fileItem.delete();
					message = message + "文件：" + fileName + "，上传成功<br/>";
					System.out.println(message);
					//保存文件方法二
					//File uploadedFile = new File(path, saveFileName);
					//item.write(uploadedFile);
					
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 //关闭输出流
			if(out != null){
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			//关闭输入流
			if(in != null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			//删除临时文件
			fileItem.delete();
		}
		
        return "test";
	}
	
	@RequestMapping(value = "/getuploadurl", method = RequestMethod.POST)
	public String getUploadUrl(HttpServletRequest request){
		//存储要下载的文件名
		Map<String,String> uploadurl = new HashMap<String, String>();
		File file = new File("E:\\Eclipse\\chengxu\\maven_ssh\\src\\main\\webapp\\WEB-INF\\upload");
		listFile(file, uploadurl);
		request.setAttribute("fileNameMap",uploadurl);
		return "getloadurl";
	}
	
	@RequestMapping(value = "/dowload", method = RequestMethod.POST)
	public void download(String filename,HttpServletRequest request,HttpServletResponse response){
		try {
			 filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
			//文件的路径
			String path = "E:\\Eclipse\\chengxu\\maven_ssh\\src\\main\\webapp\\WEB-INF\\upload";
			//得到要下载的文件
			File file = new File(path+"\\" + filename);
			//如果文件不存在
			if(!file.exists()){
				request.setAttribute("message", "您要下载的资源已被删除");
				
			}
			//处理文件名
			String realname = filename.substring(filename.indexOf("_") + 1);;
			//设备响应文件，控制浏览器下载
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
			//读取下载的文件
			FileInputStream in = new FileInputStream(path + "\\" + filename);
			
			
			*/
/*BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String downLoadPath = path+"\\" + filename;
			System.out.println(downLoadPath);
			long fileLength = new File(downLoadPath).length();
			//new String(realName.getBytes("utf-8"), "ISO8859-1")
			response.setHeader("Content-disposition", "attachment; filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			response.setContentType("application/octet-stream");
			request.setCharacterEncoding("UTF-8");

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();*//*


		
			
			//创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			         }
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
	//遍历文件用的递归
	public void listFile(File file,Map<String,String> map){
		String property = System.getProperty("user.dir");
		System.out.println(property);
		if(!file.isFile()){//如果不是一个文件，而是一个目录
			File[] listFiles = file.listFiles();
			for(int i = 0 ;i < listFiles.length;i++){
				listFile(listFiles[i],map);
			}
		}else{
			String substring = file.getName().substring(file.getName().indexOf("_")+1);
			map.put(file.getName(), substring);
		}
	}
}*/
