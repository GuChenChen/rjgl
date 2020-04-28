package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecordAttachment;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordAttachmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * Mapper 接口
 * @author LiMingyang
 * @email ${email}
 * @date 2020-04-09 15:11:35
 */
public interface ApplyNoteTestRecordAttachmentMapper extends BaseMapper<ApplyNoteTestRecordAttachment> {

    List<ApplyNoteTestRecordAttachmentVo> getAttrByRecordId(@Param("id") String id);
}
