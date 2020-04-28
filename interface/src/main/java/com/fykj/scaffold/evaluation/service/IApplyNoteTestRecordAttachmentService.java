package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecordAttachment;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordAttachmentVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * 服务类
 * @author LiMingyang
 * @email ${email}
 * @date 2020-04-09 15:11:35
 */
public interface IApplyNoteTestRecordAttachmentService extends IService<ApplyNoteTestRecordAttachment> {

    /**
    * @Description 申请单测试记录批量保存附件
    * @Author lmy
    * @Date 2020/4/10 14:31
    * @Param
    * @Return
    **/
    boolean saveAttachmentList(List<ApplyNoteTestRecordAttachment> applyNoteTestRecordAttachments);

    /**
     * @Description 根据测试记录id获取所有附件列表
     * @Author lmy
     * @Date 2020/4/10 14:31
     * @Param
     * @Return
     **/
    List<ApplyNoteTestRecordAttachmentVo> getAttrByRecordId(String id);

    /**
     * @Description 删除附件列表
     * @Author lmy
     * @Date 2020/4/10 14:31
     * @Param
     * @Return
     **/
    int deleteByMap(Map<String, Object> columnMap);
}

