package com.fykj.scaffold.weixin.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpAccount;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangf
 * @since 2019-10-30
 */
public interface IWechatMpAccountService extends IService<WechatMpAccount> {

    /**
     * 从页面修改
     *
     * @param account 账号
     * @return 是否成功
     */
    boolean updateByIdFromPage(WechatMpAccount account);

    /**
     * 重写save 添加code校验
     * @param account 实体
     * @return 是否成功
     */
    boolean save(WechatMpAccount account);
}
