package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.CopInfo;
import com.fykj.scaffold.evaluation.domain.params.CopInfoParams;
import com.fykj.scaffold.evaluation.domain.vo.CopInfoVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.mapper.CopInfoMapper;
import com.fykj.scaffold.evaluation.service.ICopInfoService;
import com.fykj.scaffold.evaluation.service.IQuotationRecordService;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * 
 *
 * 服务实现类
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
@Service
public class CopInfoServiceImpl extends BaseServiceImpl<CopInfoMapper, CopInfo> implements ICopInfoService {

    @Autowired
    IQuotationRecordService quotationRecordService;

    @Override
    public boolean saveOrUpdateCopInfo(CopInfoParams params) {
        CopInfo copInfo = new CopInfo();
        BeanUtil.copyProperties(params,copInfo);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate regDate = LocalDate.parse(params.getRegDate(), fmt);
        copInfo.setRegDate(regDate);
        if (StringUtil.isEmpty(params.getId())) {
            return addCopInfo(copInfo);
        }
        return updateCopInfo(copInfo);
    }

    @Override
    public IPage<CopInfoVo> findByPage(CopInfoParams params) {
        Page<CopInfoVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(quotationRecordService.getViewTimeEnd(params.getViewTimeEnd()));
        }
        return baseMapper.findByPage(page,params);
    }

    private boolean addCopInfo(CopInfo copInfo){
        return super.save(copInfo);
    }

    private boolean updateCopInfo(CopInfo copInfo){
        return super.updateById(copInfo);
    }
}