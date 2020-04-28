package com.fykj.scaffold.weixin.mp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseTreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author xuew
 * @since 2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WechatMpMenu extends BaseTreeEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务号code
     */
    @TableField("mp_code")
    private String mpCode;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * key
     */
    @TableField("wx_key")
    private String wxKey;

    /**
     * appId
     */
    @TableField("app_id")
    private String appId;

    /**
     * 小程序页面路径
     */
    @TableField("page_path")
    private String pagePath;

    /**
     * 素材id
     */
    @TableField("media_id")
    private String mediaId;

    /**
     * 排序号
     */
    @TableField("sequence")
    private Integer sequence;
}
