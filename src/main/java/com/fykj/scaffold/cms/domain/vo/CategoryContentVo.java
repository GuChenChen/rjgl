package com.fykj.scaffold.cms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
@Data
@ApiModel("栏目内容管理视图模型")
public class CategoryContentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String contentId;

    /**
     * 栏目id
     */
    @ApiModelProperty(value = "栏目id")
    @NotEmpty(message = "栏目不能为空")
    private List<String[]> categoryId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @Size(max = 200, message = "标题不能超过200个字符")
    @ApiModelProperty(value = " 标题")
    private String title;

    /**
     * SEO标题
     */
    @Size(max = 255, message = "SEO标题不能超过255个字符")
    @ApiModelProperty(value = " SEO标题")
    private String seoTitle;

    /**
     * SEO关健字
     */
    @Size(max = 255, message = "SEO关健字不能超过255个字符")
    @ApiModelProperty(value = " SEO关健字")
    private String seoKeyword;

    /**
     * SEO描述
     */
    @Size(max = 255, message = "SEO描述不能超过255个字符")
    @ApiModelProperty(value = " SEO描述")
    private String seoRemark;

    /**
     * 标题图片url
     */
    @ApiModelProperty(value = " 标题图片url")
    private String titleImgUrl;

    /**
     * 简介
     */
    @Size(max = 100, message = "简介不能超过100个字符")
    @ApiModelProperty(value = " 简介")
    private String briefIntroduction;

    /**
     * 自定义链接
     */
    @Size(max = 100, message = "自定义链接不能超过100个字符")
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
    @NotNull(message = "生效日期不能为空")
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
     * 是否需要审核
     */
    @ApiModelProperty(value = " 是否需要审核")
    private Boolean audit;

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

}
