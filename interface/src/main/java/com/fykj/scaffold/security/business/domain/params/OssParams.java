package com.fykj.scaffold.security.business.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 对象存储查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("对象存储查询参数")
public class OssParams extends BaseParams {

    @MatchType(value = QueryType.LIKE)
    @ApiModelProperty("文件名称模糊查询")
    private String fileName;

    @MatchType(value = QueryType.LIKE)
    @ApiModelProperty("名称模糊查询")
    private String name;

    @MatchType(value = QueryType.LIKE)
    @ApiModelProperty("标签模糊查询")
    private String labelName;

    @MatchType(value = QueryType.EQ)
    @ApiModelProperty("存储服务器")
    private String serverCode;

    @MatchType(value = QueryType.EQ)
    @ApiModelProperty("文件类型")
    private String type;

    @MatchType(value = QueryType.EQ, fieldName = "is_media")
    @ApiModelProperty("是否是媒体库")
    private boolean media=true;

}
