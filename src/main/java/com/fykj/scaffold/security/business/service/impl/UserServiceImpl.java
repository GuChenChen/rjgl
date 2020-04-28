package com.fykj.scaffold.security.business.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.domain.params.UserParams;
import com.fykj.scaffold.security.business.mapper.UserMapper;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.security.business.service.IResourceService;
import com.fykj.scaffold.security.business.service.IRoleService;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.SystemUtil;
import com.fykj.scaffold.support.wrapper.QueryWrapperBuilder;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import result.Result;
import result.ResultCode;
import utils.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private RoleResourceServiceImpl roleResourceService;

    @Autowired
    private IDictService dictService;

    @Autowired
    private ISysEvaluationUserService evaluationUserService;

    private DateTimeFormatter df = DateTimeFormatter.ofPattern(Cons.DATETIME_FORMAT);

    @Override
    public Result permission(String id, String uri) {
        User user = getById(id);
        if (user == null) {
            return new Result(ResultCode.NOT_FOUND.code(), "用户不存在");
        }

        if (StringUtil.equals(user.getUsername(), "admin")) {
            //管理员拥有全部资源权限
            return new Result();
        }

        boolean exists = resourceService.exists(uri);
        if (!exists) {
            //资源不存在，不需要鉴权
            return new Result();
        }

        //是否需要权限验证（需要鉴权的存储在数据库，不存的不需要鉴权）
        boolean hasAuth = roleResourceService.exists(user.getRoleId(), uri);
        if (hasAuth) {
            return new Result();
        }

        return new Result(ResultCode.TOKEN_FORBIDDEN_CODE);
    }

    @Override
    public String getNameById(String id) {
        return getById(id).getUsername();
    }


    @Override
    public Boolean resetPassWord(String id) {
        User user = getById(id);
        user.setPassword(passwordEncoder.encode(Cons.INIT_PSD));
        return super.updateById(user);
    }

    @Override
    public Result editPassWord(String oldPassWord, String newPassWord) {
        BackendUserDetail userInfo = getUser();
        if (userInfo == null) {
            return new Result(ResultCode.FAIL.code(), "获取当前用户失败，请重试");
        }
        User user = getById(userInfo.getId());
        if (user == null || !passwordEncoder.matches(oldPassWord, user.getPassword())) {
            return new Result(ResultCode.FAIL.code(), "请输入正确的旧密码");
        }

        user.setPassword(passwordEncoder.encode(newPassWord));
        if (super.updateById(user)) {
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }


    @Override
    public IPage<User> page(BaseParams params) {
        ((UserParams)params).setRoleLinked(currentRole().getRoleLinked());
        QueryWrapper<User> queryWrapper = QueryWrapperBuilder.build(params);
        IPage<User> result = page(params.getPage(), queryWrapper);
        return result.convert(this::convert);
    }

    @Override
    public boolean save(User user) {
        //验证用户名重复
        if (usernameExists(user.getUsername())) {
            throw new BusinessException(ResultCode.FAIL, "用户名已存在");
        }
        if (StringUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        updateRoleLinked(user);
        if(StringUtil.isEmpty(user.getType())){
            user.setType("sut_normal");
        }
        return super.save(user);
    }

    @Override
    public boolean updateById(User user) {
        if (StringUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        updateRoleLinked(user);
        user.setType("sut_normal");
        return super.updateById(user);
    }

    private User convert (User entity){
        entity.setRoleName(roleService.getById(entity.getRoleId()).getName());
        entity.setStatusName(entity.getStatus()?"正常":"禁用");
        entity.setCreateDateText(entity.getCreateDate().format(df));
        return entity;
    }

    private boolean usernameExists(String username) {
        return lambdaQuery().eq(User::getUsername, username)
                .count() > 0;
    }

    private void updateRoleLinked(User user) {
        Role role = roleService.getById(user.getRoleId());
        user.setRoleLinked(role.getRoleLinked());
    }

    @Override
    public List<User> getByRoleCode(String roleCode){
        if(StringUtils.isEmpty(roleCode)){
            return super.list();
        }
        return lambdaQuery().eq(User::getRoleId,roleService.getRoleByCode(roleCode).getId()).list();
    }

    @Override
    public Boolean getByMobileAndSet(String mobile,String enterpriseName){
        if(ObjectUtils.isEmpty(lambdaQuery().eq(User::getMobile,mobile).one())){
            Role role = roleService.getRoleByCode("test_account_role");
            User user = new User().setUsername(mobile).setPassword("123456").setMobile(mobile).setRoleId(role.getId()).setRoleLinked(role.getRoleLinked()).setType("sut_test_account").setStatus(true);
            save(user);
            SysEvaluationUser evaluationUser = new SysEvaluationUser().setUserId(lambdaQuery().eq(User::getMobile,mobile).one().getId()).setCompanyName(enterpriseName);
            evaluationUserService.save(evaluationUser);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean getByMobile(String mobile,String userId){
        if(!StringUtils.isEmpty(userId)){
            if(ObjectUtils.isEmpty(lambdaQuery().eq(User::getType,"sut_test_account").eq(User::getMobile,mobile).ne(User::getId,userId).eq(User::getDeleted,false).one())){
                return true;
            }else{
                return false;
            }
        }else{
            if(ObjectUtils.isEmpty(lambdaQuery().eq(User::getType,"sut_test_account").eq(User::getMobile,mobile).eq(User::getDeleted,false).one())){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public Boolean updateUser(User user){
        return lambdaUpdate().eq(User::getId,user.getId())
                .set(User::getUsername,user.getUsername())
                .set(User::getEmail,user.getEmail())
                .set(User::getRoleId,user.getRoleId())
                .set(User::getStatus,user.getStatus())
                .set(User::getMobile,user.getMobile())
                .set(User::getName,user.getName())
                .set(User::getType,user.getType())
                .set(User::getRoleLinked,user.getRoleLinked())
                .set(User::getVersion,user.getVersion()+1)
                .set(User::getUpdateDate, LocalDateTime.now())
                .set(User::getUpdater, SystemUtil.getUser().getId())
                .update();
    }
}
