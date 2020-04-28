package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.dto.ResourceOauthDto;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.security.business.domain.dto.NavDto;
import com.fykj.scaffold.security.business.domain.entity.Resource;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.mapper.ResourceMapper;
import com.fykj.scaffold.security.business.service.IResourceService;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.utils.SystemUtil;
import constants.Mark;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import result.ResultCode;
import utils.StringUtil;

import java.util.*;
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
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    private IUserService userService;

    @Override
    public NavDto menus() {
        String roleId = getRoleId();
        List<Resource> resources = baseMapper.findByRoleIdAndType(roleId, Cons.RESOURCE_TYPE_MENU);
        String[] perms = String.join(Mark.COMMA, baseMapper.findPermissionByRole(roleId)).split(Mark.COMMA);
        List<String> permissions = Arrays.asList(perms);
        NavDto dto = new NavDto();
        dto.setMenuList(buildTree(resources));
        dto.setPermissions(permissions);
        return dto;
    }

    @Override
    public List<Resource> buttons(String menuId) {
        if (StringUtil.isEmpty(menuId)) {
            //不指定菜单主键返回空的集合
            return Collections.emptyList();
        }

        List<Resource> buttons = baseMapper.findByRoleIdAndTypeAndParent(getRoleId(), Cons.RESOURCE_TYPE_BUTTON, menuId);
        if (CollectionUtils.isEmpty(buttons)) {
            return Collections.emptyList();
        }
        return buttons.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public boolean exists(String uri) {
        return lambdaQuery().eq(Resource::getUrl, uri).count() > 0;
    }

    @Override
    public List<Resource> tree(String roleId) {
        if (!StringUtil.isEmpty(roleId)) {
            return buildTree(baseMapper.findByRoleIdAndType(roleId, null));
        }

        BackendUserDetail user = SystemUtil.getUser();
        if (user == null) {
            throw new BusinessException(ResultCode.FAIL, "请登录系统");
        }

        if (StringUtil.equals(user.getRoleCode(), Cons.ROLE_CODE_ADMIN)) {
            return buildTree(list());
        }
        return tree(user.getRoleId());
    }

    @Override
    public List<Resource> findByRoleId(String roleId) {
        List<Resource> list = baseMapper.findByRoleIdAndType(roleId, null);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<Resource> list() {
        return list(new QueryWrapper<Resource>().orderByAsc("sequence"));
    }

    private List<Resource> buildTree(List<Resource> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<Resource> dtos = list.stream().map(this::convert).collect(Collectors.toList());
        Map<String, List<Resource>> childrenMap = dtos.stream().filter(it -> Objects.nonNull(it.getParentId()))
                .collect(Collectors.groupingBy(Resource::getParentId));
        List<Resource> tree = dtos.stream().filter(it -> Objects.isNull(it.getParentId())).collect(Collectors.toList());
        tree.forEach(it -> buildTree(it, childrenMap));
        return tree;
    }

    private void buildTree(Resource dto, Map<String, List<Resource>> childrenMap) {
        List<Resource> children = childrenMap.get(dto.getId());
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        dto.setChildren(children);
        children.forEach(it -> buildTree(it, childrenMap));
    }

    /**
     * 获取当前登录用户角色主键
     *
     * @return 角色主键
     */
    private String getRoleId() {
        User user = userService.getById(getUser().getId());
        return user.getRoleId();
    }

    private Resource convert(Resource resource) {

        switch (resource.getType()) {
            case 0:
                resource.setTypeText("接口");
                break;
            case 1:
                resource.setTypeText("菜单");
                break;
            case 2:
                resource.setTypeText("按钮");
                break;
            default:
                resource.setTypeText("其他");
        }

        return resource;

    }

    @Override
    public List<ResourceOauthDto> findResourceList() {
        return baseMapper.findAllResource();
    }

}
