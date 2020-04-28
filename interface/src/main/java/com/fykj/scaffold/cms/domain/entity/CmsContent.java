package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wangm
 * @since 2019-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsContent extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 标题
     */
    @TableField("title")
    @NotBlank(message = "标题不能为空")
    @Size(max = 200, message = "标题不能超过200个字符")
    @ApiModelProperty(value = " 标题")
    private String title;

    /**
     * SEO标题
     */
    @TableField("seo_title")
    @Size(max = 255, message = "SEO标题不能超过255个字符")
    @ApiModelProperty(value = " SEO标题")
    private String seoTitle;

    /**
     * SEO关健字
     */
    @TableField("seo_keyword")
    @Size(max = 255, message = "SEO关健字不能超过255个字符")
    @ApiModelProperty(value = " SEO关健字")
    private String seoKeyword;

    /**
     * SEO描述
     */
    @TableField("seo_remark")
    @Size(max = 255, message = "SEO描述不能超过255个字符")
    @ApiModelProperty(value = " SEO描述")
    private String seoRemark;

    /**
     * 标题别名
     */
    @JsonIgnore
    @TableField("title_alias")
    @Size(max = 255, message = "标题别名不能超过255个字符")
    @ApiModelProperty(value = " 标题别名")
    private String titleAlias;

    /**
     * 标题图片url
     */
    @TableField("title_img_url")
    @ApiModelProperty(value = " 标题图片url")
    private String titleImgUrl;

    /**
     * 简介
     */
    @TableField("brief_introduction")
    @Size(max = 100, message = "简介不能超过100个字符")
    @ApiModelProperty(value = " 简介")
    private String briefIntroduction;

    /**
     * 自定义链接
     */
    @TableField("custom_links")
    @Size(max = 100, message = "自定义链接不能超过100个字符")
    @ApiModelProperty(value = " 自定义链接")
    private String customLinks;

    /**
     * 详细描述
     */
    @TableField("description")
    @ApiModelProperty(value = " 详细描述")
    private String description;

    /**
     * 生效日期
     */
    @TableField("effective_date")
    @NotNull(message = "生效日期不能为空")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    @ApiModelProperty(value = " 生效日期")
    private LocalDateTime effectiveDate;

    /**
     * 过期日期
     */
    @TableField("expiration_date")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    @ApiModelProperty(value = " 过期日期")
    private LocalDateTime expirationDate;

    /**
     * 审核状态
     */
    @JsonIgnore
    @TableField("audit_status")
    @ApiModelProperty(value = " 审核状态")
    private String auditStatus;

    /**
     * 是否需要审核
     */
    @TableField("is_audit")
    @ApiModelProperty(value = " 是否需要审核")
    private Boolean audit;

    /**
     * 审核备注
     */
    @JsonIgnore
    @TableField("audit_memo")
    @ApiModelProperty(value = " 审核备注")
    private String auditMemo;

    /**
     * 虚拟阅读量
     */
    @TableField("virtual_reading")
    @ApiModelProperty(value = " 虚拟阅读量")
    private Integer virtualReading;

    /**
     * 实际阅读量
     */
    @TableField("actual_reading")
    @ApiModelProperty(value = " 实际阅读量")
    private Integer actualReading;
}
