package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.dto.RoleResourceDto;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.params.RoleParams;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangf
 * @since 2019-10-16
 */
public interface IRoleService extends IService<Role> {
//    /**
//     * 查询导出数据列表
//     *
//     * @param params 查询参数
//     * @return 导出数据列表
//     */
//    List<Role> export(RoleParams params);

    /**
     * 分页角色列表
     *
     * @param params 查询条件
     * @return 分页列表
     */
    IPage<Role> page(RoleParams params);

    /**
     * 保存角色及与菜单关联表
     *
     * @param dto
     * @return
     */
    boolean save(RoleResourceDto dto);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    RoleResourceDto findOneById(String id);

    /**
     * 校验编码唯一性
     *
     * @param id
     * @param code
     * @return
     */
    boolean checkCode(String id, String code);

    /**
     * 获取指定编码的角色对象
     *
     * @param code 指定编码
     * @return 角色对象
     */
    Role getRoleByCode(String code);
}
