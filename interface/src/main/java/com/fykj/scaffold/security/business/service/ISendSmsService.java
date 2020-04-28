package com.fykj.scaffold.security.business.service;

import result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-08-21
 */
public interface ISendSmsService {

    Result sendSms(String apiKey, String code, String mobile, String context);
}
