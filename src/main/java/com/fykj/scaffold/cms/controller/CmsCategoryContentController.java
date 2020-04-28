package com.fykj.scaffold.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryContent;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.vo.CategoryContentVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;
import com.fykj.scaffold.cms.service.impl.CmsCategoryContentServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import constants.Mark;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/admin/cms/categoryContent")
@Api(tags = "栏目内容管理接口")
public class CmsCategoryContentController extends BaseController<CmsCategoryContentServiceImpl, CmsCategoryContent,CmsContentParams> {

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link CmsContentParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<CmsContentVo>> page(@RequestBody(required = false) CmsContentParams params) {
        if (params == null) {
            params = new CmsContentParams();
        }
        //查询列表数据
        Page<CmsContentVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<CmsContentVo> list = baseService.getListWithQuery(page,params);
        return new JsonResult<>(list);
    }

    /**
     * 保存内容管理
     *
     * @param categoryContentVo categoryContentVo
     */
    @SysLogMethod("保存栏目内容管理")
    @ApiOperation(value = "保存内容管理")
    @PostMapping(value = "/saveContent")
    public Result saveContent(@RequestBody @Validated CategoryContentVo categoryContentVo) {
        if (baseService.saveContent(categoryContentVo)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 更新内容管理
     *
     * @param categoryContentVo categoryContentVo
     */
    @SysLogMethod("更新栏目内容管理")
    @ApiOperation(value = "更新内容管理")
    @PostMapping(value = "/updateContent")
    public Result updateContent(@RequestBody @Validated CategoryContentVo categoryContentVo) {
        if (baseService.updateContent(categoryContentVo)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 根据id获取
     *
     * @param id id
     */
    @ApiOperation(value = "根据id获取")
    @GetMapping(value = "/getCategoryContentById")
    public Result getCategoryContentById(@RequestParam String id) {
        return new JsonResult<>(baseService.getCategoryContentById(id));
    }

    /**
     * 设置置顶/取消置顶
     *
     * @param id id
     */
    @SysLogMethod("栏目内容设置置顶")
    @ApiOperation(value = "设置置顶/取消置顶")
    @GetMapping(value = "/setStick")
    public Result setStick(@RequestParam String id) {
        if (baseService.setStick(id)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 设置热门/取消热门
     *
     * @param id id
     */
    @ApiOperation(value = "设置热门/取消热门")
    @SysLogMethod("栏目内容设置热门")
    @GetMapping(value = "/setHot")
    public Result setHot(@RequestParam String id) {
        if (baseService.setHot(id)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("批量加入回收站,ids用,拼接")
    @GetMapping(value = "/recyclingByIds")
    public Result recyclingByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.recyclingByIds(idList,true);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("恢复回收站中的数据,ids用,拼接")
    @GetMapping(value = "/recoveryByIds")
    public Result recoveryByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.recyclingByIds(idList,false);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }
}
