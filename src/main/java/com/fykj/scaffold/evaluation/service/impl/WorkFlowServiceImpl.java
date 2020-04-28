package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.WorkFlow;
import com.fykj.scaffold.evaluation.mapper.WorkFlowMapper;
import com.fykj.scaffold.evaluation.service.IFlowNodeService;
import com.fykj.scaffold.evaluation.service.IWorkFlowService;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 流程
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Service
public class WorkFlowServiceImpl extends BaseServiceImpl<WorkFlowMapper, WorkFlow> implements IWorkFlowService {

    @Autowired
    private IFlowNodeService flowNodeService;

    @Override
    public boolean saveOrUpdate(WorkFlow entity) {
        if(validateFlowCode(entity.getId(),entity.getFlowCode())){
            throw new BusinessException(ResultCode.FAIL,"该流程code已存在，请修改code后保存");
        }
        return super.saveOrUpdate(entity);
    }

    private boolean validateFlowCode(String flowId, String flowCode){
        return lambdaQuery().eq(WorkFlow::getFlowCode,flowCode).ne(StringUtil.isNotEmpty(flowId),WorkFlow::getId,flowId).count() > 0;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean result = removeFlowNode(idList);
        if(!result){
            throw new BusinessException(ResultCode.FAIL,"移除流程前，移除流程包含的结点失败！");
        }
        return super.removeByIds(idList);
    }

    private boolean removeFlowNode(Collection<? extends Serializable> idList){
        List<String> flowNodeIdsOfNeedRemove = new ArrayList<>();
        idList.forEach(it->{
            String flowCode = lambdaQuery().eq(WorkFlow::getId,it).one().getFlowCode();
            List<String> flowNodeIdsSub = flowNodeService.getNodeIdsByFlowCode(flowCode);
            flowNodeIdsOfNeedRemove.addAll(flowNodeIdsSub);
        });
        if(flowNodeIdsOfNeedRemove.size()>0){
            return flowNodeService.removeAllNodesOfFlows(flowNodeIdsOfNeedRemove);
        }else{
            return true;
        }
    }
}
