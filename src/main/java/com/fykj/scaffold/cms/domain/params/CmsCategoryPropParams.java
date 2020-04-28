package com.fykj.scaffold.cms.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 内容管理查询参数
 *
 * @author wangming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("扩展属性管理查询参数")
public class CmsCategoryPropParams extends BaseParams {

    private static final long serialVersionUID = 5051351643065736048L;

    @ApiModelProperty("栏目id，精确查询")
    @MatchType
    private String categoryId;


}
