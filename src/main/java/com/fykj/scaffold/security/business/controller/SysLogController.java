package com.fykj.scaffold.security.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.params.SysEvaluationUserParams;
import com.fykj.scaffold.evaluation.domain.vo.TestAccountListVo;
import com.fykj.scaffold.security.business.domain.dto.SysLogDto;
import com.fykj.scaffold.security.business.domain.entity.SysLog;
import com.fykj.scaffold.security.business.domain.params.SysLogParams;
import com.fykj.scaffold.security.business.service.ISysLogService;
import com.fykj.scaffold.security.business.service.impl.SysLogServiceImpl;
import com.fykj.scaffold.support.utils.SystemUtil;
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
 * 系统日志 前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-10-18
 */
@RestController
@Api(tags = "系统日志")
@RequestMapping("/admin/sysLog")
public class SysLogController extends BaseController<SysLogServiceImpl,SysLog,SysLogParams> {

    @ApiOperation("分页查询列表")
    @PostMapping(value = "/pageByUserName")
    public JsonResult<IPage<SysLogDto>> pageByUserName(@RequestBody(required = false) SysLogParams params) {
        if (params == null) {
            params = new SysLogParams();
        }
        //查询列表数据
        IPage<SysLogDto> list = baseService.pageByUserName(params);
        return new JsonResult<>(list);
    }
}
