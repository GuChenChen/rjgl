package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.Resource;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.entity.RoleResource;
import com.fykj.scaffold.security.business.mapper.RoleResourceMapper;
import com.fykj.scaffold.security.business.service.IResourceService;
import com.fykj.scaffold.security.business.service.IRoleResourceService;
import com.fykj.scaffold.security.business.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean exists(String roleId, String uri) {
        return lambdaQuery().eq(RoleResource::getRoleId, roleId)
                .eq(RoleResource::getResourceUrl, uri).count() > 0;
    }

    @Override
    public boolean save(String roleId, String resourceId) {
        return save(build(roleId, resourceId));
    }

    @Override
    public boolean save(Role role, List<String> resourceIds) {
        String roleId = role.getId();
        baseMapper.deleteRoleResourceByRoleId(roleId);
        if (CollectionUtils.isEmpty(resourceIds)) {
            return true;
        }

        Map<String, String> resourceNameMap = resourceService.list().stream().collect(Collectors.toMap(Resource::getId, Resource::getName));
        int index = resourceIds.indexOf("-666666");
        resourceIds.remove("-666666");
        String roleName = role.getName();

        List<RoleResource> rsList = resourceIds.stream().map(it -> {
            RoleResource rs = new RoleResource();
            rs.setRoleId(roleId);
            rs.setRoleName(roleName);
            rs.setResourceId(it);
            rs.setResourceName(resourceNameMap.get(it));
            return rs;
        }).collect(Collectors.toList());
        rsList.stream().skip(index).forEach(it -> it.setHalfChecked(true));
        return saveBatch(rsList);
    }

    @Override
    public List<String> findResourceIdList(String roleId) {
        return baseMapper.findResourceIdList(roleId);
    }

    private RoleResource build(String roleId, String resourceId) {
        RoleResource rs = new RoleResource();
        Role role = roleService.getById(roleId);
        rs.setRoleId(roleId);
        rs.setRoleName(role.getName());
        rs.setResourceId(resourceId);
        Resource resource = resourceService.getById(resourceId);
        if (resource != null) {
            rs.setResourceName(resource.getName());
        }
        return rs;
    }


}
