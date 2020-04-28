package com.fykj.scaffold.evaluation.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.domain.params.SysEvaluationUserParams;
import com.fykj.scaffold.evaluation.domain.vo.TestAccountListVo;
import com.fykj.scaffold.evaluation.mapper.SysEvaluationUserMapper;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.service.IRoleService;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import result.ResultCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangf
 * @since 2020-02-25
 */
@Service
public class SysEvaluationUserServiceImpl extends BaseServiceImpl<SysEvaluationUserMapper, SysEvaluationUser> implements ISysEvaluationUserService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public IPage<TestAccountListVo> getListWithQuery(SysEvaluationUserParams params) {
        Page<TestAccountListVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<TestAccountListVo> result = baseMapper.getListWithQuery(page, params);
        return result;
    }

    /**
     * @param id
     * @Description 根据申请单位id获取申请单位信息
     * @Author lmy
     * @Date 2020/4/7 10:30
     * @Param
     * @Return
     */
    @Override
    public TestAccountListVo getCompanyInfoById(String id) {
        return baseMapper.getCompanyInfoById(id);
    }

    @Override
    public TestAccountListVo findDetail(String id) {
        TestAccountListVo vo = new TestAccountListVo();
        SysEvaluationUser entity = super.getById(id);
        if(entity == null){
            return null;
        }
        BeanUtils.copyProperties(entity,vo);
        vo.setEvaluationVersion(entity.getVersion());
        User user = userService.getById(entity.getUserId());
        vo.setName(user.getName());
        vo.setMobile(user.getMobile());
        vo.setEmail(user.getEmail());
        vo.setStatus(user.getStatus());
        vo.setUserVersion(user.getVersion());
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTestAccount(TestAccountListVo vo) {
        if(validateMobile(vo.getMobile(),null)){
            User user = new User();
            BeanUtil.copyProperties(vo,user);
            Role role = roleService.getRoleByCode(Cons.ROLE_CODE_TEST_ACCOUNT);
            user.setRoleId(role.getId());
            user.setRoleLinked(role.getRoleLinked());
            user.setType("sut_test_account");
            user.setUsername(vo.getMobile());
            user.setPassword(Cons.INIT_PSD);
            if(userService.save(user)){
                SysEvaluationUser entity = new SysEvaluationUser();
                BeanUtils.copyProperties(vo,entity);
                entity.setUserId(user.getId());
                return super.save(entity);
            }
        }else{
            throw new BusinessException(ResultCode.FAIL,"该手机号用户已存在");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTestAccount(TestAccountListVo vo) {
        SysEvaluationUser seu = getById(vo.getId());
        if(validateMobile(vo.getMobile(),vo.getId())){
            User user = new User();
            BeanUtil.copyProperties(vo,user);
            Role role = roleService.getRoleByCode(Cons.ROLE_CODE_TEST_ACCOUNT);
            user.setRoleId(role.getId());
            user.setRoleLinked(role.getRoleLinked());
            user.setType("sut_test_account");
            user.setId(seu.getUserId());
            user.setVersion(vo.getUserVersion());
            user.setUsername(vo.getMobile());
            if(userService.updateUser(user)){
                SysEvaluationUser entity = new SysEvaluationUser();
                BeanUtils.copyProperties(vo,entity);
                entity.setUserId(user.getId());
                entity.setVersion(vo.getEvaluationVersion());
                return super.updateById(entity);
            }
        }else{
            throw new BusinessException(ResultCode.FAIL,"该手机号用户已存在");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        SysEvaluationUser entity = super.getById(id);
        userService.removeById(entity.getUserId());
        return super.removeById(id);
    }

    private boolean validateMobile(String mobile, String id){
        String userId = null;
        if(!StringUtils.isEmpty(id)){
            userId = getById(id).getUserId();
        }
        return userService.getByMobile(mobile,userId);
    }

    @Override
    public SysEvaluationUser getByLoginUser(){
        BackendUserDetail user = SystemUtil.getUser();
        if(Cons.TEST_USER.equalsIgnoreCase(user.getType())){
            return lambdaQuery().eq(SysEvaluationUser::getUserId, SystemUtil.getUser().getId()).one();
        }
        return null;
    }

    @Override
    public void setLastLoginTime(String id){
        lambdaUpdate().eq(SysEvaluationUser::getId,id).set(SysEvaluationUser::getLastLoginDate, LocalDateTime.now()).update();
    }

    @Override
    public SysEvaluationUser getByUserId(String userId){
        return lambdaQuery().eq(SysEvaluationUser::getUserId, userId).one();
    }

    @Override
    public List<SysEvaluationUser> list(){
       return baseMapper.findTestUserList();
    }
}
