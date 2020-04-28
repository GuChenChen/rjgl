package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;
import com.fykj.scaffold.security.business.mapper.ApiParamManageMapper;
import com.fykj.scaffold.security.business.service.IApiParamManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApiParamManageServiceImpl  extends BaseServiceImpl<ApiParamManageMapper, ApiParamManage> implements IApiParamManageService {

    @Override
    public Integer getParamNumByUrlId(String urlId){
        return lambdaQuery().eq(ApiParamManage::getUrlId,urlId).count();
    }

    @Override
    public List<ApiParamManage> getParamByUrlId(String urlId){
        return lambdaQuery().eq(ApiParamManage::getUrlId,urlId).orderBy(true,true,ApiParamManage::getSortNum).list();
    }

    @Override
    public boolean saveParamInfo(List<ApiParamManage> apiParamManageList) {
        return super.saveOrUpdateBatch(apiParamManageList,apiParamManageList.size());
    }

    @Override
    public boolean removeByApiId(List<String> ids) {
        QueryWrapper<ApiParamManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("url_id",ids);
        if(baseMapper.delete(queryWrapper)>0){
            return true;
        }else{
            return false;
        }
    }

}
