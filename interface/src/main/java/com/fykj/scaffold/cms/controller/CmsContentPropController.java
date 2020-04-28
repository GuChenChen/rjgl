package com.fykj.scaffold.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.dto.PropValueDto;
import com.fykj.scaffold.cms.domain.dto.UserFieldValueDto;
import com.fykj.scaffold.cms.domain.entity.CmsContentProp;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentPropParams;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;
import com.fykj.scaffold.cms.domain.vo.PropVo;
import com.fykj.scaffold.cms.service.ICmsContentPropService;
import com.fykj.scaffold.cms.service.impl.CmsContentPropServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/admin/cms/contentProp")
public class CmsContentPropController  extends BaseController<CmsContentPropServiceImpl, CmsContentProp, CmsContentPropParams>{

    @Autowired
    private ICmsContentPropService baseService;

    @ApiOperation("根据栏目id获取扩展属性")
    @GetMapping(value = "getByCategoryId")
    public Result getByCategoryId(@RequestParam String categoryId, @RequestParam String contentId) {
        return new JsonResult<>(baseService.getByCategoryId(categoryId, contentId));
    }

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link CmsContentPropParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<PropVo>> page(@RequestBody(required = false) CmsContentPropParams params){
        if (params == null) {
            params = new CmsContentPropParams();
        }
        //查询列表数据
        Page<PropVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<PropVo> list = baseService.getListWithQuery(page,params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value = "保存公共属性")
    @PostMapping(value = "/savePubPropValue")
    public Result savePubPropValue(@RequestBody @Validated PropValueDto propValue) {
        if (baseService.savePubPropValue(propValue)) {
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }
}
