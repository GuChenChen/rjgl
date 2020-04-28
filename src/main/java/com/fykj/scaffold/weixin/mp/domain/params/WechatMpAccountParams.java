package com.fykj.scaffold.weixin.mp.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangf
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatMpAccountParams extends BaseParams {
    @MatchType(fieldName = "name", value = QueryType.LIKE)
    private String name;
}
