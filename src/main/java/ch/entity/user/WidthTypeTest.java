package ch.entity.user;

import ch.util.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月23日 17:44
 * version 1.0
 */
public class WidthTypeTest<T> {

    public T mapToBean(Map<String,Object> map, Class<T> beanClass) throws IllegalAccessException, InstantiationException {
        if(map != null){
            T t = beanClass.newInstance();
            //遍历Map集合
            for(Map.Entry<String, Object> mapData:map.entrySet()){
                String key = mapData.getKey();
                Object value = mapData.getValue();
                String setFild = null;
                try {
                    Field field = beanClass.getField(key);
                    //拼接set方法
                    setFild = "set"+ StringUtils.capitalize(key);//首字母大写
                    Method method = beanClass.getMethod(setFild, field.getType());
                    //method.setAccessible(true);暴力反射
                    //给方法赋值
                    method.invoke(t,value);

                }catch (NoSuchFieldException e){
                    System.out.println("----------该类["+t+"]没有["+key+"]参数");
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    System.out.println("----------该类["+t+"]中没有["+setFild+"]公共方法");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    System.out.println("----------该类["+t+"]中["+setFild+"]方法类型转化错误");
                    e.printStackTrace();
                }
            }
            return t;
        }
        return null;
    }

}
