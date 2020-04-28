package com.fykj.scaffold.security.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.security.business.domain.entity.ApiManage;
import org.apache.ibatis.annotations.Param;

public interface ApiManageMapper extends BaseMapper<ApiManage> {


    /**
     * 根据id跟新接口状态
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateStatusById(@Param("id")String id,@Param("status")Boolean status);
}
