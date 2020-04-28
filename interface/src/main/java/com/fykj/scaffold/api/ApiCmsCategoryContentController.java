package com.fykj.scaffold.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.cms.domain.dto.ContentCategoryPropValueDto;
import com.fykj.scaffold.cms.domain.params.ApiCmsContentParams;
import com.fykj.scaffold.cms.domain.vo.AboutUsVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentClientsVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;
import com.fykj.scaffold.cms.service.ICmsCategoryContentService;
import com.fykj.scaffold.cms.service.ICmsContentService;
import exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.ResultCode;

/**
 * @author feihj.
 * @date: 2019/11/21 9:46
 */
@RestController
@RequestMapping("/api/categoryContent")
@Api(tags = "客户端内容接口")
public class ApiCmsCategoryContentController {

    @Autowired
    private ICmsCategoryContentService categoryContentService;
    @Autowired
    private ICmsContentService contentService;


    @ApiOperation("客户端分页列表 - 软件测试/新闻咨询/合作伙伴")
    @PostMapping(value = "/findByPage")
    public JsonResult<IPage<CmsContentVo>> findByPage(@RequestBody(required = false) ApiCmsContentParams params) {
        if (params == null) {
            params = new ApiCmsContentParams();
        }
        //查询列表数据
        Page<CmsContentVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<CmsContentVo> list = categoryContentService.findByPage(page,params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value = "获取 荣誉资质")
    @GetMapping(value = "/findByHonor")
    public JsonResult<CmsContentClientsVo> findByHonor(@RequestParam String categoryId) {
        CmsContentClientsVo dto = categoryContentService.findByHonor(categoryId);
        if(dto == null){
            throw new BusinessException(ResultCode.FAIL, "该栏位下的内容已被放入回收站！");
        }else {
            return new JsonResult<>(dto);
        }
    }

    @ApiOperation(value = "获取 关于我们")
    @GetMapping(value = "/findByAboutUs")
    public JsonResult<AboutUsVo> findByAboutUs(@RequestParam String categoryId) {
        AboutUsVo dto = categoryContentService.findByAboutUs(categoryId);
        return new JsonResult<>(dto);
    }

    @ApiOperation(value = "客户端详情")
    @GetMapping(value = "/findDetailById")
    public JsonResult<ContentCategoryPropValueDto> findDetailById(@RequestParam String categoryId, @RequestParam String id) {
        ContentCategoryPropValueDto dto = contentService.findDetailById(categoryId,id);
        if(dto == null){
            throw new BusinessException(ResultCode.FAIL, "该栏位下的内容已被放入回收站！");
        }else {
            return new JsonResult<>(dto);
        }
    }
}
