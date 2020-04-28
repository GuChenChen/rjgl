package com.fykj.scaffold.cms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 内容管理视图模型
 *
 * @author wangming
 */
@Data
@ApiModel("内容管理视图模型")
public class CmsContentVo implements Serializable {
    private static final long serialVersionUID = -1757367588511867999L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "内容id")
    private String contentId;

    /**
     * 栏目id
     */
    @ApiModelProperty(value = " 栏目id")
    private String categoryId;

    /**
     * 栏目名称
     */
    @ApiModelProperty(value = " 栏目名称")
    private String categoryName;

    /**
     * 标题
     */
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
     * 排序
     */
    @ApiModelProperty(value = " 排序")
    private Integer sequence;

    /**
     * 重要度
     */
    @ApiModelProperty(value = " 重要度")
    private String importantLevel;

    /**
     * 置顶
     */
    @ApiModelProperty(value = " 置顶")
    private Boolean stick;

    /**
     * 热门
     */
    @ApiModelProperty(value = " 热门")
    private Boolean hot;

    /**
     * 评论
     */
    @ApiModelProperty(value = " 评论")
    private Boolean comment;

    /**
     * 栏目父节点id
     */
    @ApiModelProperty(value = " 栏目父节点id")
    private String parentId;

    /**
     * 栏目父节点名称
     */
    @ApiModelProperty(value = " 栏目父节点名称")
    private String parentName;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = " 审核状态")
    private String auditStatus;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = " 审核状态")
    private String auditStatusName;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = " 标签名称")
    private String labelName;

    /**
     * 虚拟阅读量
     */
    @ApiModelProperty(value = "虚拟阅读量")
    private Integer virtualReading;

    /**
     * 实际阅读量
     */
    @ApiModelProperty(value = "实际阅读量")
    private Integer actualReading;

}
