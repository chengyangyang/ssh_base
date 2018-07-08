package ch.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class DictionaryGeneratePrefixUtil {
	
	/**
	 * 
	 * DATE转为String
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) throws ParseException{
		DateFormat sdf = null;
		if(null != date ) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 判断日期是否为月末的一天
	 * 
	 * @param date
	 * @return true:是|false:不是
	 */
	public static boolean isLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean fileIsExists(String filePath){
		try {
			URL url = new URL(filePath);
			// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
			URLConnection uc = url.openConnection();
			// 打开的连接读取的输入流。
			InputStream in = uc.getInputStream();
			in.available();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取当月天数
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
}
