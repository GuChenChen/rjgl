package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.dto.RoleResourceDto;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.params.RoleParams;
import com.fykj.scaffold.security.business.mapper.RoleMapper;
import com.fykj.scaffold.security.business.service.IRoleResourceService;
import com.fykj.scaffold.security.business.service.IRoleService;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.support.wrapper.QueryWrapperBuilder;
import constants.Mark;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangf
 * @since 2019-10-16
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private IRoleResourceService roleResourceService;


//    @Override
//    public List<Role> export(RoleParams params) {
//        return baseMapper.export(params);
//    }

    @Override
    public IPage<Role> page(RoleParams params) {
        if (params == null) {
            params = new RoleParams();
        }
        params.setRoleLinked(currentRole().getRoleLinked());

        return super.page(params);
    }

    @Override
    public boolean save(RoleResourceDto dto) {
        Role role = new Role();
        BeanUtil.copyProperties(dto,role);
        if (StringUtil.isEmpty(dto.getId())) {
            role.setRoleLinked(currentRole().getRoleLinked() + Mark.COMMA + role.getCode());
        } else {
            role.setRoleLinked(null);
        }
        //保存角色
        boolean flag = saveOrUpdate(role);
        //保存角色与菜单关系
        roleResourceService.save(role, dto.getMenuIdList());
        return flag;
    }

    @Override
    public RoleResourceDto findOneById(String id) {
        Role role = getById(id);
        RoleResourceDto dto = new RoleResourceDto();
        BeanUtil.copyProperties(role,dto);
        List<String> resourceIds = roleResourceService.findResourceIdList(id);
        dto.setMenuIdList(resourceIds);
        return dto;
    }

    @Override
    public boolean checkCode(String id, String code) {
        return lambdaQuery().eq(Role::getCode,code).ne(StringUtil.isNotEmpty(id),Role::getId,id).count() > 0;
    }

    @Override
    public Role getRoleByCode(String code) {
        return lambdaQuery().eq(Role::getCode, code).one();
    }

    @Override
    public List<Role> list() {
        RoleParams params = new RoleParams();
        params.setRoleLinked(currentRole().getRoleLinked());
        return list(QueryWrapperBuilder.build(params));
    }
}
