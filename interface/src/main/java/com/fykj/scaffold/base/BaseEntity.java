package com.fykj.scaffold.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangf
 * @date 2019/2/12
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.UUID)
    @NotNull(message = "主键必填", groups = BaseEntity.Modify.class)
    @ApiModelProperty("主键(更新时必填)")
    private String id;

    @TableField("version")
    @Version
    @NotNull(message = "版本号必填", groups = BaseEntity.Modify.class)
    @ApiModelProperty(value = "版本号(更新时必填)", position = 1)
    private Integer version;

    /**
     * 0:正常 1:删除
     */
    @ApiModelProperty(hidden = true)
    @TableField("is_deleted")
    @TableLogic
    private Integer deleted = 0;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime createDate;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime updateDate;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;

    public interface Modify extends Default {
    } //编辑

    public interface Add extends Default {
    }//新增
}
