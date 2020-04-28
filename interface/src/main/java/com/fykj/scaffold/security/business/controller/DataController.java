package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.service.IFileService;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import com.fykj.scaffold.support.utils.ContentTypeUtil;
import com.fykj.scaffold.support.utils.ImageUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件处理控制器
 *
 * @author zhangzhi
 */
@RestController
@RequestMapping("/vpath")
@Api(tags = "文件处理")
public class DataController {

    @Autowired
    private IOssConfigService ossConfigService;

    @Autowired
    private IFileService fileService;

    @ApiOperation("文件处理")
    @GetMapping("/data/**")
    public void data(
            @ApiParam(name = "图片宽度，非等比例压缩时必填")
            @RequestParam(required = false, defaultValue = "0") int w,
            @ApiParam(name = "图片高度，非等比例压缩时必填")
            @RequestParam(required = false, defaultValue = "0") int h,
            @ApiParam(name = "是否等比例压缩，默认true")
            @RequestParam(required = false, defaultValue = "true") boolean fix) {

        String filePath = getFilePath();
        if (w != 0 || h != 0) {
            filePath = fileService.getFilePath(filePath, w, h, fix);
        }
        write(new File(filePath));
    }

    private void write(File file) {
        HttpServletResponse response = SystemUtil.getResponse();
        String fileName = file.getName();
        String ext = ImageUtil.getExt(fileName);
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            int length = in.available();
            byte[] data = new byte[length];
            response.setContentLength(length);
            response.setContentType(ContentTypeUtil.getContentType(ext));
            in.read(data);
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "读取文件失败", e);
        }
    }

    private String getFilePath() {
        HttpServletRequest request = SystemUtil.getRequest();
        final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

        OssConfig conf = ossConfigService.getConfig();
        return conf.getStorageLocation() + File.separator + arguments;
    }

}
