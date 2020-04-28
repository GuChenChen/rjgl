package com.fykj.scaffold.support.wrapper.annotation;


import com.fykj.scaffold.support.wrapper.enums.QueryType;

import java.lang.annotation.*;

/**
 * 匹配类型
 * 用于查询条件字段封装中指定查询类型
 *
 * @author zhangzhi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MatchType {

    /**
     * 匹配类型
     * -- 默认精确匹配
     *
     * @return {@link QueryType}
     */
    QueryType value() default QueryType.EQ;

    /**
     * 查询对象字段的名称数组
     * -- 默认是字段名称
     * -- 日期或时间区间查询时需要指定时间字段
     *
     * @return {@link String[]}
     */
    String[] fieldName() default {};
}
