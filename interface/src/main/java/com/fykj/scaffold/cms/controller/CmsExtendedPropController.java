package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.CmsExtendedProp;
import com.fykj.scaffold.cms.domain.params.CmsExtendedPropParams;
import com.fykj.scaffold.cms.service.impl.CmsExtendedPropServiceImpl;
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
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/admin/cms/extended")
public class CmsExtendedPropController extends BaseController<CmsExtendedPropServiceImpl, CmsExtendedProp,CmsExtendedPropParams> {

    @GetMapping("getExtendedPropList")
    @ApiOperation("获取扩展属性列表")
    public JsonResult<List<CmsExtendedProp>> getExtendedPropList(){
        List<CmsExtendedProp> categories = baseService.getExtendedPropList();
        return new JsonResult<>(categories);
    }
}
