package com.fykj.scaffold.weixin.mp.service.impl;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * @author wangf
 */
public class MyWxMpServiceImpl extends WxMpServiceImpl {

    /**
     * 重写了次方法，将appid改成了code
     * @param code
     * @return
     */
    @Override
    public WxMpService switchoverTo(String code) {
        return super.switchoverTo(code);
    }

    /**
     * 重写了次方法，将appid改成了code
     * @param code
     * @return
     */
    @Override
    public boolean switchover(String code) {
        return super.switchover(code);
    }
}
