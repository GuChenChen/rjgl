package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.entity.RoleResource;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface IRoleResourceService extends IService<RoleResource> {

    /**
     * 是否有权限
     * 判断是否存在指定参数的对象
     *
     * @param roleId 角色主键
     * @param uri    资源路径
     * @return 有权限:true, 没权限:false
     */
    boolean exists(String roleId, String uri);

    /**
     * 保存角色资源
     *
     * @param roleId     角色主键
     * @param resourceId 资源主键
     * @return 保存是否成功
     */
    boolean save(String roleId, String resourceId);

    /**
     * 批量保存角色资源
     * @param role
     * @param resourceIds
     * @return
     */
    boolean save(Role role, List<String> resourceIds);

    /**
     * 获取角色拥有的菜单
     * @param roleId
     * @return
     */
    List<String> findResourceIdList(String roleId);

}
