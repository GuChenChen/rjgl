package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.security.business.domain.entity.Copyright;
import com.fykj.scaffold.security.business.domain.params.CopyrightParams;
import com.fykj.scaffold.security.business.service.impl.CopyrightServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feihj.
 * @date: 2019/11/6 14:10
 */
@RestController
@RequestMapping("/admin/copyright")
@Api(tags = "版权信息")
public class CopyrightController extends BaseController<CopyrightServiceImpl, Copyright, CopyrightParams> {



}
