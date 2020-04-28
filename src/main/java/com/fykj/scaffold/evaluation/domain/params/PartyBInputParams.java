package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel("乙方获取报价查询参数")
public class PartyBInputParams {

    /**
     * 子系统数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"subsystem_count"})
    @ApiModelProperty("子系统数")
    private String subsystemCount;

    /**
     * 子系统模块数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"subsystem_module_count"})
    @ApiModelProperty("子系统模块数")
    private String subsystemModuleCount;

    /**
     * 模块功能点数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"module_function_count"})
    @ApiModelProperty("模块功能点数")
    private String moduleFunctionCount;

    /**
     * 并发数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"concurrent_count"})
    @ApiModelProperty("并发数")
    private String concurrentCount;

    /**
     * 性能点
     */
    @MatchType(value = QueryType.EQ, fieldName = {"performance_count"})
    @ApiModelProperty("性能点")
    private String performanceCount;

    /**
     * 代码行数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"code_lines_count"})
    @ApiModelProperty("代码行数")
    private String codeLinesCount;

    /**
     * 系统数
     */
    @MatchType(value = QueryType.EQ, fieldName = {"system_count"})
    @ApiModelProperty("系统数")
    private String systemCount;
}
