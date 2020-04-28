package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecord;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  申请单测试记录Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface ApplyNoteTestRecordMapper extends BaseMapper<ApplyNoteTestRecord> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ApplyNoteTestRecordVo> getListWithQuery(IPage<ApplyNoteTestRecordVo> page, @Param("params") ApplyNoteTestRecordParams params);

    /**
     * 根据id获取测试记录和对应附件信息
     *
     * @param id
     * @return
     */
    ApplyNoteTestRecordVo getRecordInfoById(@Param("id") String id);

    List<ApplyNoteTestRecordVo> getApplyNoteTestRecord(@Param("params") ApplyNoteTestRecordParams params);
}
