package com.fykj.scaffold.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import constants.Mark;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import result.JsonResult;
import result.Result;
import result.ResultCode;
import utils.ClassUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 抽象Controller基础类 业务controller需继承此类
 *
 * @param <S> 对应业务服务类
 * @param <E> 对应业务实体类
 * @author wangf
 * @date 2019/2/12
 */
@SuppressWarnings("unchecked")
public abstract class BaseController<S extends BaseServiceImpl, E extends BaseEntity,Params extends BaseParams> {

    @Autowired
    protected S baseService;

    public static final Result OK = new Result();

    /**
     * 获取对应实体的类型
     *
     * @return T.class
     */
    protected Class<E> getType() {
        return ClassUtil.getGenericType(this.getClass(), 1);
    }

    @SysLogMethod("新增")
    @ApiOperation("保存方法")
    @PostMapping(value = "/save")
    public Result save(@RequestBody @Validated({BaseEntity.Add.class}) E entity) {

        boolean result = baseService.save(entity);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

//    @SysLogMethod("单个查询")
    @ApiOperation("根据id获取")
    @GetMapping
    public Result get(@RequestParam String id) {
        return new JsonResult<>((E) baseService.getById(id));
    }

    /**
     * 根据id更新
     *
     * @param entity
     * @return
     */
    @SysLogMethod("编辑")
    @ApiOperation("更新方法")
    @PostMapping(value = "/update")
    public Result update(@RequestBody @Validated({BaseEntity.Modify.class}) E entity) {
        if (baseService.updateById(entity)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }

    @SysLogMethod("删除")
    @ApiOperation("单个删除方法")
    @GetMapping(value = "/delete")
    public Result remove(@RequestParam String id) {
        boolean result = baseService.removeById(id);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @SysLogMethod("批量删除")
    @ApiOperation("批量删除方法")
    @GetMapping(value = "/removeByIds")
    public Result removeByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.removeByIds(idList);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

//    @SysLogMethod("查询全部")
    @ApiOperation("获取全部方法")
    @GetMapping(value = "/all")
    public List<E> all() {
        return baseService.list();
    }

    /**
     * 分页查询，当表字段庞大时，建议使用自定义vo，查询固定列
     *
     * @param params 查询参数
     * @return 分页结果
     */
    @ApiOperation("分页查询")
    @PostMapping(value = "/pages")
    public JsonResult<IPage<E>> list(@RequestBody(required = false) Params params) {
        IPage<E> result = baseService.page(params);
        return new JsonResult<>(result);
    }

}
