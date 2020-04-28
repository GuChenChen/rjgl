package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.FlowNode;
import com.fykj.scaffold.evaluation.domain.params.FlowNodeParams;

import java.util.List;

/**
 * 流程节点
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
public interface IFlowNodeService extends IService<FlowNode> {

    /**
     * 通过流程code获取结点id集
     *
     * @param flowCode
     * @return
     */
    List<String> getNodeIdsByFlowCode(String flowCode);

    /**
     * 删除流程下的所有结点
     *
     * @param idList
     * @return
     */
    boolean removeAllNodesOfFlows(List<String> idList);

    /**
     * 分页查询
     * @param params
     * @return
     */
    IPage<FlowNode> findByPage(FlowNodeParams params);
}

