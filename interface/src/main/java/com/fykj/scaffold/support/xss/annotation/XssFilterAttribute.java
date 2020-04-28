package com.fykj.scaffold.support.xss.annotation;

import java.lang.annotation.*;

/**
 * @author ZC
 * @date: 2019/10/31
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XssFilterAttribute {
}
