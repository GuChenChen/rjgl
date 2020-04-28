package com.fykj.scaffold.cms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.cms.domain.entity.CmsContentAttachment;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端视图模型
 *
 * @author feihj.
 * @date: 2020/4/1 13:12
 */
@Data
@ApiModel("客户端视图模型")
public class CmsContentClientsVo implements Serializable {
    private static final long serialVersionUID = -1757367588511867999L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = " 标题")
    private String title;

    @ApiModelProperty(value = " 详细描述")
    private String description;

    @ApiModelProperty(value = " 简介")
    private String briefIntroduction;

    @ApiModelProperty(value = "浏览量")
    private Integer actualReading;

    @ApiModelProperty(value = " 标题图片url")
    private String titleImgUrl;

    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    @ApiModelProperty(value = " 生效日期")
    private LocalDateTime effectiveDate;

    List<CmsContentAttachment> contentAttachmentList = new ArrayList<>();



}
