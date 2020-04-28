package com.fykj.scaffold.evaluation.domain.vo;

import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecord;
import com.fykj.scaffold.support.annotation.DictTrans;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
* @Description 测试申请管理
* @Author lmy
* @Date 2020/4/8 8:56
**/
@Data
public class ApplyNoteVo {

    private String id;

    /**
     * 申请单位主键
     */
    private String evaluationUserId;

    /**
     * 申请单位名称
     */
    private String evaluationUserName;

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
     * 申请单位手机号
     */
    private String tel;

    /**
     * 应收金额（元）
     */
    private BigDecimal amountReceivable;

    /**
     * 实收金额（元）
     */
    private BigDecimal amountReceived;

    /**
     * 测试报告编号
     */
    private String testReportNo;

    /**
     * 提交时间
     */
    private String createDate;

    private Integer version;

    private Integer deleted;

    private String updateDate;

    private String creator;

    private String updater;
}
