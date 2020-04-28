package com.fykj.scaffold.support.wrapper.enums;

/**
 * 常用查询类型枚举
 *
 * @author zhangzhi
 */

public enum QueryType {
    /**
     * 等于
     */
    EQ(0),

    /**
     * 不等于
     */
    NE(1),

    /**
     * 模糊匹配
     */
    LIKE(2),

    /**
     * 不匹配
     */
    NOT_LIKE(3),


    /**
     * 范围查询
     */
    IN(4),

    /**
     * 不在范围
     */
    NOT_IN(5),

    /**
     * 日期开始
     */
    DATE_START(6),

    /**
     * 日期结束
     */
    DATE_END(7),

    /**
     * 时间开始
     */
    TIME_START(8),

    /**
     * 时间结束
     */
    TIME_END(9),

    /**
     * 大于等于
     */
    GE(10),

    /**
     * 小于等于
     */
    LE(11),

    /**
     * 左侧匹配
     * 例: likeRight("name", "王")--->name like '王%'
     */
    LIKE_RIGHT(12);

    int value;

    QueryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
