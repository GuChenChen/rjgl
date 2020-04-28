package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.Copyright;
import com.fykj.scaffold.security.business.mapper.CopyrightMapper;
import com.fykj.scaffold.security.business.service.ICopyrightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Service
@Slf4j
public class CopyrightServiceImpl extends BaseServiceImpl<CopyrightMapper, Copyright> implements ICopyrightService {

    @Override
    public Copyright findDetailByCode(String code) {
        return lambdaQuery().eq(Copyright::getCode,code).orderByDesc(Copyright::getCreateDate).one();
    }
}
