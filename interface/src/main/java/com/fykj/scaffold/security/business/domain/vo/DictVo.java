package com.fykj.scaffold.security.business.domain.vo;

import com.fykj.scaffold.security.business.domain.entity.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author feihj.
 * @date: 2020/2/7 18:05
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("字典表")
public class DictVo implements Serializable {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private String code;

    /**
     * 子集字典
     */
    @ApiModelProperty("子集字典")
    private List<Dict> list;
}
