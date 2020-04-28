package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.domain.params.SysEvaluationUserParams;
import com.fykj.scaffold.evaluation.domain.vo.TestAccountListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangf
 * @since 2020-02-25
 */
public interface SysEvaluationUserMapper extends BaseMapper<SysEvaluationUser> {
    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<TestAccountListVo> getListWithQuery(IPage<TestAccountListVo> page, @Param("params") SysEvaluationUserParams params);

    /**
    * @Description 根据申请单位id获取申请单位信息
    * @Author lmy
    * @Date 2020/4/7 10:30
    * @Param
    * @Return
    **/
    TestAccountListVo getCompanyInfoById(String id);

    /**
     * @return
     */
    List<SysEvaluationUser> findTestUserList();
}
