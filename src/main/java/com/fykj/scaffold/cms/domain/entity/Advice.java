package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 咨询建议
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_advice")
public class Advice extends BaseEntity {
    private static final long serialVersionUID = 7461298507604717915L;

    /**
     * 标题
     */
    @Size(max = 50, message = "标题长度不能超过50个字符")
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题", required = true)
    @TableField("title")
    private String title;

    /**
     * 编码
     */
    @Size(max = 50, message = "编码长度不能超过50个字符")
    @NotBlank(message = "编码不能为空")
    @ApiModelProperty(value = "编码", required = true)
    @TableField("code")
    private String code;

    /**
     * 字段列表
     */
    @ApiModelProperty(value = "字段列表")
    @TableField(exist = false)
    private List<AdviceField> fieldList;


}
