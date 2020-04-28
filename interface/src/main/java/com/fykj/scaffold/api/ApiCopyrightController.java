package com.fykj.scaffold.api;


import com.fykj.scaffold.security.business.domain.entity.Copyright;
import com.fykj.scaffold.security.business.service.ICopyrightService;
import exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.ResultCode;

/**
 * @author feihj.
 * @date: 2019/11/6 14:10
 */
@RestController
@RequestMapping("/api/copyright")
@Api(tags = "版权信息")
public class ApiCopyrightController {

    @Autowired
    private ICopyrightService copyrightService;


    @ApiOperation(value = "根据code 获取版权信息")
    @GetMapping(value = "/findDetailByCode")
    public JsonResult<Copyright> findDetailByCode(@RequestParam String code) {
        Copyright copyright = copyrightService.findDetailByCode(code);
        if(copyright == null){
            throw new BusinessException(ResultCode.FAIL, "该栏位下的内容已被放入回收站！");
        }else {
            return new JsonResult<>(copyright);
        }
    }


}
