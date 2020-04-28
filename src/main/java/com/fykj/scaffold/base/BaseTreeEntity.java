package com.fykj.scaffold.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树结构的基础类
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTreeEntity extends BaseEntity {
    private static final long serialVersionUID = 6345476870026674872L;

    /**
     * 上级对象主键
     */
    @ApiModelProperty(value = "上级主键")
    @TableField("parent_id")
    private String parentId;
}
