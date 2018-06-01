package ch.util;

import com.trm.constants.SystemConstants;
import com.trm.entity.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadFileUtil {

	private static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

	/**
	 * 富文本上传图片--默认地址
	 */
	private static String RichTextImgDefault = "/ueditor/themes/default/images/spacer.gif";

	/**
	 * 富文本原前缀
	 */
	private static String ImagePathFormat = "/rich-text";

	/**
	 * 富文本内有图片的上传图片到指定路径
	 * 
	 * @param realPath
	 *            项目路径
	 * @param context
	 *            富文本内容
	 * @param physicalPath
	 *            物理路径
	 * @param db_richImgPrefix
	 *            数据库保存的图片前缀( 图片路径前缀 + richImgPrefix)
	 * @param richImgPrefix
	 *            图片前缀(/rich-text)
	 * @param RichTextImgDefault
	 *            富文本上传图片--默认地址
	 * @return
	 */
	public static String uploaRichdImg(String realPath, String context, String physicalPath, String RichTextImgPrefix
	/* ,String richImgPrefix,String RichTextImgDefault */) throws BusinessException {
		String db_richImgPrefix = RichTextImgPrefix + ImagePathFormat;
		String resultContext = context;
		// 富文本内容中的图片
		if (StringHelper.isNotEmpty(context)) {
			if (context.contains(RichTextImgDefault)) {
				BusinessException bs = new BusinessException();
				bs.setExceptionCode(SystemConstants.REASONCODE_2048);
				bs.setExceptionMsg("富文本图片未加载完成，请稍后提交！");
				throw bs;
			}

			List<String> list = UploadFileUtil.getImageSrc(context);
			if (list.size() > 0) {
				for (String imgSrc : list) {
					String originalFile = realPath + imgSrc;
					logger.info("临时图片储存路径 :", originalFile);
					File oldfile = new File(originalFile);
					if (oldfile.exists()) {
						try {
							InputStream inStream = new FileInputStream(originalFile);
							// 文件存储路径
							String newPath = physicalPath + imgSrc;
							logger.info("储存图片到新路径 :", newPath);
							UploadFileUtil.uploadFileToDes(inStream, newPath);
							boolean del = deleteFile(originalFile);
							logger.info("删除临时图片 :", del);
						} catch (IOException e) {
							logger.info("保存图片异常 :", e.getMessage());
							return null;
						}
					}
					// 返回保存数据库文本内容
					// 如果图片src不包含db_richImgPrefix 就做处理
					if (!imgSrc.contains(db_richImgPrefix)) {
						if (imgSrc.contains(ImagePathFormat)) {
							logger.info("===================================modify s imgSrc: " + imgSrc);
							String tempImgsrc = imgSrc;
							imgSrc = imgSrc.replace(ImagePathFormat, db_richImgPrefix);
							logger.info("===================================modify after replace s imgSrc: " + imgSrc);
							resultContext = resultContext.replace(tempImgsrc, imgSrc);
						}
					}

				}
			}
		}
		return resultContext;
	}

	/**
	 * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0 的List
	 * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg
	 * |.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
	 * 
	 * @param htmlCode
	 *            HTML源码
	 * @return <img>标签 src 属性指向的图片地址的List集合
	 * @author Carl He
	 */
	public static List<String> getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern.compile(
				"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);
		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
			imageSrcList.add(src);
		}
		return imageSrcList;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/*
	 * 上传文件到指定文件夹下,并且返回存储文件的相对路径
	 */
	public static String uploadFile(MultipartFile file, HttpServletRequest request, String key) throws Exception {

		String relativePath = null;
		String originalName = file.getOriginalFilename();

		if (null != originalName && !"".equals(originalName)) {
			String newName = GenerateCodeUtil.generateCode("pic") + originalName.substring(originalName.lastIndexOf("."));

			// 从properties文件中读取相关信息
			ResourceBundle bundle = ResourceBundle.getBundle("portalsite.pathConfig", Locale.getDefault());

			// 虚拟路径
			relativePath = bundle.getString(key) + "/" + newName;
			// 输出文件路径
			String path = bundle.getString(key + "Path") + "/" + newName;

			logger.debug("real path : " + path);

			InputStream inputStream;
			try {
				inputStream = file.getInputStream();
				// 上传文件到指定的路径
				uploadFileToDes(inputStream, path);
			} catch (IOException e) {
				logger.error("上传文件异常 :" + e.getMessage());
				throw new IOException();
			}
		}

		return relativePath;
	}

	/*
	 * 复制原文件到指定的路径下
	 */
	public static void uploadFileToDes(InputStream inputStream, String path) throws IOException {
		File destFile = new File(path);
		try {
			FileUtils.copyInputStreamToFile(inputStream, destFile);
		} catch (IOException e) {
			logger.error("上传文件异常 :" + e.getMessage());
			throw new IOException();
		}
	}
}
