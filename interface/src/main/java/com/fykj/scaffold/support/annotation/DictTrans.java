package com.fykj.scaffold.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在字段上打上注解，自动转换
 * Created by xuew
 * on 2019/3/27.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictTrans {
    String transTo() default "";
}
