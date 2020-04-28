package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;

import java.util.List;

/**
 * <p>
 *  测试申请单附件服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IApplyNoteAttachmentService extends IService<ApplyNoteAttachment> {

    /**
     * @Description 后台分页查询信息
     * @Author lmy
     * @Date 2020/4/7 13:11
     * @Param
     * @Return
     **/
    IPage<ApplyNoteAttachmentVo> getListWithQuery(ApplyNoteAttachmentParams params);

    /**
    * @Description 测试申请批量保存附件
    * @Author lmy
    * @Date 2020/4/10 9:11
    * @Param
    * @Return
    **/
    boolean saveAttachmentList(List<ApplyNoteAttachment> applyNoteAttachmentList);


    /**
     * @Description 查询前端测试项目附件List
     * @Author gcc
     * @Date 2020/4/15 17:20
     * @Param
     * @Return
     **/
    List<ApplyNoteAttachmentVo> getApplyNoteAttachment(ApplyNoteAttachmentParams params);

    /**
     * @Description 测试申请保存附件
     * @Author gcc
     * @Date 2020/4/17 16:11
     * @Param
     * @Return
     **/
    boolean saveAttachment(ApplyNoteAttachment applyNoteAttachment);
}
