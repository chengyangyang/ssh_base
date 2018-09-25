package ch.controller.file;

import ch.common.bean.ProgressEntity;
import ch.common.rebean.CustomMultipartResolver;
import ch.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Description:文件的类
 *
 * @author cy
 * @date 2018年08月06日 13:43
 * version 1.0
 */
@Controller
@RequestMapping("/file")   //@RequestMapping("${mgt}/file")  可以使用这种形式,统一进行添加头部
public class FileController {

    @Value("#{filePath.fileDoMain}")
    private String doMain;


    public static final int cacheA = 10 * 1024;

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
     * 文件上传进度条的请求
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

    /**
     * 文件的下载
     * @param request
     * @return
     */
    @RequestMapping(value = "/download",method = {RequestMethod.POST,RequestMethod.GET})
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String filePath = request.getSession().getServletContext().getRealPath("/files");
            String fileCode = request.getParameter("fileCode");
            HashMap<String, String> map = new HashMap<>();
            map.put("JBXX","《企业基本信息表》.docx");
            map.put("XMQK","《企业技术项目情况表》.docx");
            map.put("JSXQ","《企业技术需求情况表》.docx");
            map.put("JRXQ","《企业科技金融需求情况表》.docx");
            map.put("ZCXQ","《企业科技政策需求情况表》.docx");
            map.put("pdf","如何免费获得百度文库的收费文档.pdf");
            map.put("tp","Desert.jpg");

            String path = filePath+"/"+map.get(fileCode);
            File file = new File(path);//文件的路径
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.reset();
                // 指定下载的文件名--设置响应头
                response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                response.setContentType("application/x-download;charset=UTF-8");
                int bytesRead = 0;
                byte[] buffer = new byte[cacheA];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, cacheA)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.close();
            response.flushBuffer();
        }
    }


    /**
     * 文件的在线打开,(只能打开图片或者pdf文档),可以使用此方法,用前端控制是否下载
     * @param request
     * @return
     */
    @RequestMapping(value = "/openFile",method = {RequestMethod.POST,RequestMethod.GET})
    public void openFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //路径测试
        System.out.println(doMain);
        try{
            String filePath = request.getSession().getServletContext().getRealPath("/files");
            String fileCode = request.getParameter("fileCode");
            HashMap<String, String> map = new HashMap<>();
            map.put("JBXX","《企业基本信息表》.docx");
            map.put("XMQK","《企业技术项目情况表》.docx");
            map.put("JSXQ","《企业技术需求情况表》.docx");
            map.put("JRXQ","《企业科技金融需求情况表》.docx");
            map.put("ZCXQ","《企业科技政策需求情况表》.docx");
            map.put("pdf","如何免费获得百度文库的收费文档.pdf");
            map.put("tp","Desert.jpg");

            String path = filePath+"/"+map.get(fileCode);
            File file = new File(path);//文件的路径
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.reset();

                //在线打开
                if(true){
                    URL u = new URL("file:///" + path);
                    response.setContentType(u.openConnection().getContentType());
                        response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                }else{
                    // 指定下载的文件名--设置响应头
                    response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                    response.setContentType("application/x-download;charset=UTF-8");
                }


                int bytesRead = 0;
                byte[] buffer = new byte[cacheA];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, cacheA)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.close();
            response.flushBuffer();
        }
    }



}
