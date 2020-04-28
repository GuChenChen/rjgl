package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.Copyright;

/**
 * @author feihj.
 * @date: 2019/11/6 14:10
 */
public interface ICopyrightService extends IService<Copyright> {

    /**
     * 根据code 获取版权信息
     * @param code
     * @return
     */
    Copyright findDetailByCode(String code);


}
