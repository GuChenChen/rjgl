package com.fykj.scaffold.api;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  测试申请单 前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/api/applyNoteFront")
@Api(tags = "申请单接口")
public class ApiApplyNoteController extends BaseController<ApplyNoteServiceImpl, ApplyNote,BaseParams> {





}
