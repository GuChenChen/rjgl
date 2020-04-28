package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.dto.NavDto;
import com.fykj.scaffold.security.business.domain.dto.ResourceOauthDto;
import com.fykj.scaffold.security.business.domain.entity.Resource;

import java.util.List;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 获取当前登录用户的菜单和权限列表对象
     * -- 包含层级关系
     *
     * @return 菜单和权限列表对象
     */
    NavDto menus();

    /**
     * 获取当前登录用户指定菜单下的按钮列表
     *
     * @param menuId 指定菜单id
     * @return 菜单下按钮列表
     */
    List<Resource> buttons(String menuId);

    /**
     * 判断指定地址的资源是否存在
     *
     * @param uri 资源地址
     * @return 存在：true, 不存在：false
     */
    boolean exists(String uri);

    /**
     * 获取资源树
     *
     * @param roleId 角色主键
     * @return 资源树
     */
    List<Resource> tree(String roleId);

    /**
     * 获取指定角色资源列表
     *
     * @param roleId 角色主键
     * @return 资源列表
     */
    List<Resource> findByRoleId(String roleId);

    /**
     *系统初始化时查询所有权限匹配的菜单
     * @return
     */
    List<ResourceOauthDto> findResourceList();
}
