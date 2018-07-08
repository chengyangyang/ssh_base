package ch.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL参数获取工具类
 * @author zhaosimiao 2017-3-3 16:02:36
 *
 */
public class ParameterUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ParameterUtils.class);
	
	/**
	 * sql参数获取方法：得到某个精确查询参数
	 * @param param
	 * @return
	 */
	public static String getEqualParam(String parameter) {
		
		if(!StringUtils.isBlank(parameter)) {
			parameter = parameter.trim();
		}
		logger.info("SQL传入参数[精确查询 parameter]:" + parameter);
		return parameter;
	}
	
	/**
	 * sql参数获取方法：得到某个模糊查询参数
	 * @param param
	 * @return
	 */
	public static String getLikeParam(String parameter) {
		
		if(!StringUtils.isBlank(parameter)) {
			parameter = "%" + parameter.trim() + "%";
		}
		else {
			parameter = "%";
		}
		logger.info("SQL传入参数[模糊查询 parameter]:" + parameter);
		return parameter;
	}
	
	/**
	 * sql参数获取方法：得到精确查询参数
	 * @param parameter 可以传入多个String类型的参数[个数不定]
	 * @return
	 */
	public static Object[] getEqualParams(String... parameters) {
		
		int count = parameters.length;
		Object[] objs = new Object[count];
		
		for (int i = 0; i<count; i++){
			String parameter = parameters[i];
			if(!StringUtils.isBlank(parameter)) {
				objs[i] = parameter.trim();
			}
		}
		for (int i = 0; i<count; i++){
			logger.info("SQL传入参数[精确查询 parameter" + (i+1) + "]:" + objs[i]);
		}
		return objs;
	}
	
	/**
	 * sql参数获取方法：得到模糊查询参数
	 * @param parameter 可以传入多个String类型的参数[个数不定]
	 * @return
	 */
	public static Object[] getLikeParams(String... parameters) {
		
		int count = parameters.length;
		Object[] objs = new Object[count];
		
		for (int i = 0; i<count; i++){
			String parameter = parameters[i];
			if(!StringUtils.isBlank(parameter)) {
				objs[i] = "%" + parameter.trim() + "%";
			}
			else {
				objs[i] = "%";
			}
		}
		for (int i = 0; i<count; i++){
			logger.info("SQL传入参数[模糊查询 parameter" + (i+1) + "]:" + objs[i]);
		}
		return objs;
	}
	
}
