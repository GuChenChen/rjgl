package com.fykj.scaffold.weixin.mp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangf
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WechatMpAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务号代码
     */
    @TableField("code")
    private String code;

    /**
     * 服务号名称
     */
    @TableField("name")
    private String name;

    /**
     * 一个公众号的appid
     */
    @TableField("appId")
    private String appId;

    /**
     * 公众号的appsecret
     */
    @TableField("secret")
    private String secret;

    /**
     * 接口配置里的Token值
     */
    @TableField("token")
    private String token;

    /**
     * 接口配置里的EncodingAESKey值
     */
    @TableField("aesKey")
    private String aesKey;


}
