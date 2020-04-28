package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseTreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
public class Dict extends BaseTreeEntity {
    private static final long serialVersionUID = 4054050813462089293L;

    /**
     * 字典编码 唯一
     */
    @NotBlank(message = "字典编码不能为空")
    @Size(max = 20, message = "字典编码不能超过20个字符")
    private String code;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String name;

    /**
     * 显示排序
     */
    private Integer sequence;

    /**
     * 值域-（主要用于有第三字段需求的扩展字段）
     */
    private String value;

    /**
     * 激活状态
     */
    private Boolean status;

}
