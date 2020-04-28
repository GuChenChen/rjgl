package com.fykj.scaffold.evaluation.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.CopInfo;
import com.fykj.scaffold.evaluation.domain.params.CopInfoParams;
import com.fykj.scaffold.evaluation.domain.vo.CopInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * Mapper 接口
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
public interface CopInfoMapper extends BaseMapper<CopInfo> {

    /**
     * 分页查询企业信息列表
     * @param params 参数
     * @return 分页结果
     */
    IPage<CopInfoVo> findByPage(IPage<CopInfoVo> page, @Param("params") CopInfoParams params);
	
}
