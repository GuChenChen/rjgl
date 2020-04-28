package com.fykj.scaffold.evaluation.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.support.annotation.DictTrans;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 根据登录用户测试申请列表
 * @Author lmy
 * @Date 2020/4/8 8:56
 **/
@Data
public class ApplyNoteListVo {
    private String id;

    /**
     * 申请单号
     */
    private String code;

    /**
     * 测试项目名称
     */
    private String projectName;

    /**
     * 状态code
     */
    private String statusText;


    /**
     * 申请单位手机号
     */
    private String tel;


    /**
     * 提交时间
     */
    private String createDate;

    /**
     * 是否可下载
     */
    private Integer downLoad;

    /**
     * 测试报告编号(测试报告名)
     */
    private String testReportNo;

    /**
     * 测试报告文件地址
     */
    private String testReportUrl;
}
