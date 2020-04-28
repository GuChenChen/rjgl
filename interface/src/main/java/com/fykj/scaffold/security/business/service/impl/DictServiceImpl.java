package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.domain.vo.DictVo;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.security.business.mapper.DictMapper;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.DictCacheClient;
import com.fykj.scaffold.support.utils.RedisService;
import constants.Mark;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Service
@Slf4j
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DictCacheClient dictCacheClient;

    @Override
    public List<Dict> list() {
        return list(new QueryWrapper<Dict>().orderByAsc("sequence"));
    }

    @Override
    public Dict getByCode(String code) {
        Dict dict = lambdaQuery().eq(Dict::getCode, code).one();
        if (dict == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "未找到指定code的数据字典:" + code);
        }
        return dict;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dict> findByParentCode(String code) {
        Dict parent = getByCode(code);
        return lambdaQuery().eq(Dict::getParentId, parent.getId())
                .orderByAsc(Dict::getSequence).list();
    }

    @Override
    public void refreshDictCache() {
        dictCacheClient.deleteAll();
        Map<String, String> dictMap = list().stream().collect(Collectors.toMap(Dict::getCode, Dict::getName));
        redisService.hmSetBatch(DictCacheClient.DICT_KEY, dictMap, 0);
    }

    @Override
    public String getNameByCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return "";
        }

        try {
            Dict dict = getByCode(code);
            return dict.getName();

        } catch (Exception e) {
            log.error("not found of dict code:{}。", code, e);
        }
        return code;
    }

    @Override
    public List<IdTextVo> idTextVoList(String type) {
        return findByParentCode(type).stream()
                .filter(Dict::getStatus)
                .map(it -> new IdTextVo(it.getCode(), it.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<IdTextVo> findTopDict() {
        List<Dict> dicts = lambdaQuery().isNull(Dict::getParentId).list();
        return dicts.stream().map(it -> new IdTextVo(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkCodeExists(String id, String code) {
        return lambdaQuery().eq(Dict::getCode, code)
                .ne(StringUtil.isNotEmpty(id), Dict::getId, id).count() > 0;
    }

    @Override
    public String findFileTypeByValue(String value) {
        try {
            String parentId = getByCode(Cons.FILE_TYPE).getId();
            Dict dict = lambdaQuery().eq(Dict::getParentId, parentId)
                    .like(Dict::getValue, ',' + value + ',').one();
            return dict != null ? dict.getCode() : Cons.OTHER_TYPES;
        } catch (MyBatisSystemException e) {
            log.error("查询文件类型{}失败", value, e);
            throw new BusinessException(ResultCode.ERROR, "查询文件类型{+" + value + "+}失败");
        }
    }

    @Override
    public List<String> getOssExtList() {
        String parentId = getByCode(Cons.FILE_TYPE).getId();
        return lambdaQuery().eq(Dict::getParentId, parentId)
                .eq(Dict::getStatus, true).list()
                .stream()
                .map(Dict::getValue)
                .map(it -> it.substring(1, it.length() - 1))
                .flatMap(it -> Arrays.stream(it.split(Mark.COMMA)))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> findAllDict() {
        Map<String, String> dicts = redisService.hmGetEntries(DictCacheClient.DICT_KEY);
        if (dicts.size() == 0) {
            dicts = list().stream().collect(Collectors.toMap(Dict::getCode, Dict::getName));
            redisService.hmSetBatch(DictCacheClient.DICT_KEY, dicts, 0);
        }
        return dicts;
    }

    @Override
    public List<DictVo> findAll() {
        List<Dict> subList = lambdaQuery().isNull(Dict::getParentId).list();
        List<DictVo> subVos = new ArrayList<>();
        subList.forEach(it -> {
            DictVo vo = new DictVo();
            BeanUtils.copyProperties(it, vo);
            subVos.add(vo);
        });
        subVos.forEach(it -> {
            it.setList(findByParentCode(it.getCode()));
        });
        return subVos;
    }

    @Override
    public boolean save(Dict entity) {
        if (super.save(entity)) {
            dictCacheClient.addDict(entity.getCode(), entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Dict entity) {
        if (super.updateById(entity)) {
            String code = entity.getCode();
            dictCacheClient.deleteDict(code);
            dictCacheClient.addDict(code, entity.getName());
            return true;
        }
        return false;
    }
}
