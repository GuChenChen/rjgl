package com.fykj.scaffold.security.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.params.RoleParams;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询导出数据到Excel文件
     *
     * @param params 查询参数
     * @return 导出数据列表
     */
    List<Role> export(@Param("params") RoleParams params);
}
