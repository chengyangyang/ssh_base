package ch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 主要用于文件名产生类
 * 
 * @author zhaoismiao 2016-12-7 12:36:00
 *
 */
public class FileUtil {

	/**
	 * 产生文件夹路径，配置路径/年/月/日
	 * 
	 * 格式如：配置路径/2009/5/15/
	 * 
	 */
	public static String createDatePath() {

		return Datetime.getLocalYear() + "/" + Datetime.getLocalMonth() + "/" + Datetime.getLocalDay();

	}

	/**
	 * 产生文件名:yyyyMMddHHmmss,并在后面加n位随机数
	 * 
	 * 格式如：200905011021155489.jpg
	 */
	public static String createFileName(int n) {
		String date = Datetime.getLocalDateStr("yyyyMMddHHmmss");
		// 取4位随机数
		String b = String.valueOf(Math.random());
		String c = b.substring(2, n + 2);
		return date + c;
	}

	/**
	 * 取文件扩展名 格式如：jpg
	 */
	public static String getSmallName(String fileName) {
		String tempName = "";
		if (fileName != null) {
			tempName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return tempName;
	}

	/**
	 * 删除图片
	 */
	public static boolean delete(String path) {

		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的文件或目录
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean deleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) {
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) {
				return deleteFile(sPath);
			} else {
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	private boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 * @return 目录删除成功返回true 目录删除失败返回false
	 */
	private boolean deleteDirectory(String sPath) {
		boolean flag = false;
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return flag;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public void syncdownload(String urlString, String filename, String savePath) {
		// 构造URL
		URL url;
		InputStream is;
		URLConnection con;
		try {
			url = new URL(urlString);
			// 打开连接
			con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}

			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("同步图片失败！");
		}
	}

}
