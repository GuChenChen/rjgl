package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsCategoryContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 栏目list id
     */
    @TableField("category_list")
    private String categoryList;

    /**
     * 内容id
     */
    @TableField("content_id")
    private String contentId;

    /**
     * 排序
     */
    @TableField("sequence")
    private Integer sequence;

    /**
     * 重要度
     */
    @TableField("important_level")
    private String importantLevel;

    /**
     * 置顶
     */
    @TableField("is_stick")
    private Boolean stick;

    /**
     * 热门
     */
    @TableField("is_hot")
    private Boolean hot;


    /**
     * 评论
     */
    @TableField("is_comment")
    private Boolean comment;

    /**
     * 是否加入回收站
     */
    @TableField("is_recycling")
    private Boolean recycling;

    /**
     * 栏目父节点id
     */
    @TableField("parent_id")
    private String parentId;


}
