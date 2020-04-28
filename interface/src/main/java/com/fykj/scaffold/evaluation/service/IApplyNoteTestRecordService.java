package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecord;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;

import java.util.List;

/**
 * <p>
 *  申请单测试记录服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IApplyNoteTestRecordService extends IService<ApplyNoteTestRecord> {

    /**
     * @Description 保存测试记录 返回测试记录
     * @Author lmy
     * @Date 2020/4/9 13:39
     * @Param
     * @Return
     **/
    ApplyNoteTestRecord saveApplyNoteTestRecord(ApplyNoteTestRecord entity);

    /**
     * @Description 后台分页查询信息
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     **/
    IPage<ApplyNoteTestRecordVo> getListWithQuery(ApplyNoteTestRecordParams params);

    /**
    * @Description 根据id获取测试记录和对应附件信息
    * @Author lmy
    * @Date 2020/4/10 12:36
    **/
    ApplyNoteTestRecordVo getRecordInfoById(String id);

    /**
     * @Description 更新并删除附件
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     **/
    boolean updateRecordAndDelAtt(ApplyNoteTestRecord entity);


    /**
     * @Description 前端测试记录List查询
     * @Author gcc
     * @Date 2020/4/15 17:18
     * @Param
     * @Return
     **/
    List<ApplyNoteTestRecordVo> getApplyNoteTestRecord(ApplyNoteTestRecordParams params);

}
