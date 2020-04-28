package com.fykj.scaffold.api;


import com.fykj.scaffold.cms.domain.entity.CmsCategory;
import com.fykj.scaffold.cms.service.ICmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * @author feihj.
 * @date: 2019/11/21 9:55
 */
@RestController
@RequestMapping("/api/category")
@Api(tags = "客户端栏目管理接口")
public class ApiCmsCategoryController  {
    
    @Autowired
    private ICmsCategoryService categoryService;

    
    @ApiOperation("获取第一级栏目")
    @GetMapping("findParentAll")
    public JsonResult<List<CmsCategory>> findParentAll(){
        List<CmsCategory> categories = categoryService.findParentAll();
        return new JsonResult<>(categories);
    }

    @ApiOperation("根据第一级id 获取第二级栏目 / 首页获取服务分类/ 首页获取专业优势")
    @GetMapping("findChildAllByParentId")
    public JsonResult<List<CmsCategory>> findChildAllByParentId(@RequestParam String patentId){
        List<CmsCategory> categories = categoryService.findChildAllByParentId(patentId);
        return new JsonResult<>(categories);
    }

    @ApiOperation("获取 解决方案/客户案例")
    @GetMapping("findCustomerCase")
    public JsonResult<List<CmsCategory>> findCustomerCase(@RequestParam String patentId){
        List<CmsCategory> categories = categoryService.findCustomerCase(patentId);
        return new JsonResult<>(categories);
    }

    @ApiOperation("首页获取客户案例")
    @GetMapping("findCaseList")
    public JsonResult<List<CmsCategory>> findCaseList(@RequestParam String patentId){
        List<CmsCategory> categories = categoryService.findCaseList(patentId);
        return new JsonResult<>(categories);
    }


}
