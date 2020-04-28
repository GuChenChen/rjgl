package com.fykj.scaffold.evaluation.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.FollowUpRecord;
import com.fykj.scaffold.evaluation.domain.params.FollowUpRecordParams;
import com.fykj.scaffold.evaluation.service.impl.FollowUpRecordServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  跟进记录前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/followUpRecord")
public class FollowUpRecordController extends BaseController<FollowUpRecordServiceImpl, FollowUpRecord, FollowUpRecordParams> {

}
