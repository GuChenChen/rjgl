package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecordAttachment;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteTestRecordAttachmentMapper;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordAttachmentService;
import com.fykj.scaffold.support.utils.DictTransUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * 服务实现类
 * @author LiMingyang
 * @email ${email}
 * @date 2020-04-09 15:11:35
 */
@Service
public class ApplyNoteTestRecordAttachmentServiceImpl extends BaseServiceImpl<ApplyNoteTestRecordAttachmentMapper, ApplyNoteTestRecordAttachment> implements IApplyNoteTestRecordAttachmentService {

    /**
     * @param applyNoteTestRecordAttachments
     * @Description 申请单测试记录批量保存附件
     * @Author lmy
     * @Date 2020/4/10 14:31
     * @Param
     * @Return
     */
    @Override
    public boolean saveAttachmentList(List<ApplyNoteTestRecordAttachment> applyNoteTestRecordAttachments) {
        if (applyNoteTestRecordAttachments.size() > 0) {
            Map<String, Object> columnMap = new HashMap<String, Object>();
            columnMap.put("record_id",applyNoteTestRecordAttachments.get(0).getRecordId());
            super.removeByMap(columnMap);
        }
        applyNoteTestRecordAttachments.forEach(attachment -> {
            if (StringUtils.isNotEmpty(attachment.getId())) {
                attachment.setId("");
            }
        });
        return super.saveBatch(applyNoteTestRecordAttachments);
    }

    /**
     * @param id
     * @Description 根据测试记录id获取所有附件列表
     * @Author lmy
     * @Date 2020/4/10 14:31
     * @Param
     * @Return
     */
    @Override
    public List<ApplyNoteTestRecordAttachmentVo> getAttrByRecordId(String id) {
        List<ApplyNoteTestRecordAttachmentVo> list = baseMapper.getAttrByRecordId(id);
        return list;
    }

    /**
     * @Description 删除附件列表
     * @Author lmy
     * @Date 2020/4/10 14:31
     * @Param
     * @Return
     **/
    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return baseMapper.deleteByMap(columnMap);
    }

    private ApplyNoteTestRecordAttachmentVo covert(ApplyNoteTestRecordAttachmentVo vo){
        DictTransUtil.trans(vo);
        return vo;
    }
}