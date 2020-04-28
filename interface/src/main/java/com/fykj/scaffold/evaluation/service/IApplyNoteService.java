package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteDetailVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteListVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByApplyTimeVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos.ApplyNoteSumByStatusVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;

import java.util.List;

/**
 * <p>
 *  测试申请单服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IApplyNoteService extends IService<ApplyNote> {

    /**
    * @Description
    * @Author lmy
    * @Date 2020/4/7 13:11
    * @Param
    * @Return
    **/
    ApplyNote getMaxCodeNumber();

    /**
    * @Description 后台分页查询信息
    * @Author lmy
    * @Date 2020/4/7 13:11
    * @Param
    * @Return
    **/
    IPage<ApplyNoteVo> getListWithQuery(ApplyNoteParams params);

    /**
    * @Description 保存申请单信息 返回申请单
    * @Author lmy
    * @Date 2020/4/9 13:39
    * @Param
    * @Return
    **/
    ApplyNote saveApplyNote(ApplyNote entity);

    /**
     * @Description 修改申请单信息 返回申请单
     * @Author lmy
     * @Date 2020/4/9 13:39
     * @Param
     * @Return
     **/
    ApplyNote updateApplyNote(ApplyNote entity);

    /**
     * 按状态统计测试申请
     * @param params
     * @return
     */
    ApplyNoteSumByStatusVo sumByStatus(ApplyNoteParams params);

    /**
     * 按申请时间统计测试申请
     * @param params
     * @return
     */
    IPage<ApplyNoteSumByApplyTimeVo> sumByApplyTime(ApplyNoteParams params);

    /**
     * 按状态统计测试申请
     * @param sum
     */
    void writeExcel(ApplyNoteSumByStatusVo sum);

    /**
     * 按登录用户获取测试申请
     *
     * @return
     */
    List<ApplyNoteListVo> findByLoginUser();

    /**
     * @Description 查询前端测试详情
     * @Author gcc
     * @Date 2020/4/15 17:30
     * @Param
     * @Return
     **/
    ApplyNoteDetailVo getInformationDetail(String id);

    String typeCovert(String id);
}
