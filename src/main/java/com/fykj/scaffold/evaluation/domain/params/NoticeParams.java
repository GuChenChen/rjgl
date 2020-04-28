package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通知
 * 查询参数
 *
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("通知查询参数")
public class NoticeParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = -4816195861280571415L;

    @MatchType(value = QueryType.LIKE, fieldName = {"name"})
    @ApiModelProperty("节点名称")
    private String name;
}
