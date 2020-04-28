package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.dto.SysLogDto;
import com.fykj.scaffold.security.business.domain.entity.ApiManage;
import com.fykj.scaffold.security.business.domain.entity.SysLog;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.domain.params.SysLogParams;
import com.fykj.scaffold.security.business.mapper.SysLogMapper;
import com.fykj.scaffold.security.business.service.ISysLogService;
import com.fykj.scaffold.security.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-10-18
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Autowired
    private IUserService userService;

    @Override
    public IPage<SysLogDto> pageByUserName(SysLogParams params) {
        IPage<SysLog> result = super.page(params);
        return result.convert(this::convert);
    }

    private SysLogDto convert(SysLog entity){
        SysLogDto dto = new SysLogDto();
        dto.setId(entity.getId());
        User user = userService.getOne((Wrapper<User>) new QueryWrapper().eq("username", entity.getUsername()));
        dto.setOperator(user.getName());
        dto.setMobile(user.getMobile());
        dto.setOperateDate(entity.getCreateDate());
        dto.setOperation(entity.getOperation());
        return dto;
    }
}
