package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  测试申请单附件Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface ApplyNoteAttachmentMapper extends BaseMapper<ApplyNoteAttachment> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ApplyNoteAttachmentVo> getListWithQuery(IPage<ApplyNoteAttachmentVo> page, @Param("params") ApplyNoteAttachmentParams params);

    List<ApplyNoteAttachmentVo> getApplyNoteAttachment(@Param("params") ApplyNoteAttachmentParams params);

}
