package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ReportPageView;
import com.fykj.scaffold.evaluation.domain.params.ReportPageParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPageViewParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPagesParams;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchOneVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchVo;

import java.util.List;

/**
 * <p>
 *  模块使用记录服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IReportPageViewService extends IService<ReportPageView> {

    /**
     * @Description: 根据前台传来code存入浏览信息
     * @Param: [code]
     * @return: void
     * @Author: Mr.Wei
     * @Date: 2020-4-2
     */
    void saveReportPageByCode(String code);

    /**
    * @Description: 获取报价存入浏览数据
    * @Param: [content]
    * @return: void
    * @Author: Mr.Wei
    * @Date: 2020-4-8
    */
    void saveFirstDetail(String content);

    /**
     * 前端各模块浏览量统计分页查询信息
     *
     * @param params
     * @return
     */
    IPage<ReportPageVo> getMapWithQuery(ReportPageViewParams params);

    /**
     * 用户页面使用情况汇总分页查询信息
     *
     * @param params
     * @return
     */
    IPage<ReportPageViewVo> getListWithQuery(ReportPageParams params);

    /** 
    * @Description: 测试单位账号管理单位详情
    * @Param: [params] 
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo> 
    * @Author: Mr.Wei 
    * @Date: 2020-4-8 
    */
    IPage<ReportPageViewVo> getListByUserId(ReportPagesParams params);

    /**
    * @Description: 获取今日各模块浏览量
    * @Param: []
    * @return: java.util.List<com.fykj.scaffold.evaluation.domain.vo.workBenchVo>
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    List<WorkBenchVo> getWorkBenchPage();

    /**
    * @Description: 近6个月 测试申请数及订单金额对比
    * @Param: []
    * @return: java.util.List<com.fykj.scaffold.evaluation.domain.vo.workBenchOneVo>
    * @Author: Mr.Wei
    * @Date: 2020-4-10
    */
    List<WorkBenchOneVo> getWorkBenchPageOne();

    WorkBenchVo getWorkBenchPageNum();
}
