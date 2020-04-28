package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.FlowNode;
import com.fykj.scaffold.evaluation.domain.params.FlowNodeParams;
import com.fykj.scaffold.security.business.domain.dto.SysOssDto;
import org.apache.ibatis.annotations.Param;

/**
 * 流程结点
 *
 * Mapper 接口
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
public interface FlowNodeMapper extends BaseMapper<FlowNode> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<FlowNode> getListWithQuery(IPage<FlowNode> page, @Param("params") FlowNodeParams params);

}
