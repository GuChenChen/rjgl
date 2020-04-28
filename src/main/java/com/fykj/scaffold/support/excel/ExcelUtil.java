package com.fykj.scaffold.support.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fykj.scaffold.support.utils.SystemUtil;
import exception.BusinessException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import result.ResultCode;
import utils.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Excel工具类（基于阿里巴巴EasyExcel）
 *
 * @author zhangzhi
 */
public class ExcelUtil {

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel 文件
     * @param clazz 实体类映射
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> clazz) {
        ExcelListener<T> excelListener = new ExcelListener<>();
        ExcelReader reader = getReader(excel, clazz, excelListener);
        reader.readAll();
        reader.finish();
        return excelListener.getDatas();
    }


    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel   文件
     * @param clazz   实体类映射，继承 BaseRowModel 类
     * @param sheetNo sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> clazz, int sheetNo) {
        return readExcel(excel, clazz, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param clazz       实体类映射
     * @param sheetNo     Index of sheet,0 base.
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> clazz, int sheetNo,
                                        int headLineNum) {
        ExcelListener<T> excelListener = new ExcelListener<>();
        ExcelReader reader = getReader(excel, clazz, excelListener);
        ReadSheet readSheet = EasyExcel.readSheet(sheetNo).headRowNumber(headLineNum).build();
        reader.read(readSheet);
        reader.finish();
        return excelListener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param list     数据 list
     * @param fileName 导出的文件名
     * @param clazz    映射实体类，Excel 模型
     */
    public static void writeExcel(List<?> list, String fileName, Class<?> clazz) {
        writeExcelWithSheets(list, fileName, clazz);
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param list     数据 list
     * @param fileName 导出的文件名
     * @param clazz    映射实体类，Excel 模型
     */
    public static void writeExcelWithSheets(List<?> list, String fileName, Class<?> clazz) {
        if (StringUtil.isEmpty(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
        ExcelWriter writer = EasyExcel.write(getOutputStream(fileName), clazz).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
        writer.write(list, writeSheet);
        writer.finish();
    }

    /**
     * 模板导出Excel
     *
     * @param list     数据 list
     * @param fileName 导出的文件名
     * @param template 模板路径
     */
    public static void fillExcel(List<?> list, String fileName, String template) {
        if (StringUtil.isEmpty(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
        ExcelWriter writer = EasyExcel.write(getOutputStream(fileName)).withTemplate(getTemplateStream(template)).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
        writer.fill(list, writeSheet);
        writer.finish();
    }

    /**
     * 模板导出Excel
     *
     * @param list 数据 list
     */
    public static void fillExcel(List<?> list, String template) {
        fillExcel(list, null, template);
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    public static OutputStream getOutputStream(String fileName) {
        //创建本地文件
        if (StringUtil.isEmpty(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
        String filePath = fileName + ExcelTypeEnum.XLSX.getValue();
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                if (!dbfFile.createNewFile()) {
                    throw new BusinessException(ResultCode.FAIL, "Fail to create file.");
                }
            }
            HttpServletResponse response = SystemUtil.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;success=true;filename=" + filePath);
            response.setContentType("application/ms-excel");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "创建文件失败！", e);
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel, Class clazz, ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        if (!isExcel(filename)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "不支持的文件格式。");
        }
        try {
            InputStream in = new BufferedInputStream(excel.getInputStream());
            return EasyExcel.read(in, clazz, excelListener).build();
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "读取文件失败", e);
        }
    }

    /**
     * 判断是否是Excel文件
     *
     * @param filename 文件名称
     * @return 是Excel-true/不是Excel-false
     */
    private static boolean isExcel(String filename) {
        if (StringUtil.isEmpty(filename)) {
            return false;
        }
        String lowerName = filename.toLowerCase();
        return lowerName.endsWith(ExcelTypeEnum.XLS.getValue())
                || lowerName.endsWith(ExcelTypeEnum.XLSX.getValue());
    }

    private static InputStream getTemplateStream(String template) {
        ClassPathResource resource = new ClassPathResource("/data/excelTemplate/" + template);

        try {
            return resource.getInputStream();
        } catch (IOException var3) {
            throw new BusinessException(ResultCode.FAIL, String.format("Excel模板[%s]读取失败", template));
        }
    }
}
