package ch.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrmDateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(TrmDateUtil.class);
	private static final String TIMESTAMP_PATTERN= "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN= "yyyy-MM-dd";
	private static final String DATE_PATTERN_TERSE= "yyyyMMddHHmmss";
	
	/**
	 * 获取当前时间
	 * 格式yyyyMMdd字符串
	 * @return
	 * @throws ParseException
	 */
	public static String getStringDate() throws ParseException {
		Date date = new Date(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	public static String getStringDate(Date date,String datePattern) {
		DateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(date);
	}
	
	/**  
	 * @description：  把Timestamp时间类型转化为String类型
	 * @param：  ts
	 * @return String     
	 */ 	
	public static String convertDate(Timestamp ts) {
		DateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);  
		
		if(null != ts) {
			Date date  = ts;
			return sdf.format(date);
		}
		
		return "";
	}

	/**
	 * @description：  把Timestamp时间类型转化为String类型
	 * @param：  ts
	 * @return String
	 */
	public static String convertDate_A(Timestamp ts) {
		DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		if(null != ts) {
			Date date  = ts;
			return sdf.format(date);
		}

		return "";
	}
	
	/**  
	 * @description：  把String类型转化为Timestamp类型
	 * @param： time
	 * @return Timestamp     
	 */ 	
	public static Timestamp conFromStringToTimestamp(String time) {
		Date date = new Date(); 
		DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		try {
			if(null != time && !"".equals(time)) {
				date = sdf.parse(time);
				Timestamp ts = new Timestamp(date.getTime());
				return ts;
			}
			
		} catch (ParseException e) {
			logger.error("时间转换异常:" + e.getMessage());
			e.printStackTrace();
		}
		return null;  
	}
	
	public static Timestamp conFromStringToTimestampHH(String time) {
		Date date = new Date(); 
		DateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
		try {
			if(null != time || !"".equals(time)) {
				date = sdf.parse(time);
				Timestamp ts = new Timestamp(date.getTime());
				return ts;
			}
			
		} catch (ParseException e) {
			logger.error("时间转换异常:" + e.getMessage());
			e.printStackTrace();
		}
		return null;  
	}
	
	/**  
	 * @description：  比较两个时间大小
	 * @param：  t1
	 * @param： t2
	 * @return boolean     
	 */ 	
	public static boolean compareTimestamp(Timestamp t1, Timestamp t2) {
		boolean result = t1.getTime() > t2.getTime() ? true : false;
		return result;
	}
	
	/**  
	 * @description：  计算之前时间+指定的天数-当前时间所剩余的时间
	 * @param：  before
	 * @param：  inverval
	 * @return 获取剩余的时间   
	 */ 	
	public static String getRemainTime(Date before,int inverval) {
		//获取当前时间		
		Date now = new Date(System.currentTimeMillis());
		
		Date d2 = DateUtils.addDays(before, inverval);
		//计算剩余时间
		String remainTime = DurationFormatUtils.formatPeriod(now.getTime(), d2.getTime(), "HH':'mm':'ss");
		return remainTime;
	}
	
	/**
	 * @description: 把C标段返回时间转为正常格式(20161118032334 --> 2016-11-18 03:23:34)
	 * @param str
	 * @return
	 */
	public static String getDateString(String str) {
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		String hour = str.substring(8, 10);
		String min = str.substring(10, 12);
		String sec = str.substring(12, 14);
		StringBuilder sb = new StringBuilder();
		sb.append(year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec);
		return sb.toString();
	}
	
	/**
	 * @description: 把C标段返回时间转为正常格式(20161118032334 --> 2016-11-18 03:23:34)
	 * @param str
	 * @return
	 */
	public static String getDateDDString(String str) {
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		StringBuilder sb = new StringBuilder();
		sb.append(year + "-" + month + "-" + day);
		return sb.toString();
	}
	
	// 获取当前年份
	public static String getCurrYear() {
		Calendar cal = Calendar.getInstance();
		String currYear = String.valueOf(cal.get(Calendar.YEAR));
		return currYear;
	}
	
	public static String transferLongToDate(String dateFormat, Long millSec) {
		if (StringHelper.isEmpty(dateFormat)) {
			dateFormat = DATE_PATTERN_TERSE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	public static String getEightBitDate() {
		Format format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		return date;
	}

	public static String DateToStr(Date date) {
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = format.format(date);
			return str;
		} else {
			return null;
		}

	}
	
	public static Calendar stringToCalendar(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(conFromStringToTimestamp(dateStr).getTime());
		return calendar;
	}
}
