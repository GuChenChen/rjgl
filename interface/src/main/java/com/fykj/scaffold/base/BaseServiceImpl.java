package com.fykj.scaffold.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.service.IRoleService;
import com.fykj.scaffold.support.utils.SystemUtil;
import com.fykj.scaffold.support.wrapper.QueryWrapperBuilder;
import exception.BusinessException;
import org.springframework.util.CollectionUtils;
import result.ResultCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 自定义service，用于重写mybatis的service
 *
 * @author wangf
 * @date 2019/2/22
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> {

    /**
     * 获取当前用户
     *
     * @return 用户信息
     */
    public BackendUserDetail getUser() {
        return SystemUtil.getUser();
    }

    /**
     * 修改默认获取方法，如果入参id不存在，抛出异常
     *
     * @param id 对象主键
     * @return 对应对象
     */
    @Override
    public T getById(Serializable id) {
        T t = baseMapper.selectById(id);
        if (t == null) {
            throw new BusinessException(ResultCode.FAIL, String.format("%s[%s]对象不存在。", currentModelClass().getSimpleName(), id));
        }
        return t;
    }

    /**
     * 根据查询条件分页查询
     *
     * @param params 查询条件封装
     * @return 分页列表
     */
    public IPage<T> page(BaseParams params) {
        if (params == null) {
            params = new BaseParams();
        }
        //查询列表数据
        QueryWrapper<T> queryWrapper = QueryWrapperBuilder.build(params);
        return page(params.getPage(), queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (idList.stream().anyMatch(it -> !deletable(it, idList.toArray()))) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "对象已被使用，请删除引用后重试");
        }
        return super.removeByIds(idList);
    }

    @Override
    public boolean removeById(Serializable id) {
        if (!deletable(id)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "对象已被使用，请删除引用后重试");
        }
        return super.removeById(id);
    }

    protected boolean deletable(Serializable id, Serializable... ids) {
        if (!(getInstance() instanceof BaseTreeEntity)) {
            return true;
        }

        List<T> children = findByParent(id);
        if (CollectionUtils.isEmpty(children)) {
            return true;
        }

        List<Serializable> idList = idList(ids);
        if (children.stream().map(BaseEntity::getId)
                .anyMatch(it -> !idList.contains(it))) {
            return false;
        }

        return true;
    }

    protected List<Serializable> idList(Serializable... ids) {
        List<Serializable> idList;
        if (ids == null) {
            idList = Collections.emptyList();
        } else {
            idList = Arrays.asList(ids);
        }
        return idList;
    }

    private T getInstance() {
        try {
            return currentModelClass().newInstance();
        } catch (Exception e) {
            throw new BusinessException(ResultCode.ERROR, "error of current model class", e);
        }
    }

    public List<T> findByParent(Serializable parentId) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).eq("is_deleted", false);
        return list(queryWrapper);
    }

    protected Role currentRole(){
        BackendUserDetail user = getUser();
        if (user == null) {
            throw new BusinessException(ResultCode.FAIL, "请登录先");
        }

        IRoleService roleService = SystemUtil.getBean(IRoleService.class);
        return roleService.getRoleByCode(user.getRoleCode());
    }

}
