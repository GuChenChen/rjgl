package com.fykj.scaffold.cms.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.Advice;
import com.fykj.scaffold.cms.domain.params.AdviceParams;
import com.fykj.scaffold.cms.domain.vo.UserFieldValueVo;
import com.fykj.scaffold.cms.service.IUserFieldValueService;
import com.fykj.scaffold.cms.service.impl.AdviceServiceImpl;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.support.excel.ExcelUtil;
import com.fykj.scaffold.support.utils.ExcelExportUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Slf4j
@RestController
@RequestMapping("/admin/advice")
public class AdviceController extends BaseController<AdviceServiceImpl, Advice, AdviceParams> {

    @Autowired
    private IUserFieldValueService userFieldValueService;

    @PostMapping("/export")
    @ApiOperation("导出数据到Excel")
    public void export(@RequestBody IdTextVo idTextVo) {
        UserFieldValueVo result = userFieldValueService.getDataByAdviceId(idTextVo.getId());
        Map<String, Object> context = Maps.newHashMap();
        context.put("list", result);
        ExcelExportUtil.exportExcel(context, "advice.xlsx", SystemUtil.getResponse());
    }


    @GetMapping("/test")
    @ApiOperation("导出数据到Excel测试")
    public void testExport(@RequestParam String adviceId) {
        UserFieldValueVo result = userFieldValueService.getDataByAdviceId(adviceId);

        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        Resource resource = new ClassPathResource("/data/excelTemplate/test.xlsx");
        try {
            ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(null))
                    .withTemplate(resource.getInputStream()).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig horizontal = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
            excelWriter.fill(result.getDataList(), horizontal, writeSheet);
            List<List<String>> fields = result.getFieldList().stream()
                    .map(it -> it.stream().map(IdTextVo::getText).collect(Collectors.toList()))
                    .collect(Collectors.toList());
            excelWriter.write(fields, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("Fail to export excel:", e);
        }
    }
}
