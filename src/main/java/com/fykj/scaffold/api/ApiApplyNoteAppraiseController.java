package com.fykj.scaffold.api;


import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAppraiseGetParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAppraiseParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAppraiseVo;
import com.fykj.scaffold.evaluation.service.IApplyNoteAppraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

/**
 * <p>
 *  测试申请单评价前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/api/applyNoteAppraise")
@Api(tags = "测试申请单评价管理接口")
public class ApiApplyNoteAppraiseController {

    @Autowired
    private IApplyNoteAppraiseService baseService;

    @ApiOperation("提交评价")
    @PostMapping(value = "/commitAppraise")
    public JsonResult commitAppraise(@RequestBody(required = false) ApplyNoteAppraiseParams params) {
        if (params == null) {
            params = new ApplyNoteAppraiseParams();
        }
        //插入数据
        baseService.insert(params);
        return new JsonResult(0,"评论成功");
    }

    @ApiOperation("获取评价")
    @PostMapping(value = "/getAppraise")
    public JsonResult<ApplyNoteAppraiseVo> getAppraise(@RequestBody(required = false) ApplyNoteAppraiseGetParams params) {
        if (params == null) {
            params = new ApplyNoteAppraiseGetParams();
        }
        //获取数据
        ApplyNoteAppraiseVo applyNoteAppraiseVo = baseService.findByApplyNoteId(params.getApplyNoteId());
        return new JsonResult<>(applyNoteAppraiseVo);
    }
}
