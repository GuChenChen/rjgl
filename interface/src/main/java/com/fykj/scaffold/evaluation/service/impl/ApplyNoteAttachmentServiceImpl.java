package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteAttachmentMapper;
import com.fykj.scaffold.evaluation.service.IApplyNoteAttachmentService;
import com.fykj.scaffold.support.utils.DictTransUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  测试申请单附件服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class ApplyNoteAttachmentServiceImpl extends BaseServiceImpl<ApplyNoteAttachmentMapper, ApplyNoteAttachment> implements IApplyNoteAttachmentService {

    /**
     * @param params
     * @Description 后台分页查询信息
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     */
    @Override
    public IPage<ApplyNoteAttachmentVo> getListWithQuery(ApplyNoteAttachmentParams params) {
        Page<ApplyNoteAttachmentVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<ApplyNoteAttachmentVo> result = baseMapper.getListWithQuery(page, params).convert(this::covert);
        return result;
    }

    private ApplyNoteAttachmentVo covert(ApplyNoteAttachmentVo vo){
        DictTransUtil.trans(vo);
        return vo;
    }

    /**
     * @param applyNoteAttachmentList
     * @Description 测试申请批量保存附件
     * @Author lmy
     * @Date 2020/4/10 9:11
     * @Param
     * @Return
     */
    @Override
    public boolean saveAttachmentList(List<ApplyNoteAttachment> applyNoteAttachmentList) {
        if (applyNoteAttachmentList.size() > 0) {
            Map<String, Object> columnMap = new HashMap<String, Object>();
            columnMap.put("apply_note_id",applyNoteAttachmentList.get(0).getApplyNoteId());
            super.removeByMap(columnMap);
        }
        applyNoteAttachmentList.forEach(applyNoteAttachment -> {
            if (StringUtils.isNotEmpty(applyNoteAttachment.getId())) {
                applyNoteAttachment.setId("");
            }
        });
        return super.saveBatch(applyNoteAttachmentList);
    }

    /**
     * @Description 查询前端测试项目附件List
     * @Author gcc
     * @Date 2020/4/15 17:20
     * @Param
     * @Return
     **/
    @Override
    public List<ApplyNoteAttachmentVo> getApplyNoteAttachment(ApplyNoteAttachmentParams params) {
        List<ApplyNoteAttachmentVo> applyNoteAttachment = baseMapper.getApplyNoteAttachment(params);
        return applyNoteAttachment;
    }

    @Override
    public boolean saveAttachment(ApplyNoteAttachment applyNoteAttachment) {
        return super.save(applyNoteAttachment);
    }


}
