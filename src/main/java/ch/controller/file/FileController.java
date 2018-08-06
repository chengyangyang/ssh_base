package ch.controller.file;

import ch.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
        String contextPath = request.getSession().getServletContext().getRealPath("/data");
        String path = contextPath+"/diagrams";
        Object[] fileLastName = {".zip",".doc",".docx"};
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setDefaultEncoding("utf-8");
        //判断是否有文件上传
        if(multipartResolver.isMultipart(request)){
            //转化成request
            MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
            //取得request中的所有文件
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            Collection<MultipartFile> files = fileMap.values();
            int size = files.size();//文件的数量
            if(size > 6){
                return "上传数量不得大于6个";
            }
            //遍历上传文件
            for (MultipartFile file:files) {
                long startMillis = System.currentTimeMillis();//文件上传的开始时间
                if(file != null){
                    String name = file.getName();
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

}
