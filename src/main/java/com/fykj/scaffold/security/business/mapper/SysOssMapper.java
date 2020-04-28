package com.fykj.scaffold.security.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.security.business.domain.dto.SysOssDto;
import com.fykj.scaffold.security.business.domain.entity.SysOss;
import com.fykj.scaffold.security.business.domain.params.OssParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文件上传Mapper 接口
 * </p>
 *
 * @author zhangzhi
 */
@Mapper
public interface SysOssMapper extends BaseMapper<SysOss> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param ossParams
     * @return
     */
    IPage<SysOssDto> getListWithQuery(IPage<SysOssDto> page, @Param("params") OssParams ossParams);
}
