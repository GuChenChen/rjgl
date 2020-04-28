package com.fykj.scaffold.cms.domain.vo;

import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 内容附件管理视图模型
 *
 * @author wangming
 */
@Data
@ApiModel("内容附件管理视图模型")
public class AttachVo implements Serializable {
    private static final long serialVersionUID = -1757367588511867999L;

    @ApiModelProperty(value = "内容id")
    @NotBlank(message = "内容不能为空")
    private String contentId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    @Size(max = 100, message = "名称不能超过100个字符")
    private String name;

    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255个字符")
    private String remark;

    @ApiModelProperty(value = "附件列表")
    @NotEmpty(message = "附件不能为空")
    private List<IdTextVo> filePaths;

}