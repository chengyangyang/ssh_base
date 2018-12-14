package ch.constants;

public class SystemConstants {
	public static final String RESPONSECOE_000 = "000";
	public static final String RESPONSECOE_004 = "004";
	public static final String RESPONSECOE_008 = "008";	//业务级别
	public static final String RESPONSECOE_016 = "016"; //系统级别
	//ReasonCode
	public static final String REASONCODE_2048 = "2048"; //查询数据不存在
	public static final String REASONCODE_2068 = "2068"; //数据校验不合法 (目前特指时间校验)
	public static final String REASONCODE_2088 = "2088"; //状态转换异常
	public static final String REASONCODE_3056 = "3056"; //重复添加
	public static final String REASONCODE_4022 = "4022"; //用户未登陆
	public static final String REASONCODE_4241 = "4241"; //交易类型
	public static final String REASONCODE_6241 = "6241"; //发起支付失败
	public static final String REASONCODE_6248 = "6248"; //支付未完成
	public static final String REASONCODE_4011 = "4011"; // C标段接口访问不通或者数据格式有问题

	// 登录和实名校验的reason code
	public static final String FRONT_USER_NOT_LOGIN = "3001"; // 前端用户未登陆
	public static final String MGT_USER_NOT_LOGIN = "3002"; // 后端用户未登陆
	public static final String NOT_REAL_CERT = "3003"; // 该用户未实名认证
	public static final String PHONE_NOT_CERT = "3004"; // 该用户未手机认证

	public final static String TRM_SUCCESS = "success";
	public final static String TRM_ERROR = "error";

	public final static String ERROR_TYPE_0 = "0";//表示一般类型的错误
	public final static String ERROR_TYPE_1 ="1";//表示校验类型的错误
	public final static String ERROR_TYPE_2 ="2";//表示权限不够的错误
	public final static String ERROR_TYPE_3 ="3";//表示XSS风险错误
	public final static String ERROR_TYPE_4 ="4";//表示与C标段对接的错误

	public final static String DELETE_FLAG_Y = "Y";//表示删除该对象
	public final static String DELETE_FLAG_N = "N";//表示该对象可用

	public final static String FLAG_Y = "Y";//表示该对象可用
	public final static String FLAG_N = "N";//表示该对象不可用

	public final static String AJAX_INDICATOR = "ajaxIndicator";

	public final static String SYSTEM_SEPARATOR = " ";
	public final static String SYSTEM_MINUS = "-";
	public final static String SYSTEM_COMMA = ",";


	//used in webservice interceptor
	public static final String XML_HEADER = "SoapHeader";
	public static final String XML_AUTHENTICATION = "authentication";
	public static final String XML_TIMESTAMP = "timestamp";
	public static final String XML_LICENSE = "license";
	public static final String XML_SESSIONBEAN ="sessionBean";
	public static final String XML_USERNAME ="username";
	public static final String XML_USERID ="userId";
	public static final String XML_PHONE ="phone";
	public static final String XML_REALNAME = "realName";
	public static final String XML_EMAIL = "email";
	public static final String XML_ENTER_CODE = "enterCode";
	public static final String XML_ENTER_ID = "enterId";
	public static final String XML_SOURCE = "source";
}
