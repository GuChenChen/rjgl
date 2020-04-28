package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecord;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteTestRecordAttachmentMapper;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteTestRecordMapper;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordAttachmentService;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordService;
import com.fykj.scaffold.support.utils.DictTransUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  申请单测试记录服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class ApplyNoteTestRecordServiceImpl extends BaseServiceImpl<ApplyNoteTestRecordMapper, ApplyNoteTestRecord> implements IApplyNoteTestRecordService {

    @Autowired
    private IApplyNoteTestRecordAttachmentService iApplyNoteTestRecordAttachmentService;

    /**
     * @param entity
     * @Description 保存测试记录 返回测试记录
     * @Author lmy
     * @Date 2020/4/9 13:39
     * @Param
     * @Return
     */
    @Override
    public ApplyNoteTestRecord saveApplyNoteTestRecord(ApplyNoteTestRecord entity) {
        entity.setStartDate(LocalDateTime.now());
        if (entity.getFinished() && entity.getEndDate() == null ) {
            //测试结束 结束时间为空更新为当前时间 不为空不变
            entity.setEndDate(LocalDateTime.now());
        }
        boolean result = super.save(entity);
        if (result) {
            return entity;
        }
        return null;
    }

    /**
     * @param params
     * @Description 后台分页查询信息
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     */
    @Override
    public IPage<ApplyNoteTestRecordVo> getListWithQuery(ApplyNoteTestRecordParams params) {
        Page<ApplyNoteTestRecordVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<ApplyNoteTestRecordVo> result = baseMapper.getListWithQuery(page, params);
        DictTransUtil.trans(result.getRecords());
        return result;
    }

    /**
     * @param id
     * @Description 根据id获取测试记录和对应附件信息
     * @Author lmy
     * @Date 2020/4/10 12:36
     */
    @Override
    public ApplyNoteTestRecordVo getRecordInfoById(String id) {
        return baseMapper.getRecordInfoById(id);
    }

    /**
     * @param entity
     * @Description 更新并删除附件
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     */
    @Override
    public boolean updateRecordAndDelAtt(ApplyNoteTestRecord entity) {
        if (entity.getFinished() && entity.getEndDate() == null) {
            entity.setEndDate(LocalDateTime.now());
        }
        boolean result = retBool(baseMapper.updateById(entity));
        if(result){
            Map<String, Object> columnMap = new HashMap<String, Object>();
            columnMap.put("record_id",entity.getId());
            iApplyNoteTestRecordAttachmentService.deleteByMap(columnMap);
        }
        return result;
    }


    /**
     * @Description 前端测试记录List查询
     * @Author gcc
     * @Date 2020/4/15 17:18
     * @Param
     * @Return
     **/
    @Override
    public List<ApplyNoteTestRecordVo> getApplyNoteTestRecord(ApplyNoteTestRecordParams params) {
        List<ApplyNoteTestRecordVo> applyNoteTestRecord = baseMapper.getApplyNoteTestRecord(params);
        return applyNoteTestRecord;
    }

}
