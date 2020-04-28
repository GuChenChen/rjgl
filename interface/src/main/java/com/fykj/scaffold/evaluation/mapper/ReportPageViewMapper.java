package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.ReportPageView;
import com.fykj.scaffold.evaluation.domain.params.ReportPageParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPageViewParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPagesParams;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchOneVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchVo;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface ReportPageViewMapper extends BaseMapper<ReportPageView> {

    /**
     * 前端各模块浏览量统计分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ReportPageVo> getMapWithQuery(IPage<ReportPageVo> page, @Param("params") ReportPageViewParams params , @Param("List") List<Dict> idList);

    /**
    * @Description: 用户页面使用情况汇总查询信息
    * @Param: [page, params, idList]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo>
    * @Author: Mr.Wei
    * @Date: 2020-4-3
    */
    IPage<ReportPageViewVo> getListWithQuery(IPage<ReportPageViewVo> page, @Param("params") ReportPageParams params , @Param("List") List<Dict> idList);
    
    /** 
    * @Description: 测试单位账号管理单位详情
    * @Param: [page, params, idList] 
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo> 
    * @Author: Mr.Wei 
    * @Date: 2020-4-8 
    */
    IPage<ReportPageViewVo> getListByUserId(IPage<ReportPageViewVo> page, @Param("params") ReportPagesParams params , @Param("List") List<Dict> idList);

    /**
    * @Description:  获取今日各模块浏览量
    * @Param: [idList]
    * @return: java.util.List<com.fykj.scaffold.evaluation.domain.vo.workBenchVo>
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    List<WorkBenchVo> getWorkBenchPage(@Param("List") List<Dict> idList);

    /**
    * @Description: 近6个月 测试申请数及订单金额对比
    * @Param: []
    * @return: java.util.List<com.fykj.scaffold.evaluation.domain.vo.workBenchOneVo>
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    List<WorkBenchOneVo> getWorkBenchPageOne();

    /**
    * @Description: 今日新增注册数
    * @Param: []
    * @return: Integer
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    Integer getToDayNum();

    /**
    * @Description:  处理中的申请数
    * @Param: []
    * @return: Integer
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    Integer getProcessing();

    /**
    * @Description: 已结项申请数
    * @Param: []
    * @return: Integer
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    Integer getCompletedItem();
}
