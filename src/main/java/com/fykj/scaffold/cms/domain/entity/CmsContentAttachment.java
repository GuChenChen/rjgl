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
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsContentAttachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 内容id
     */
    @TableField("content_id")
    private String contentId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;


}
