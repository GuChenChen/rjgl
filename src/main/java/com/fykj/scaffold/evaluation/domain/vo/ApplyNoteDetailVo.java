package com.fykj.scaffold.evaluation.domain.vo;

import com.fykj.scaffold.support.annotation.DictTrans;
import lombok.Data;

@Data
public class ApplyNoteDetailVo {

    /**
     * 总金额
     */
    private String sumMoney;

    /**
     * 申请单号
     */
    private String code;

    /**
     * 测试项目名称
     */
    private String projectName;

    /**
     * 版本
     */
    private String versions;

    /**
     * 测试类型
     */
    private String type;

    /**
     * 状态code
     */
    @DictTrans(transTo = "statusText")
    private String status;


    /**
     * 状态code
     */
    private String statusText;

    /**
     * 测试系统概述
     */
    private String systemSummarize;

    /**
     * 提交时间
     */
    private String createDate;

    /**
     * 测试报告编号(测试报告名)
     */
    private String testReportNo;

    /**
     * 测试报告文件地址
     */
    private String testReportUrl;

}
