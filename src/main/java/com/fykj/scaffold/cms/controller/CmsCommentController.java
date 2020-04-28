package com.fykj.scaffold.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.CmsComment;
import com.fykj.scaffold.cms.domain.params.CmsCommentParams;
import com.fykj.scaffold.cms.domain.vo.CmsCommentVo;
import com.fykj.scaffold.cms.service.impl.CmsCommentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuew
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/admin/cms/comment")
public class CmsCommentController extends BaseController<CmsCommentServiceImpl, CmsComment, CmsCommentParams> {

    @ApiOperation(value = "保存内容附件管理")
    @PostMapping(value = "/reply")
    public Result reply(String id, String content) {
        if (baseService.reply(id, content)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link CmsCommentParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<CmsCommentVo>> page(@RequestBody(required = false) CmsCommentParams params) {
        if (params == null) {
            params = new CmsCommentParams();
        }
        //查询列表数据
        Page<CmsCommentVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<CmsCommentVo> list = baseService.getListWithQuery(page, params);
        return new JsonResult<>(list);
    }
}
