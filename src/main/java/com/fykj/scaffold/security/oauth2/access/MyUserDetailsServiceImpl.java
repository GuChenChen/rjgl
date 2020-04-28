package com.fykj.scaffold.security.oauth2.access;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.service.IRoleService;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.utils.RegexUtils;
import exception.BusinessException;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import result.ResultCode;

/**
 * @author wangf
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        Wrapper<User> queryWrapper;
        if(RegexUtils.isMobileExact(param)){
            queryWrapper = new QueryWrapper<>(User.builder().mobile(param).build());
        }else{
            queryWrapper = new QueryWrapper<>(User.builder().username(param).build());
        }
        User one = userService.getOne(queryWrapper, true);

        if (one == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "该用户不存在");
        }
        BackendUserDetail user = new BackendUserDetail(RegexUtils.isMobileExact(param)?one.getUsername():param, one.getPassword(),
                one.getStatus(),
                true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(one.getRoleId().toString())
        );
        user.setId(one.getId());
        user.setNickName(one.getName());
        user.setType(one.getType());
        BeanUtils.copyProperties(one, user);
        Role role = roleService.getById(one.getRoleId());
        user.setRoleCode(role.getCode());
        return user;
    }
}
