package com.fykj.scaffold.weixin.mp.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

/**
 * @author wangf
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxMpMyConfigImpl extends WxMpDefaultConfigImpl {

    protected volatile String code;
}
