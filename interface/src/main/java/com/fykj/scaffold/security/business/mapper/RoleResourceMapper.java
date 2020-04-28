package com.fykj.scaffold.security.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.security.business.domain.entity.RoleResource;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    /**
     * 获取角色拥有的菜单
     * @param roleId
     * @return
     */
    List<String> findResourceIdList(String roleId);

    /**
     * 编辑角色时，删除角色菜单
     * @param roleId
     */
    void deleteRoleResourceByRoleId(String roleId);
}
