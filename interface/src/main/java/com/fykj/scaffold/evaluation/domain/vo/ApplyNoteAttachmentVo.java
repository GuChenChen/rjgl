package com.fykj.scaffold.evaluation.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.annotation.DictTrans;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  测试申请单附件
 * </p>
 */
@Data
public class ApplyNoteAttachmentVo {

    private String id;

    /**
     * 申请单主键
     */
    private String applyNoteId;

    /**
     * 附件类型
     */
    @DictTrans (transTo = "typeText")
    private String type;

    /**
     * 附件类型
     */
    private String typeText;

    /**
     * 附件地址
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原始文件名
     */
    private String fileName;
}
