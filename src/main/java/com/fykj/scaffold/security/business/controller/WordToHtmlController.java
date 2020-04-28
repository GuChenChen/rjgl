package com.fykj.scaffold.security.business.controller;

import com.fykj.scaffold.security.business.service.impl.WordToHtmlServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import result.JsonResult;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/editor")
@Api(tags="富文本框")
public class WordToHtmlController {

    @Autowired
    private WordToHtmlServiceImpl service;

    @ApiOperation("Word转Html")
    @PostMapping(value="/wordToHtml")
    public JsonResult<String> wordToHtml(MultipartFile file, HttpServletRequest request) throws Exception {
        String filePass=service.writeFileToTargetPath(file);
        return new JsonResult<>(service.getWordToTransfer(filePass,file));
    }
}
