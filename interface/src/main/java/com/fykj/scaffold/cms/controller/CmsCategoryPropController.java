package com.fykj.scaffold.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryProp;
import com.fykj.scaffold.cms.domain.params.CmsCategoryPropParams;
import com.fykj.scaffold.cms.service.impl.CmsCategoryPropServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/admin/cms/categoryProp")
@Api(tags = "栏目增加属性管理接口")
public class CmsCategoryPropController extends BaseController<CmsCategoryPropServiceImpl, CmsCategoryProp,CmsCategoryPropParams> {

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link CmsCategoryPropParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<CmsCategoryProp>> page(@RequestBody(required = false) CmsCategoryPropParams params) {
        if (params == null) {
            params = new CmsCategoryPropParams();
        }
        //查询列表数据
        Page<CmsCategoryProp> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<CmsCategoryProp> list = baseService.getListWithQuery(page,params);
        return new JsonResult<>(list);
    }

    @SysLogMethod("新增")
    @ApiOperation("保存方法")
    @PostMapping(value = "/save")
    @Override
    public Result save(@RequestBody @Validated({BaseEntity.Add.class}) CmsCategoryProp entity) {
        if (baseService.checkExistCategoryIdAndPropId(entity.getCategoryId(),entity.getPropId())) {
            return new Result(ResultCode.FAIL.code(),"请勿重复添加");
        }
        boolean result = baseService.save(entity);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }
}
