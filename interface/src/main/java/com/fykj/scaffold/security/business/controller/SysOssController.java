package com.fykj.scaffold.security.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.security.business.domain.dto.SysOssDto;
import com.fykj.scaffold.security.business.domain.entity.SysOss;
import com.fykj.scaffold.security.business.domain.params.OssParams;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.security.business.service.impl.SysOssServiceImpl;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * 文件上传接口
 *
 * @author zhangzhi
 */
@RestController
@RequestMapping("/admin/oss")
@Api(description = "文件上传接口")
public class SysOssController extends BaseController<SysOssServiceImpl, SysOss, OssParams> {

    @Autowired
    private IDictService dictService;

    @ApiOperation("上传文件")
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile file,
                         @RequestParam(name = "serverCode") String serverCode,
                         @RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "labels", required = false) String[] labels,
                         @RequestParam(name = "media") boolean media) {
        if (file == null || file.isEmpty()) {
            return new Result(ResultCode.BAD_REQUEST.code(), "请选择要上传的文件");
        }
        SysOss upload = baseService.upload(file, serverCode, media, name, labels);
        return new JsonResult<>(upload);

    }

    @ApiOperation("文件分类下拉列表")
    @GetMapping(value = "/typeList")
    public JsonResult<List<IdTextVo>> typeList() {
        return new JsonResult<>(dictService.idTextVoList(Cons.FILE_TYPE));
    }

    @ApiOperation("文件存储方式下拉列表")
    @GetMapping(value = "/serverList")
    public JsonResult<List<IdTextVo>> serverList() {
        return new JsonResult<>(dictService.idTextVoList(Cons.SERVER_CODE));
    }

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link CmsContentParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<SysOssDto>> page(@RequestBody(required = false) OssParams params) {
        if (params == null) {
            params = new OssParams();
        }
        //查询列表数据
        Page<SysOssDto> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<SysOssDto> list = baseService.getListWithQuery(page,params);
        return new JsonResult<>(list);
    }

}
