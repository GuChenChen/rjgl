package com.fykj.scaffold.api;

import com.fykj.scaffold.security.business.domain.entity.SysOss;
import com.fykj.scaffold.security.business.service.ISysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import result.JsonResult;
import result.Result;
import result.ResultCode;


@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/oss")
public class ApiFileUploadController {

    @Autowired
    private ISysOssService ossService;

    @ApiOperation("上传文件")
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile file,
                         @RequestParam(name = "serverCode") String serverCode) {
        if (file == null || file.isEmpty()) {
            return new Result(ResultCode.BAD_REQUEST.code(), "请选择要上传的文件");
        }
        SysOss upload = ossService.upload(file, serverCode, false, null, null);
        return new JsonResult<>(upload);
    }

}
