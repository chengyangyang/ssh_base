package ch.constants;

/**
 * 异常处理用到的常量
 * @author zhaosimiao 2017-5-9 15:42:36
 */
public class ExceptionConstants {
	
	/**
	 * 自定义业务异常：BusinessException错误码
	 */
	public static final String BUSI_ERR_9999 = "BUSI_ERR_9999";
	
	/**
	 * Spring框架异常：BindException错误码 (前后台传参包含日期类型可能会抛此异常)
	 */
	public static final String SPRING_ERR_0001 = "SPRING_ERR_0001";
	
	/**
	 * Spring框架异常：MethodArgumentNotValidException错误码 (方法参数无效)
	 */
	public static final String SPRING_ERR_0002 = "SPRING_ERR_0002";
	
	/**
	 * Java系统异常：RuntimeException错误码 (运行时错误)
	 */
	public static final String JAVA_RUNTIME_ERR = "JAVA_RUNTIME_ERR";
	
	/**
	 * SQL异常
	 */
	public static final String SQL_ERR = "SQL_ERR";
	
	/**
	 * 其他未识别的系统异常
	 */
	public static final String OTHER_SYS_ERR = "OTHER_SYS_ERR";
	
	/**
	 * 通用错误类型
	 */
	public final static String ERR_TYPE_0 = "0";
	
	/**
	 * 日期类型等前后台传参校验异常错误类型
	 */
	public final static String ERR_TYPE_1 ="1";
	
	/**
	 * 方法参数无效异常错误类型
	 */
	public final static String ERR_TYPE_2 ="2";
	
	/**
	 * 页面错误提示语
	 */
	public final static String PAGE_ERROR_NOTICE = "系统出现了无法预知的错误！";
	
	public final static String EXPLAIN_ERR_0 = "登录异常";
	public final static String EXPLAIN_ERR_1 = "业务异常";
	public final static String EXPLAIN_ERR_2 = "日期类型转化异常";
	public final static String EXPLAIN_ERR_3 = "方法参数无效异常";
	public final static String EXPLAIN_ERR_4 = "系统运行时异常";
	public final static String EXPLAIN_ERR_5 = "SQL异常";
	public final static String EXPLAIN_ERR_6 = "其他未识别异常";
}
