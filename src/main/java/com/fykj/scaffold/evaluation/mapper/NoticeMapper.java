package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.evaluation.domain.params.NoticeParams;
import org.apache.ibatis.annotations.Param;

/**
 * 通知
 *
 * Mapper 接口
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 后台分页查询信息
     *
     * @param page 分页对象
     * @param params 参数
     * @return 分页结果
     */
    IPage<Notice> getListWithQuery(IPage<Notice> page,  @Param("params") NoticeParams params);
}
