package com.fykj.scaffold.weixin.mp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuew
 * @since 2019-11-02
 */
public interface IWechatMpMenuService extends IService<WechatMpMenu> {

    /**
     * 根据code获取服务号菜单
     * @param code 服务号code
     * @return 菜单列表
     */
    List<WechatMpMenu> getByCode(String code);

    /**
     * 保存菜单
     * @param menu 菜单
     * @return 是否更新成功
     */
    boolean saveMenu(WechatMpMenu menu);

    /**
     * 更新菜单
     * @param menu 菜单
     * @return 是否更新成功
     */
    boolean updateMenu(WechatMpMenu menu);

    /**
     * 将本地数据库的菜单同步到腾讯服务器
     * @param code 服务号code
     */
    void pushToWeChat(String code);

    /**
     * 将本地数据库的菜单同步到腾讯服务器
     * @param code 服务号code
     */
    void syncFromWeChat(String code);

}
