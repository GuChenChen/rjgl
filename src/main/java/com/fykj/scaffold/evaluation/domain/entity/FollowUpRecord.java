package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 跟进记录
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FollowUpRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 联系时间
     */
    @TableField("link_date")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime linkDate;

    /**
     * 对接人
     */
    @TableField("link_person")
    private String linkPerson;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 录入时间
     */
    @TableField("input_date")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime inputDate;

    /**
     * 测试单位
     */
    @TableField("evaluation_user_id")
    private String evaluationUserId;


}
