package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.*;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos.ApplyNoteSumReceiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  测试申请单Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface ApplyNoteMapper extends BaseMapper<ApplyNote> {

    /**
    * @Description 获取数据库中当月最后一个申请编号
    * @Author lmy
    * @Date 2020/4/3 10:59
    **/
    ApplyNote getMaxCodeNumber();

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ApplyNoteVo> getListWithQuery(IPage<ApplyNoteVo> page, @Param("params") ApplyNoteParams params);

    /**
     * 按状态统计
     * @param params
     * @return
     */
    List<ApplyNoteSumReceiveVo> sumByStatus(@Param("params")ApplyNoteParams params);

    /**
     * 按申请时间统计
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ApplyNoteSumByApplyTimeVo> sumByApplyTime(IPage<ApplyNoteSumByApplyTimeVo> page, @Param("params") ApplyNoteParams params);

    List<ApplyNoteListVo>findByLoginUser(@Param("evaluationUserId") String evaluationUserId);

    ApplyNoteDetailVo getInformationDetail(@Param("id") String id);

    /**
     * 测试类型type翻译中文
     * @param id
     * @return
     */
    String typeCovert(@Param("id") String id);
}
