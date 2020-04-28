package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.CmsLabel;
import com.fykj.scaffold.cms.domain.params.CmsLabelParams;
import com.fykj.scaffold.cms.service.impl.CmsLabelServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-11-04
 */
@RestController
@RequestMapping("admin/cms/label")
public class CmsLabelController extends BaseController<CmsLabelServiceImpl, CmsLabel,CmsLabelParams> {

    @GetMapping("getLabelList")
    @ApiOperation("获取标签列表")
    public JsonResult<List<CmsLabel>> getLabelList(){
        List<CmsLabel> categories = baseService.getLabelList();
        return new JsonResult<>(categories);
    }
}
