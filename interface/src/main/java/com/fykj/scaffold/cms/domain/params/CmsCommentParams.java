package com.fykj.scaffold.cms.domain.params;

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
 * @author xuew
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("内容管理查询参数")
public class CmsCommentParams extends BaseParams {

    private static final long serialVersionUID = 5051351643065736048L;

    @ApiModelProperty("过期开始时间")
    @MatchType(value = QueryType.DATE_START, fieldName = "comment_time")
    private String startDate;

    @ApiModelProperty("过期结束时间")
    @MatchType(value = QueryType.DATE_END, fieldName = "comment_time")
    private String endDate;

    @ApiModelProperty("姓名")
    @MatchType(value = QueryType.LIKE)
    private String name;

    @ApiModelProperty("手机号")
    @MatchType(value = QueryType.EQ)
    private String phone;

    @ApiModelProperty("回复状态")
    @MatchType(value = QueryType.EQ, fieldName = "is_reply")
    private Boolean reply;

    @ApiModelProperty("内容id")
    @MatchType(value = QueryType.EQ)
    private String contentId;

//    @ApiModelProperty("栏目id")
//    @MatchType(value = QueryType.EQ)
//    private String categoryId;
}
