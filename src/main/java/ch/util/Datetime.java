package ch.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 时间处理类
 * 
 * @author zhaosimiao 2016-12-7 12:32:16
 *
 */
public class Datetime {

	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 */
	public static Date getDateAmountDays(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 得到当前日期  
	 */
	
	/*
	 * 字符串 
	 */
	
	//格林尼治时间
	public static String getGMTDateStr(String parton) {
		if(parton==null||parton.trim().equals("")){
			parton="yyyy-MM-dd HH:mm:ss";
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(parton);
		return sdf.format(date);
	}
	//北京时间
	public static String getLocalDateStr(String parton) {
		if(parton==null||parton.trim().equals("")){
			parton="yyyy-MM-dd HH:mm:ss";
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(parton);
		sdf.setTimeZone(new SimpleTimeZone(28800000,"Asia/Shanghai"));//设置时区  北京时间
		return sdf.format(date);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	/*
	 * Date
	 */
	//北京时间
	public static Date getLocalDate() {
		String parton="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(parton);
		try {
			return sdf.parse(getLocalDateStr(null));
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	/**
	 * String 转Date
	 */
	public static Date toDate(String date, String pattern) {
		Date date2;
		
		if(StringUtils.isEmpty(pattern)){
			pattern="yyyy-MM-dd HH:mm:ss";
		}
		try {
			if (date != null && !date.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				date2 = sdf.parse(date);
			} else {
				date2 = toLocalDate(new Date(), pattern);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return date2;
	}

	/**
	 * Date 转LocalDate
	 */
	public static Date toLocalDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		TimeZone zone = new SimpleTimeZone(28800000, "Asia/Shanghai");// +8区
		sdf.setTimeZone(zone);
		String sdate = sdf.format(date);

		SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);
		try {
			return sdf2.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

/**
 * 得到当前 年 月 日 时 
 */

	/*
	 * GMT 格林尼治时间
	 */
	@SuppressWarnings("deprecation")
	public static int getGMTYear(){
		return new Date().getYear()+1900;
		
	}
	@SuppressWarnings("deprecation")
	public static int getGMTMonth(){
		return new Date().getMonth()+1;
		
	}
	@SuppressWarnings("deprecation")
	public static int getGMTDay(){
		return new Date().getDate();
		
	}
	@SuppressWarnings("deprecation")
	public static int getGMTHours(){
		return new Date().getHours();
		
	}
	
	/*
	 * 北京时间
	 */
	@SuppressWarnings("deprecation")
	public static int getLocalYear(){
		return getLocalDate().getYear()+1900;
		
	}
	@SuppressWarnings("deprecation")
	public static int getLocalMonth(){
		return getLocalDate().getMonth()+1;
		
	}
	@SuppressWarnings("deprecation")
	public static int getLocalDay(){
		return getLocalDate().getDate();
		
	}
	@SuppressWarnings("deprecation")
	public static int getLocalHours(){
		return getLocalDate().getHours();
		
	}
	@SuppressWarnings("deprecation")
	public static int getLocalWeekDay(){
		return getLocalDate().getDay();
		
	}
	   //   当前日期加减n天后的日期，返回String   (yyyy-mm-dd)   
    public  static String   nDaysAftertoday(String pattern,int   n)   {   
        SimpleDateFormat   df   =   new   SimpleDateFormat(pattern);   
        Calendar   rightNow   =   Calendar.getInstance();   
        //rightNow.add(Calendar.DAY_OF_MONTH,-1);   
        rightNow.add(Calendar.DAY_OF_MONTH,+n);   
        return   df.format(rightNow.getTime());   
    }   
  
    //   当前日期加减n天后的日期，返回String   (yyyy-mm-dd)   
    public  static Date   nDaysAfterNowDate(int   n)   {   
        Calendar   rightNow   =   Calendar.getInstance();   
        //rightNow.add(Calendar.DAY_OF_MONTH,-1);   
        rightNow.add(Calendar.DAY_OF_MONTH,+n);   
        return   rightNow.getTime();   
    }   
  
    //   给定一个日期型字符串，返回加减n天后的日期型字符串   
    public  static String   nDaysAfterOneDateString(String   basicDate,String pattern,int   n)   {   
        SimpleDateFormat   df   =   new   SimpleDateFormat(pattern);   
        Date   tmpDate   =   null;   
        try   {   
            tmpDate   =   df.parse(basicDate);   
        }   
        catch(Exception   e){   
            //   日期型字符串格式错误   
        }   
        long   nDay=(tmpDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);   
        tmpDate.setTime(nDay);   
  
        return   df.format(tmpDate);   
    }   
  
    //   给定一个日期，返回加减n天后的日期   
    public  static Date   nDaysAfterOneDate(Date   basicDate,int   n)   {   
        long   nDay=(basicDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);   
        basicDate.setTime(nDay);   
  
        return   basicDate;   
    }     
   /**
    * 判断给定的日期是否在一定的范围内 如：daycount=7 返回true 说明给定日期在最近7天内
    * @date 日期字符串
    * @daycount 天数
    * @auth liuguang
    * @date Apr 23, 2013
    */
    public  static boolean   getRecentDate(String startTime,int daycount)   {   
       	//按照传入的格式生成一个simpledateformate对象
       	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
       	String nowTime=sd.format(new Date());
       	long nd = 1000*24*60*60;//一天的毫秒数
       	try {
       	//获得两个时间的毫秒时间差异
        long diff = sd.parse(nowTime).getTime() - sd.parse(startTime).getTime();
       	int day =(int)(diff/nd);//计算差多少天
       	//输出结果
       //System.out.println("时间相差：----"+day+"天");
       if(day>daycount){
    	   return false;
       }else{ 
    	   return true;
       }
       } catch (ParseException e) {
       	e.printStackTrace();
       	return false;
       	}
        }
    
   public  static void   getRecentDate(String startTime, String endTime, String format)   {   
   	//按照传入的格式生成一个simpledateformate对象
   	SimpleDateFormat sd = new SimpleDateFormat(format);
   	long nd = 1000*24*60*60;//一天的毫秒数
   	long nh = 1000*60*60;//一小时的毫秒数
   	long nm = 1000*60;//一分钟的毫秒数
   	long ns = 1000;//一秒钟的毫秒数
   	long diff;
   	try {
   	//获得两个时间的毫秒时间差异
   	diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
   	long day = diff/nd;//计算差多少天
   	long hour = diff%nd/nh;//计算差多少小时
   	long min = diff%nd%nh/nm;//计算差多少分钟
   	long sec = diff%nd%nh%nm/ns;//计算差多少秒
   	//输出结果
   	System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
   
   	} catch (ParseException e) {
   	e.printStackTrace();
   	}
    }

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日期的年,即yyyy-MM-dd中的yyyy
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}


	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	/**
	 * 时间段之内的月数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int calDiffMonth(String startDate,String endDate){
		int result=0;
		try {
			SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
			Date start = sfd.parse(startDate);
			Date end = sfd.parse(endDate);
			int startYear=getYear(start);
			int startMonth=getMonth(start);
			int startDay=getDay(start);
			int endYear=getYear(end);
			int endMonth=getMonth(end);
			int endDay=getDay(end);
			if (startDay>endDay){ //1月17  大于 2月28
				if (endDay==getDaysOfMonth(getYear(new Date()),2)){   //也满足一月
					result=(endYear-startYear)*12+endMonth-startMonth;
				}else{
					result=(endYear-startYear)*12+endMonth-startMonth-1;
				}
			}else{
				result=(endYear-startYear)*12+endMonth-startMonth;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}


   
   public static void main(String[] args) {
   	getRecentDate("2013-3-11",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "yyyy-MM-dd");
   	System.out.println(getRecentDate("1997-4-11",7));

   	//两个时间段的
   	int i = calDiffMonth("2016-02-28", "2016-4-28");
   	System.out.println(i);

   	}
}
