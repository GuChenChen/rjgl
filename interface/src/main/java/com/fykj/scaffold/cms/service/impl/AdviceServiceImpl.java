package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.Advice;
import com.fykj.scaffold.cms.mapper.AdviceMapper;
import com.fykj.scaffold.cms.service.IAdviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 咨询建议服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdviceServiceImpl extends BaseServiceImpl<AdviceMapper, Advice> implements IAdviceService {

    @Override
    public Advice getByCode(String code) {
        return lambdaQuery().eq(Advice::getCode,code).one();
    }
}
