package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.FlowNode;
import com.fykj.scaffold.evaluation.domain.params.FlowNodeParams;
import com.fykj.scaffold.evaluation.mapper.FlowNodeMapper;
import com.fykj.scaffold.evaluation.service.IFlowNodeService;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 流程结点
 *
 * 服务实现类
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Service
public class FlowNodeServiceImpl extends BaseServiceImpl<FlowNodeMapper, FlowNode> implements IFlowNodeService {

    @Override
    public boolean saveOrUpdate(FlowNode entity) {
        if(validateNodeCode(entity.getFlowCode(),entity.getId(),entity.getAction())){
            throw new BusinessException(ResultCode.FAIL,"该结点编码已存在，请修改结点编码后保存");
        }
        if(validateNodeName(entity.getFlowCode(),entity.getId(),entity.getActionName())){
            throw new BusinessException(ResultCode.FAIL,"该结点名称已存在，请修改结点名称后保存");
        }
        return super.saveOrUpdate(entity);
    }

    private boolean validateNodeCode(String flowCode, String nodeId, String action){
        return lambdaQuery().eq(FlowNode::getFlowCode,flowCode).eq(FlowNode::getAction,action).ne(StringUtil.isNotEmpty(nodeId),FlowNode::getId,nodeId).count() > 0;
    }

    private boolean validateNodeName(String flowCode, String nodeId, String actionName){
        return lambdaQuery().eq(FlowNode::getFlowCode,flowCode).eq(FlowNode::getActionName,actionName).ne(StringUtil.isNotEmpty(nodeId),FlowNode::getId,nodeId).count() > 0;
    }

    @Override
    public List<String> getNodeIdsByFlowCode(String flowCode) {
        List<String> nodeIds = new ArrayList<>();
        lambdaQuery().eq(FlowNode::getFlowCode,flowCode).list().forEach(it-> nodeIds.add(it.getId()));
        return nodeIds;
    }

    @Override
    public boolean removeAllNodesOfFlows(List<String> idList){
        return super.removeByIds(idList);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList){
        idList.forEach(it->{
            String nodeCode = lambdaQuery().eq(FlowNode::getId,it).one().getAction();
            int use = lambdaQuery().eq(FlowNode::getOriginStatus,nodeCode).count()+lambdaQuery().eq(FlowNode::getTargetStatus,nodeCode).count();
            if(use>0){
                throw new BusinessException(ResultCode.FAIL,"请先处理好与需要删除结点有关联的结点的前驱后继结点信息！");
            }
        });
        return super.removeByIds(idList);
    }

    @Override
    public IPage<FlowNode> findByPage(FlowNodeParams params) {
        Page<FlowNode> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        return baseMapper.getListWithQuery(page,params);
    }

}
