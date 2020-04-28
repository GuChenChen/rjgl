package com.fykj.scaffold.security.business.domain.entity;

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
 * @since 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ScheduleJobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableField("job_id")
    private String jobId;

    /**
     * spring bean名称
     */
    @TableField("bean_name")
    private String beanName;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * 任务状态   1：成功   0：失败
     */
    @TableField("status")
    private Boolean status;

    /**
     * 失败信息
     */
    @TableField("error")
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    @TableField("times")
    private Integer times;


}
