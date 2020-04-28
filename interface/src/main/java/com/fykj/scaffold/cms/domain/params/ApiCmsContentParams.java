package com.fykj.scaffold.cms.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author feihj.
 * @date: 2019/11/21 9:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("客户端新闻查询参数")
public class ApiCmsContentParams extends BaseParams {

    private static final long serialVersionUID = 5051351643065736048L;
    

    @ApiModelProperty("第一级栏目Id")
    @MatchType
    private String categoryId;

    @ApiModelProperty("第二级栏目Id")
    @MatchType
    private String parentId;


}
