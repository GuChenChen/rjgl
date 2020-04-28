package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.Advice;

/**
 * <p>
 * 咨询建议服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface IAdviceService extends IService<Advice> {

    Advice getByCode(String code);
}
