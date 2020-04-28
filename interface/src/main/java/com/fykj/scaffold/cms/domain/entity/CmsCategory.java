package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangm
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "名称不能为空")
    @Size(max = 20, message = "名称不能超过20个字符")
    @ApiModelProperty(value = " 名称")
    private String name;

    /**
     * 排序
     */
    @TableField("sequence")
    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = " 排序")
    private Integer sequence;

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
     * 图片url
     */
    @TableField("img_url")
    @ApiModelProperty(value = "图片url")
    private String imgUrl;

    /**
     * URL链接
     */
    @TableField("url_link")
    @Size(max = 100, message = "URL链接不能超过100个字符")
    @ApiModelProperty(value = " URL链接")
    private String urlLink;

    /**
     * 状态（启用，未启用）
     */
    @TableField("status")
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = " 状态")
    private Boolean status;

    /**
     * 描述
     */
    @TableField("remark")
    @ApiModelProperty(value = " 描述")
    private String remark;

    /**
     * 父节点
     */
    @TableField("parent_id")
    @ApiModelProperty(value = " 父节点")
    private String parentId;

    /**
     * 栏目级别
     */
    @ApiModelProperty(value = "栏目级别")
    private Integer level = 1;

    /**
     * 下级栏目列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = " 下级栏目列表")
    private List<CmsCategory> subCategories;

    /**
     * 标签
     */
    @TableField(exist = false)
    @ApiModelProperty(value = " 标签")
    private String[] labels;


}
