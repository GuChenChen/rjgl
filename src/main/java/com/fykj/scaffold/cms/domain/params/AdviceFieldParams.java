package com.fykj.scaffold.cms.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 咨询建议字段查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("咨询建议字段查询参数")
public class AdviceFieldParams extends BaseParams {
    private static final long serialVersionUID = 4101640252674555739L;

    @ApiModelProperty("咨询建议主键")
    @MatchType
    private String adviceId;
}
