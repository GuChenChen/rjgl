package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.dto.SysLogDto;
import com.fykj.scaffold.security.business.domain.entity.SysLog;
import com.fykj.scaffold.security.business.domain.params.SysLogParams;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-10-18
 */
public interface ISysLogService extends IService<SysLog> {
    /**
     * 分页查询
     * @param params 查询条件封装
     * @return
     */
    IPage<SysLog> page(BaseParams params);

    /**
     * 通过用户名分页查询
     * @param params
     * @return
     */
    IPage<SysLogDto> pageByUserName(SysLogParams params);
}
