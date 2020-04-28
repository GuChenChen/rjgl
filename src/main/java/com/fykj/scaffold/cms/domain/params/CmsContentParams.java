package com.fykj.scaffold.cms.domain.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
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
@ApiModel("内容管理查询参数")
public class CmsContentParams extends BaseParams {

    private static final long serialVersionUID = 5051351643065736048L;

    @ApiModelProperty("标题，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "title")
    private String title;

    @ApiModelProperty("标签，模糊查询")
    @MatchType(value = QueryType.LIKE)
    private String labelName;

    @ApiModelProperty("栏目list，模糊匹配")
    @MatchType
    private String categoryList;

    @ApiModelProperty("栏目级别")
    @MatchType
    @JsonIgnore
    private Integer categoryLevel;

    @ApiModelProperty("审核状态，精确查询")
    @MatchType
    private String auditStatus;

    @ApiModelProperty("过期开始时间")
    @MatchType(value = QueryType.DATE_START)
    private String startDate;

    @ApiModelProperty("过期结束时间")
    @MatchType(value = QueryType.DATE_END)
    private String endDate;

    @ApiModelProperty("是否是回收站")
    @MatchType
    private boolean recycling;

}
