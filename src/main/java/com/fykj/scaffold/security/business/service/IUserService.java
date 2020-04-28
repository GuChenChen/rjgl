package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.domain.params.UserParams;
import result.Result;

import java.util.List;


/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface IUserService extends IService<User> {


    /**
     * 验证用户访问权限
     *
     * @param id  用户id
     * @param uri 请求路径
     * @return code：0-有权限， 其他-没权限
     */
    Result permission(String id, String uri);

    /**
     * 根据主键获取用户显示名称
     *
     * @param id 用户主键
     * @return 用户名称
     */
    String getNameById(String id);

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    Boolean resetPassWord(String id);

    /**
     * 修改密码
     *
     * @param oldPassWord 旧密码
     * @param newPassWord 新密码
     * @return
     */
    Result editPassWord(String oldPassWord, String newPassWord);

    /**
     * 分页查询
     *
     * @param params 用户签到情况查询参数
     * @return 返回签到用户信息
     */
    IPage<User> page(BaseParams params);

    /**
     * 通过角色编码查询相应用户列表
     * @param roleCod
     * @return
     */
    List<User> getByRoleCode(String roleCod);

    /**
     * @param mobile
     * @return
     */
    Boolean getByMobileAndSet(String mobile,String enterpriseName);

    /**
     * @param mobile
     * @param userId
     * @return
     */
    Boolean getByMobile(String mobile, String userId);

    /**
     * @param user
     * @return
     */
    Boolean updateUser(User user);
}
