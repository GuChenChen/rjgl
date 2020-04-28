package com.fykj.scaffold.cms.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.cms.domain.vo.PropVo;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容栏目属性属性值数据模型
 *
 * @author yangx.
 * @date: 2019-11-22
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("审核模型")
public class ContentCategoryPropValueDto implements Serializable {

    private static final long serialVersionUID = 1509133741332548390L;

    @ApiModelProperty(value = "内容id")
    private String id;

    @ApiModelProperty(value = "版本号(更新时必填)")
    private Integer version;

    @ApiModelProperty(value = " 标题")
    private String title;

    /**
     * SEO标题
     */
    @ApiModelProperty(value = " SEO标题")
    private String seoTitle;

    /**
     * SEO关健字
     */
    @ApiModelProperty(value = " SEO关健字")
    private String seoKeyword;

    /**
     * SEO描述
     */
    @ApiModelProperty(value = " SEO描述")
    private String seoRemark;

    /**
     * 标题别名
     */
    @ApiModelProperty(value = " 标题别名")
    private String titleAlias;

    /**
     * 标题图片url
     */
    @ApiModelProperty(value = " 标题图片url")
    private String titleImgUrl;

    /**
     * 简介
     */
    @ApiModelProperty(value = " 简介")
    private String briefIntroduction;

    /**
     * 自定义链接
     */
    @ApiModelProperty(value = " 自定义链接")
    private String customLinks;

    /**
     * 详细描述
     */
    @ApiModelProperty(value = " 详细描述")
    private String description;

    /**
     * 生效日期
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    @ApiModelProperty(value = " 生效日期")
    private LocalDateTime effectiveDate;

    /**
     * 过期日期
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    @ApiModelProperty(value = " 过期日期")
    private LocalDateTime expirationDate;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = " 审核状态")
    private String auditStatus;

    /**
     * 是否需要审核
     */
    @ApiModelProperty(value = " 是否需要审核")
    private Boolean audit;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = " 审核备注")
    private String auditMemo;

    /**
     * 虚拟阅读量
     */
    @ApiModelProperty(value = " 虚拟阅读量")
    private Integer virtualReading;

    /**
     * 实际阅读量
     */
    @ApiModelProperty(value = " 实际阅读量")
    private Integer actualReading;

    /**
     * 栏目属性属性值Map
     */
    @ApiModelProperty(value = "属性，属性值")
    private List<PropVo> propAndValue = new ArrayList<>();
}
