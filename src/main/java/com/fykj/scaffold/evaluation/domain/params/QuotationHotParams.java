package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 查询参数
 *
 * @author gcc
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("报价热度统计更新参数")
public class QuotationHotParams extends BaseParams {
    /**
     * 主键
     */
    private String id;
    /**
     * 甲方
     */
    private Integer firstParty;

    /**
     * 乙方
     */
    private Integer secondParty;

    /**
     * 功能测试
     */
    private Integer functionTest;

    /**
     * 性能测试
     */
    private Integer performanceTest;

    /**
     * 安全测试
     */
    private Integer securityTestCode;
}
