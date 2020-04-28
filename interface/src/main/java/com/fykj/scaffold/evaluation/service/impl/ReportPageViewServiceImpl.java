package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ReportPageView;
import com.fykj.scaffold.evaluation.domain.params.ReportPageParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPageViewParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPagesParams;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchOneVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchVo;
import com.fykj.scaffold.evaluation.mapper.ReportPageViewMapper;
import com.fykj.scaffold.evaluation.service.IReportPageViewService;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  模块使用记录服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class ReportPageViewServiceImpl extends BaseServiceImpl<ReportPageViewMapper, ReportPageView> implements IReportPageViewService {

    @Autowired
    private IDictService dictService;

    @Autowired
    private ISysEvaluationUserService sysEvaluationUserService;

    @Override
    public void saveReportPageByCode(String code) {
        ReportPageView reportPageView = new ReportPageView();
        String pageName = dictService.getNameByCode(code);
        reportPageView.setViewTime(LocalDate.now());
        reportPageView.setPageCode(code);
        reportPageView.setPageText(pageName);
        BackendUserDetail user = SystemUtil.getUser();
        //登陆用户存单位ID
        if (!StringUtils.isEmpty(user)){
            reportPageView.setEvaluationUserId(sysEvaluationUserService.getByLoginUser().getId());
        }
        reportPageView.setIp(SystemUtil.getClientIp());
        save(reportPageView);
    }

    @Override
    public void saveFirstDetail(String content) {
        ReportPageView reportPageView = new ReportPageView();
        String pageName = dictService.getNameByCode("simulatedQuotation");
        reportPageView.setViewTime(LocalDate.now());
        reportPageView.setPageCode("simulatedQuotation");
        reportPageView.setPageText(pageName);
        reportPageView.setContent(content);
        BackendUserDetail user = SystemUtil.getUser();
        //登陆用户存单位ID
        if (!StringUtils.isEmpty(user)){
            reportPageView.setEvaluationUserId(sysEvaluationUserService.getByLoginUser().getUserId());
        }
        reportPageView.setIp(SystemUtil.getClientIp());
        save(reportPageView);
    }

    /**
    * @Description: 根据前台传来的时间将结束时间加一个月
    * @Param: [viewTimeEnd]
    * @return: java.lang.String
    * @Author: Mr.Wei
    * @Date: 2020-4-8
    */
    public String getViewTimeEnd(String viewTimeEnd){
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        if ( !StringUtils.isEmpty(viewTimeEnd)){
            try {
                Date dd = df.parse(viewTimeEnd);
                calendar.setTime(dd);
                calendar.add(Calendar.MONTH, 1);//加一月
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return df.format(calendar.getTime());
    }

    @Override
    public IPage<ReportPageVo> getMapWithQuery(ReportPageViewParams params) {
        Page<ReportPageVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        List<Dict> listWithQuery = dictService.findByParentCode("moduleName");
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(this.getViewTimeEnd(params.getViewTimeEnd()));
        }
        IPage<ReportPageVo> result = baseMapper.getMapWithQuery(page, params, listWithQuery);
        return result;
    }

    @Override
    public IPage<ReportPageViewVo> getListWithQuery(ReportPageParams params) {
        Page<ReportPageViewVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        List<Dict> listWithQuery = dictService.findByParentCode("moduleName");
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(this.getViewTimeEnd(params.getViewTimeEnd()));
        }
        IPage<ReportPageViewVo> result = baseMapper.getListWithQuery(page, params, listWithQuery);
        return result;
    }

    @Override
    public IPage<ReportPageViewVo> getListByUserId(ReportPagesParams params) {
        Page<ReportPageViewVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        List<Dict> listWithQuery = dictService.findByParentCode("moduleName");
        IPage<ReportPageViewVo> result = baseMapper.getListByUserId(page, params, listWithQuery);
        return result;
    }

    @Override
    public List<WorkBenchVo> getWorkBenchPage() {
        List<Dict> listWithQuery = dictService.findByParentCode("moduleName");
        List<WorkBenchVo> workBenchPage = baseMapper.getWorkBenchPage(listWithQuery);
        return workBenchPage;
    }

    @Override
    public List<WorkBenchOneVo> getWorkBenchPageOne() {
        List<WorkBenchOneVo> workBenchOneVos = baseMapper.getWorkBenchPageOne();
        return workBenchOneVos;
    }

    @Override
    public WorkBenchVo getWorkBenchPageNum() {
        WorkBenchVo workBenchVo = new WorkBenchVo();
        Integer toDayNum = baseMapper.getToDayNum();
        Integer processing = baseMapper.getProcessing();
        Integer completedItem = baseMapper.getCompletedItem();
        if ( 0 != toDayNum){
            workBenchVo.setToDays("今日新增注册数 | "+toDayNum);
        }else {
            workBenchVo.setToDays("今日新增注册数 | "+0);
        }
        if ( 0 != processing){
            workBenchVo.setProCe("处理中的申请数 | "+processing);
        }else {
            workBenchVo.setProCe("处理中的申请数 | "+0);
        }
        if ( 0 != completedItem){
            workBenchVo.setCompletedItems("已结项申请数 | "+completedItem);
        }else {
            workBenchVo.setCompletedItems("已结项申请数 | "+0);
        }
        return workBenchVo;
    }

}
