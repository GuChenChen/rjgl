package com.fykj.scaffold.evaluation.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkBenchVo {
    /**
     * 模块名称
     */
    private String name;
    /**
     * 关于我们
     */
    private int value;

    /**
     * 今日新增注册数
     */
    private int toDay;

    /**
     * 处理中的申请数
     */
    private int processing;

    /**
     * 已结项申请数
     */
    private int completedItem;

    /**
     * 今日新增注册数
     */
    private String toDays;

    /**
     * 处理中的申请数
     */
    private String proCe;

    /**
     * 已结项申请数
     */
    private String completedItems;

}
