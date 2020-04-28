package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.FieldType;
import com.fykj.scaffold.cms.domain.params.FieldTypeParams;
import com.fykj.scaffold.cms.service.impl.FieldTypeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字段类型前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/admin/advice/fieldType")
public class FieldTypeController extends BaseController<FieldTypeServiceImpl, FieldType, FieldTypeParams> {

}
