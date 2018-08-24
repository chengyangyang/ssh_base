package ch.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.sql.Timestamp;
import java.util.*;

import ch.tool.SqlToBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public abstract class BeanUtils extends org.springframework.beans.BeanUtils{
	public static void main(String[] args) {
		/*Person person = new Person();
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",123);
		map.put("createBy","nihao");
		map.put("createDate",new Timestamp(System.currentTimeMillis()));
		map.put("total",new BigDecimal("0"));

		Person person1 = (Person)mapToBean(map,Person.class);
		System.out.println("nihao");*/

	}

	public static void copyProperties(Object source, Object target) throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						// 这里判断下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (value != null) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							if(value instanceof Date){
								String dateStr = DictionaryGeneratePrefixUtil.getStringDate((Date)value);
								Timestamp time = TrmDateUtil.conFromStringToTimestamp(dateStr);
								writeMethod.invoke(target, time);
							}else{
								writeMethod.invoke(target, value);
							}
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}

	/**
	 * 将map集合转化为javabean,如果javabean中没有该字段,则不进行映射,会自动去除映射格式错误的的类
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToBean(Map<String,Object> map, Class<?> beanClass) {
		if(map != null){
			Object object = null;
			try {
				object = beanClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			//遍历Map集合
			for(Map.Entry<String, Object> mapData:map.entrySet()){
				String key = mapData.getKey();
				Object value = mapData.getValue();
				String setFild = null;
				try {
					Field field = beanClass.getDeclaredField(key);
					//拼接set方法
					setFild = "set"+ StringUtils.capitalize(key);//首字母大写
					Method method = beanClass.getDeclaredMethod(setFild, field.getType());
					//method.setAccessible(true);暴力反射
					//给方法赋值
					method.invoke(object,value);

				}catch (NoSuchFieldException e){
					System.out.println("----------该类["+object+"]没有["+key+"]参数");
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println("----------该类["+object+"]中没有["+setFild+"]公共方法");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.out.println("----------该类["+object+"]中["+setFild+"]方法类型转化错误");
					e.printStackTrace();
				}catch (IllegalArgumentException e){
					System.out.println("----------该类["+object+"]中["+setFild+"]方法类型转化错误");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return object;
		}
		return null;
	}

	/**
	 * 根据List<Map<String, Object>>数据转换为JavaBean数据
	 * @param
	 * @param
	 * @return
	 * @throws*/

	public static List<Object> ListMapToJavaBean(List<Map<String, Object>> list, Class<?> beanClass) {
		ArrayList<Object> obj = new ArrayList<>();
		if(list != null && list.size() >0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> stringObjectMap = list.get(i);
				Object o = mapToBean(stringObjectMap, beanClass);
				obj.add(o);
			}
		}
		// 返回
		return obj;
	}
}
