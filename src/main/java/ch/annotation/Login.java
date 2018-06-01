package ch.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    /**
     * @Description功能value的参数索引位置 默认是过滤前端用户
     */
    String value() default "front";
}
