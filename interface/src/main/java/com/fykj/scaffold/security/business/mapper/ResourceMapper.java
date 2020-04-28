package com.fykj.scaffold.security.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.security.business.domain.dto.ResourceOauthDto;
import com.fykj.scaffold.security.business.domain.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据角色主键、资源类型和父资源查询资源列表
     *
     * @param roleId   角色主键
     * @param type     资源类型
     * @param parentId 父资源主键
     * @return 资源列表
     */
    List<Resource> findByRoleIdAndTypeAndParent(@Param("roleId") String roleId, @Param("type") Integer type, @Param("parentId") String parentId);

    /**
     * 根据角色主键、资源类型查询资源列表
     *
     * @param roleId 角色主键
     * @param type   资源类型
     * @return 资源列表
     */
    List<Resource> findByRoleIdAndType(@Param("roleId") String roleId, @Param("type") Integer type);

    /**
     * 获取指定角色的所有按钮权限列表
     *
     * @param roleId 指定角色主键
     * @return 按钮权限列表
     */
    List<String> findPermissionByRole(String roleId);

    /**
     * 系统初始化时查询所有权限匹配的菜单
     * @return
     */
    List<ResourceOauthDto> findAllResource();
}
