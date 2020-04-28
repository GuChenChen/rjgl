package com.fykj.scaffold.evaluation.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportPageViewVo {
    /**
     * 模块（字典）
     */
    private String pageCode;

    /**
     * 单位主键
     */
    private String evaluationUserId;

    /**
     * 查看时间
     */
    private LocalDate viewTime;

    /**
     * 模块文本
     */
    private String pageText;

    /**
     * 用户IP
     */
    private String ip;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 登录时间
     */
    private String createDate;

    /**
     * 关于我们
     */
    private String aboutUs;
    /**
     * 软件测试
     */
    private String softwareTest;
    /**
     * 新闻资讯
     */
    private String newsInformation;

    /**
     *解决方案
     */
    private String solution;

    /**
     *客户案例
     */
    private String customerCase;

    /**
     *荣誉资源
     */
    private String honorResources;

    /**
     *合作伙伴
     */
    private String cooperativePartner;

    /**
     *注册登录
     */
    private String registerLogin;

    /**
     *在线客服
     */
    private String onlineService;

    /**
     *模拟报价
     */
    private String simulatedQuotation;

    /**
     *首页
     */
    private String index;
}
