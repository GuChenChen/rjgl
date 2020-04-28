package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangx
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsReadingRecord extends BaseEntity {

    private static final long serialVersionUID = 4523744448399963746L;

    /**
     * 阅读者用户名
     */
    @TableField("reader_user_name")
    @ApiModelProperty(value = " 阅读者用户名")
    private String readerUserName;

    /**
     * 内容id
     */
    @TableField("content_id")
    @NotBlank(message = "内容id不能为空")
    @ApiModelProperty(value = " 内容id")
    private String contentId;

    /**
     * 阅读时间
     */
    @TableField("reading_time")
    @ApiModelProperty(value = " 阅读时间")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime readingTime;
}
