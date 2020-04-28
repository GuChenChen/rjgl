package com.fykj.scaffold.api;


import com.fykj.scaffold.evaluation.service.IReportPageViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  模块使用记录前端控制器
 *  未登录获取IP
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */

@RestController
@RequestMapping("/api/reportPageView")
@Slf4j
@Api(tags = "模块使用记录管理接口")
public class ApiReportPageViewController {

    @Autowired
    private IReportPageViewService reportPageViewService;

    @ApiOperation("根据code获取前端各模块浏览量")
    @PostMapping(value = "/saveCode")
    public void saveCode(@RequestBody String code){
        reportPageViewService.saveReportPageByCode(code);
    }
}

