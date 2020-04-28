package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.*;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos.*;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteMapper;
import com.fykj.scaffold.evaluation.service.IApplyNoteService;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordService;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.DictTransUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import result.ResultCode;
import utils.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  测试申请单 服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class ApplyNoteServiceImpl extends BaseServiceImpl<ApplyNoteMapper, ApplyNote> implements IApplyNoteService {

    @Value("${sumByStatus.excel}")
    private String sumByStatusExcel;

    @Autowired
    private IDictService dictService;

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");

    @Autowired
    private ISysEvaluationUserService sysEvaluationUserService;

    @Autowired
    private IApplyNoteTestRecordService iApplyNoteTestRecordService;

    /**
    * @Description 申请单信息组成
    * @Author lmy
    * @Date 2020/4/3 10:18
    **/
    @Override
    public boolean save(ApplyNote entity) {

        return super.save(entity);
    }

    /**
     * @Description 获取数据库中当月最后一个申请编号
     * @Author lmy
     * @Date 2020/4/3 10:58
     **/
    @Override
    public ApplyNote getMaxCodeNumber() {
        return baseMapper.getMaxCodeNumber();
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
    public IPage<ApplyNoteVo> getListWithQuery(ApplyNoteParams params) {
        Page<ApplyNoteVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if(StringUtils.isNotEmpty(params.getStartDate()) && StringUtils.isNotEmpty(params.getEndDate())){
            params.setStartDate(params.getStartDate()+" 00:00:00");
            params.setEndDate(params.getEndDate()+" 23:59:59");
        }
        IPage<ApplyNoteVo> result = baseMapper.getListWithQuery(page, params).convert(this::covert);
        return result;
    }

    private ApplyNoteVo covert(ApplyNoteVo vo){
        DictTransUtil.trans(vo);
        return vo;
    }

    /**
     * @param entity
     * @Description 保存申请单信息 返回申请单
     * @Author lmy
     * @Date 2020/4/9 13:39
     * @Param
     * @Return
     */
    @Override
    public ApplyNote saveApplyNote(ApplyNote entity) {
        //申请单编号生成
        String codeTime = DateFormatUtils.format(new Date(),"yyyy-MM").replace("-","");
        String serialNumber = "001";
        ApplyNote applyNote = getMaxCodeNumber();
        if (!ObjectUtils.isEmpty(applyNote)){
            DecimalFormat decimalFormat=new DecimalFormat("000");
            serialNumber = decimalFormat.format(Integer.parseInt(applyNote.getCode().substring(6,9))+1);
        }
        Random random = new Random();
        int ends = random.nextInt(99);
        String codeRandom = String.format("%02d",ends);
        entity.setCode(codeTime+serialNumber+codeRandom);
        //TODO 测试状态为测试通过 发送短信
        TestAccountListVo testAccountListVo = sysEvaluationUserService.getCompanyInfoById(entity.getEvaluationUserId());
        entity.setTel(testAccountListVo.getMobile());
        if ("passApply".equals(entity.getStatus())){
            //测试通过 更新测试结束时间为当前时间
            entity.setEndDate(LocalDateTime.now());
        }
        boolean result = super.save(entity);
        if (result) {
            return entity;
        }
        return null;
    }

    /**
     * @param entity
     * @Description 修改申请单信息 返回申请单
     * @Author lmy
     * @Date 2020/4/9 13:39
     * @Param
     * @Return
     */
    @Override
    public ApplyNote updateApplyNote(ApplyNote entity) {
        if ("passApply".equals(entity.getStatus())){
            //测试通过 更新测试结束时间为当前时间
            entity.setEndDate(LocalDateTime.now());
        } else {
            //测试不通过 结束时间为空
            entity.setEndDate(null);
        }
        boolean result = super.updateById(entity);
        if (result) {
            return entity;
        }
        return null;
    }

    @Override
    public ApplyNoteSumByStatusVo sumByStatus(ApplyNoteParams params){
        if(StringUtil.isEmpty(params.getStartDate()) && StringUtil.isEmpty(params.getEndDate())){
            params.setStartDate(LocalDateTime.now().format(fmt));
            params.setEndDate(LocalDateTime.now().format(fmt));
        }
        List<ApplyNoteSumReceiveVo> records = baseMapper.sumByStatus(params);
        List<SumInfoVo> sumInfoVos = new ArrayList<>();
        records.forEach(it -> {
            if(sumInfoVos.stream().filter((SumInfoVo s)->s.getStatus().equalsIgnoreCase(it.getStatus())).collect(Collectors.toList()).size() == 0){
                SumInfoVo sumInfo = new SumInfoVo();
                sumInfo.setStatus(it.getStatus());
                if(ObjectUtils.isEmpty(sumInfo.getMonthList())){
                    sumInfo.setMonthList(new ArrayList<>());
                }
                sumInfoVos.add(sumInfo);
            }
            SumInfoVo sumInfo = sumInfoVos.stream().filter((SumInfoVo s)->s.getStatus().equalsIgnoreCase(it.getStatus())).collect(Collectors.toList()).get(0);
            List<PropertyVo> list = new ArrayList<>();
            list.add(new PropertyVo<Integer>("数量",ObjectUtils.isEmpty(it.getCount())?0:it.getCount()));
            list.add(new PropertyVo<BigDecimal>("金额",ObjectUtils.isEmpty(it.getMoney())?BigDecimal.ZERO:it.getMoney()));
            sumInfo.getMonthList().add(new MonthSumVo(it.getMonth(),list));
        });
        return new ApplyNoteSumByStatusVo(fillList(sumInfoVos,params));
    }

    private List<SumInfoVo> fillList(List<SumInfoVo> sumInfoVos,ApplyNoteParams params){
        List<String> monthsList = getBetweenMonth(params);
        sumInfoVos.forEach(it -> {
            List<String> months = new ArrayList<>();
            it.getMonthList().forEach(im -> {
                months.add(im.getMonth());
            });
            List<String> allMonths = monthsList;
            allMonths.removeAll(months);
            allMonths.forEach(is -> {
                List<PropertyVo> list = new ArrayList<>();
                list.add(new PropertyVo<Integer>("数量",0));
                list.add(new PropertyVo<BigDecimal>("金额",BigDecimal.ZERO));
                it.getMonthList().add(new MonthSumVo(is,list));
            });
            it.setMonthList(it.getMonthList().stream().sorted(Comparator.comparing(MonthSumVo::getMonth)).collect(Collectors.toList()));
        });
        return sumInfoVos;
    }

    private List<String> getBetweenMonth(ApplyNoteParams params){
        LocalDate start = LocalDate.parse(params.getStartDate() + "-01");
        LocalDate end = LocalDate.parse(params.getEndDate() + "-01");
        List<String> monthList = new ArrayList<>();
        if(params.getStartDate().equalsIgnoreCase(params.getEndDate())){
            monthList.add(params.getStartDate());
            return monthList;
        }else{
            while(!start.isAfter(end)){
                monthList.add(start.format(fmt));
                start = start.plusMonths(1);
            }
            return monthList;
        }
    }

    @Override
    public IPage<ApplyNoteSumByApplyTimeVo> sumByApplyTime(ApplyNoteParams params){
        Page<ApplyNoteSumByApplyTimeVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if(StringUtils.isEmpty(params.getStartDate())){
            params.setStartDate(LocalDateTime.now().format(fmt)+"-01 00:00:00");
        }
        if(StringUtils.isEmpty(params.getEndDate())){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Cons.DATE_FORMAT);
            params.setEndDate(LocalDateTime.now().format(dtf)+" 23:59:59");
        }
        if(StringUtils.isNotEmpty(params.getStartDate()) && StringUtils.isNotEmpty(params.getEndDate())){
            params.setStartDate(params.getStartDate()+" 00:00:00");
            params.setEndDate(params.getEndDate()+" 23:59:59");
        }
        IPage<ApplyNoteSumByApplyTimeVo> result = baseMapper.sumByApplyTime(page, params);
        result.getRecords().forEach(it->{
            it.setAmountAllReceived((!ObjectUtils.isEmpty(it.getAmountReceived())&&!ObjectUtils.isEmpty(it.getAmountReceivable())&&it.getAmountReceived().equals(it.getAmountReceivable()))?"是":"否");
            it.setTestCompleteTime((it.getStatus().equals("passApply") || it.getStatus().equals("cancleApply"))?it.getTestCompleteTime():null);
        });
        return page;
    }

    @Override
    public void writeExcel(ApplyNoteSumByStatusVo sum){
        List<SumInfoVo> sumInfoVos = sum.getInfos();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = createExcel(sumInfoVos,xssfWorkbook);
        sumInfoVos.forEach(it->{
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum+1);
            row.createCell(0).setCellValue(convert(it.getStatus()));
            for(int i = 1; i < (it.getMonthList().size()*2)+1; i++){
                Object obj = it.getMonthList().get((i-1)/2).getPropertyList().get(i%2 == 0 ? 1 : 0).getValue();
                if(obj instanceof Integer){
                    row.createCell(i).setCellValue((Integer)obj);
                }
                if(obj instanceof BigDecimal){
                    row.createCell(i).setCellValue(((BigDecimal) obj).doubleValue());
                }
            }
        });
        try {
            xssfWorkbook.write(getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convert(String status){
         List<Dict> dicts = dictService.findByParentCode("applyStatus");
         Map<String,String> map = new HashMap<>();
         dicts.forEach(it->map.put(it.getCode(),it.getName()));
         return map.get(status);
    }

    private XSSFSheet createExcel(List<SumInfoVo> sumInfoVos, XSSFWorkbook xssfWorkbook){
        XSSFSheet sheet = xssfWorkbook.createSheet("sheet1");
        CellRangeAddress cra = new CellRangeAddress(0,0,0, CollectionUtils.isEmpty(sumInfoVos)?0:(sumInfoVos.get(0).getMonthList().size()*2));
        sheet.addMergedRegion(cra);
        Row titleRow = sheet.createRow(0);
        XSSFFont font1 = xssfWorkbook.createFont();
        font1.setFontHeight(16);
        font1.setBold(true);
        //抬头属性设置
        XSSFCellStyle style1 = setCellStyle(xssfWorkbook,font1);
        Cell cell = titleRow.createCell(0);
        cell.setCellValue("测试申请统计-按状态");
        cell.setCellStyle(style1);
        //excel列名属性设置
        XSSFFont font2 = xssfWorkbook.createFont();
        font2.setFontHeight(12);
        XSSFCellStyle style2 = xssfWorkbook.createCellStyle();
        style2.cloneStyleFrom(style1);
        style2.setFont(font2);
        CellRangeAddress cra1 = new CellRangeAddress(1,2,0, 0);
        sheet.addMergedRegion(cra1);
        Row statusRow = sheet.createRow(1);
        Cell statusCell = statusRow.createCell(0);
        statusCell.setCellValue("申请状态");
        statusCell.setCellStyle(style2);
        Row attributeRowForProperty = sheet.createRow(2);
        for(int i=1; i<(CollectionUtils.isEmpty(sumInfoVos)?0:sumInfoVos.get(0).getMonthList().size())+1;i++){
            CellRangeAddress craForMonth = new CellRangeAddress(1,1,2*i-1,2*i);
            sheet.addMergedRegion(craForMonth);
            Cell monthCell = statusRow.createCell(i*2-1);
            Cell propertyCell1 = attributeRowForProperty.createCell(2*i-1);
            Cell propertyCell2 = attributeRowForProperty.createCell(2*i);
            monthCell.setCellValue(sumInfoVos.get(0).getMonthList().get(i-1).getMonth());
            monthCell.setCellStyle(style2);
            propertyCell1.setCellValue("数量");
            propertyCell1.setCellStyle(style2);
            propertyCell2.setCellValue("金额");
            propertyCell2.setCellStyle(style2);
        }
        return sheet;
    }

    private OutputStream getOutputStream() {
        try {
            HttpServletResponse response = SystemUtil.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;success=true;filename=" + sumByStatusExcel);
            response.setContentType("application/ms-excel");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "创建文件失败！", e);
        }
    }


    private XSSFCellStyle setCellStyle(XSSFWorkbook xssfWorkbook,XSSFFont font){
        XSSFCellStyle style = xssfWorkbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    @Override
    public List<ApplyNoteListVo> findByLoginUser() {
        BackendUserDetail user = SystemUtil.getUser();
//        登陆用户存单位ID
        if (StringUtil.isNotEmpty(user)){
            List<ApplyNoteListVo> byLoginUser = baseMapper.findByLoginUser(sysEvaluationUserService.getByLoginUser().getId());
            return byLoginUser;
        }
        return null;
    }

    @Override
    public ApplyNoteDetailVo getInformationDetail(String id) {
        ApplyNoteDetailVo informationDetail = baseMapper.getInformationDetail(id);
        return informationDetail;
    }

    @Override
    public String typeCovert(String id) {
        String typeCovert = baseMapper.typeCovert(id);
        return typeCovert;
    }
}
