package ch.controller.file;

import ch.common.bean.ProgressEntity;
import ch.common.rebean.CustomMultipartResolver;
import ch.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Description:文件的类
 *
 * @author cy
 * @date 2018年08月06日 13:43
 * version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {


    /**
     * 文件的上传
     * @param request
     * @return
     */
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request){
    	//获取数据测试
    	String user = request.getParameter("user");
    	String param = request.getParameter("param");
        String contextPath = request.getSession().getServletContext().getRealPath("/data");
        String path = contextPath+"/diagrams";
        Object[] fileLastName = {".zip",".doc",".docx",".txt",".iso"};
        //创建一个通用的多部分解析器
        CustomMultipartResolver multipartResolver = new CustomMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setDefaultEncoding("utf-8");
        //判断是否有文件上传
        if(multipartResolver.isMultipart(request)){
            //转化成request
            MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)(request);
            //取得request中的所有文件
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            Collection<MultipartFile> files = fileMap.values();
            int size = files.size();//文件的数量
            if(size > 6){
                return "上传数量不得大于6个";
            }
            if(size == 0) {
            	return "没有文件或者刷新页面重新上传";
            }
            //遍历上传文件
            for (MultipartFile file:files) {
                long startMillis = System.currentTimeMillis();//文件上传的开始时间
                if(file != null){
                    String name = file.getName();//获取jsp页面中name中的值
                    String fileName = file.getOriginalFilename();//获得上传文件的名称
                    if(StringUtils.isBlank(fileName)){
                        continue;
                    }
                    int lastNum = fileName.lastIndexOf(".");
                    String suffix = fileName.substring(lastNum, fileName.length());//判断文件的结尾
                    //判断后缀名是否符合标准
                    boolean contains = ArrayUtils.contains(fileLastName,suffix);
                    if(!contains){
                        return "后缀名不合法";
                    }
                }
                try {
                    String filePath = path+file.getOriginalFilename()+new Date().getTime();
                    file.transferTo(new File(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "上传成功";
    }

    /**
     * 进度条的请求
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProgress",method = RequestMethod.POST)
    @ResponseBody
    public ProgressEntity initCreateInfo(HttpServletRequest request) {
        ProgressEntity status =  (ProgressEntity) request.getSession().getAttribute("status");
        if(status==null){
            return new ProgressEntity();
        }
        return status;
    }

}
