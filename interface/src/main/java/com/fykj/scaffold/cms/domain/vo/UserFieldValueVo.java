package com.fykj.scaffold.cms.domain.vo;

import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangming
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("咨询建议导出模型")
public class UserFieldValueVo {

    @ApiModelProperty(value = "dataList")
    private List<IdTextVo> dataList;

    private List<List<IdTextVo>> fieldList = new ArrayList<>();
}
