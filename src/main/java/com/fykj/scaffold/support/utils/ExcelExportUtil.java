package com.fykj.scaffold.support.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出excel工具类
 * 适用于jxls模板导出
 *
 * @author zhangzhi
 */
@Slf4j
public class ExcelExportUtil {

    private ExcelExportUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 导出列表数据到Excel文件
     *
     * @param result       列表数据
     * @param templateName 模板名称
     */
    public static void export(List<?> result, String templateName) {
        Map<String, Object> context = new HashMap<>(2);
        context.put("list", result);
        exportExcel(context, templateName, SystemUtil.getResponse());
    }

    /**
     * 导出Excel文件
     *
     * @param context      Excel内容集合
     * @param templateName Excel模板名称
     * @param fileName     Excel文件名称
     * @param response     {@link HttpServletResponse}
     */
    public static void exportExcel(Map<String, Object> context, String templateName, String fileName, HttpServletResponse response) {
        Assert.notEmpty(context, "Context must not be null");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;success=true;filename=" + fileName);
        response.setContentType("application/ms-excel");

        Resource resource = new ClassPathResource("/data/excelTemplate/" + templateName);
        try (InputStream is = resource.getInputStream();
             OutputStream os = response.getOutputStream();
             BufferedOutputStream out = new BufferedOutputStream(os)) {

            XLSTransformer former = new XLSTransformer();
            Workbook workbook = former.transformXLS(is, context);
            workbook.write(out);
        } catch (Exception e) {
            log.error("Fail to export excel:", e);
        }
    }

    /**
     * 导出excel文件
     * 默认当前时间毫秒数做为Excel文件名称
     *
     * @param context      Excel内容集合
     * @param templatePath Excel模板路径
     * @param response     {@link HttpServletResponse}
     */
    public static void exportExcel(Map<String, Object> context, String templatePath, HttpServletResponse response) {
        String fileName = System.currentTimeMillis() + ".xlsx";
        exportExcel(context, templatePath, fileName, response);
    }

}
