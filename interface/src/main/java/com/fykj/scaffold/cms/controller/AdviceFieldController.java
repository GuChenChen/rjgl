package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.AdviceField;
import com.fykj.scaffold.cms.domain.params.AdviceFieldParams;
import com.fykj.scaffold.cms.service.impl.AdviceFieldServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * <p>
 * 咨询建议字段前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/admin/advice/field")
public class AdviceFieldController extends BaseController<AdviceFieldServiceImpl, AdviceField, AdviceFieldParams> {

    @GetMapping("findByAdviceId")
    @ApiModelProperty("获取咨询建议字段列表")
    public JsonResult<List<AdviceField>> findByAdviceId(@RequestParam String adviceId) {
        return new JsonResult<>(baseService.findByAdviceId(adviceId));
    }
}
