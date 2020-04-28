package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.XssTagAttributeProtocol;
import com.fykj.scaffold.security.business.domain.params.XssTagParams;
import com.fykj.scaffold.security.business.mapper.XssTagAttributeProtocolMapper;
import com.fykj.scaffold.security.business.service.IXssTagAttributeProtocolService;
import com.fykj.scaffold.support.utils.XssFilterUtil;
import com.fykj.scaffold.support.wrapper.QueryWrapperBuilder;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoc
 * @since 2019-10-30
 */
@Service
public class XssTagAttributeProtocolServiceImpl extends BaseServiceImpl<XssTagAttributeProtocolMapper, XssTagAttributeProtocol> implements IXssTagAttributeProtocolService {

    private String keyTemp;

    private Map<String,List<String>> valueTemp;

    private String innerKeyTemp;

    private List<String> innerValueTemp;

    private Map<String,String> valueMapTemp;

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        super.removeByIds(idList);
        XssFilterUtil.init();
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        XssFilterUtil.init();
        return true;
    }

    @Override
    protected boolean deletable(Serializable id, Serializable... ids) {
        super.deletable(id, ids);
        XssFilterUtil.init();
        return true;
    }

    @Override
    public IPage<XssTagAttributeProtocol> findByPage(XssTagParams params) {
        QueryWrapper<XssTagAttributeProtocol> queryWrapper = QueryWrapperBuilder.build(params);
        return page(params.getPage(), queryWrapper);
    }

    @Override
    public void updateInfo(XssTagAttributeProtocol info){
        if(StringUtil.isEmpty(info.getId())){
            throw new BusinessException(ResultCode.NOT_VALID,"id不能为空!");
        }
        checkBeforeSave(info);
        updateById(info);
        XssFilterUtil.init();
    }

    @Override
    public void saveInfo(XssTagAttributeProtocol info){
        checkBeforeSave(info);
        save(info);
        XssFilterUtil.init();
    }

    @Override
    public String[] getTags(){
        return lambdaQuery().groupBy(XssTagAttributeProtocol::getTag)
                .eq(XssTagAttributeProtocol::getDeleted,false)
                .eq(XssTagAttributeProtocol::getStatus,true)
                .notIn(XssTagAttributeProtocol::getTag,"")
                .in(XssTagAttributeProtocol::getAttributeKey,"")
                .in(XssTagAttributeProtocol::getAttributeValue,"")
                .in(XssTagAttributeProtocol::getProtocol,"")
                .list()
                .stream()
                .map(XssTagAttributeProtocol::getTag)
                .toArray(String[]::new);
    }

    /**
     * @return Map<Tag,List<AttributeKey>>
     */
    @Override
    public Map<String,List<String>> getTagAndAttrKey(){
        innerKeyTemp = "";
        innerValueTemp = new ArrayList<>();
        return lambdaQuery().groupBy(XssTagAttributeProtocol::getTag,XssTagAttributeProtocol::getAttributeKey)
                .eq(XssTagAttributeProtocol::getDeleted,false)
                .eq(XssTagAttributeProtocol::getStatus,true)
                .notIn(XssTagAttributeProtocol::getTag,"")
                .notIn(XssTagAttributeProtocol::getAttributeKey,"")
                .in(XssTagAttributeProtocol::getAttributeValue,"")
                .in(XssTagAttributeProtocol::getProtocol,"")
                .list()
                .stream()
                .collect(Collectors.toMap(XssTagAttributeProtocol::getTag, a -> {
                           if (!innerKeyTemp.equals(a.getTag())) {
                                innerKeyTemp = a.getTag();
                                innerValueTemp = new ArrayList<>();
                            }
                            innerValueTemp.addAll(Arrays.asList(a.getAttributeKey().split(",")));
                            return innerValueTemp;
                        },
                        (k1, k2) -> k1));
    }

    /**
     * @return Map<Tag, Map<AttributeKey,AttributeValue>>
     */
    @Override
    public Map<String, Map<String,String>> getTagAndAttrKeyVal(){
        keyTemp = "";
        valueTemp = new HashMap<>();
        innerKeyTemp = "";
        innerValueTemp = new ArrayList<>();
        return lambdaQuery().groupBy(XssTagAttributeProtocol::getTag,XssTagAttributeProtocol::getAttributeKey,
                XssTagAttributeProtocol::getAttributeValue)
                .eq(XssTagAttributeProtocol::getDeleted,false)
                .eq(XssTagAttributeProtocol::getStatus,true)
                .notIn(XssTagAttributeProtocol::getTag,"")
                .notIn(XssTagAttributeProtocol::getAttributeKey,"")
                .notIn(XssTagAttributeProtocol::getAttributeValue,"")
                .in(XssTagAttributeProtocol::getProtocol,"")
                .list().stream().collect(Collectors.toMap(XssTagAttributeProtocol::getTag, a -> {
                            if (!keyTemp.equals(a.getTag())) {
                                keyTemp = a.getTag();
                                valueMapTemp = new HashMap<>();
                            }
                            valueMapTemp.put(a.getAttributeKey(), a.getAttributeValue());
                            return valueMapTemp;
                        },
                        (key1, key2) -> key1));
    }

    /**
     * @return Map<Tag,Map<AttributeKey, List<protocol>>>
     */
    @Override
    public Map<String,Map<String, List<String>>> getTagAndKeyAndProtocol(){
        keyTemp = "";
        valueTemp = new HashMap<>();
        innerKeyTemp = "";
        innerValueTemp = new ArrayList<>();
        return lambdaQuery().groupBy(XssTagAttributeProtocol::getTag,XssTagAttributeProtocol::getAttributeKey,
                XssTagAttributeProtocol::getProtocol)
                .eq(XssTagAttributeProtocol::getStatus,true)
                .notIn(XssTagAttributeProtocol::getTag,"")
                .notIn(XssTagAttributeProtocol::getAttributeKey,"")
                .in(XssTagAttributeProtocol::getAttributeValue,"")
                .notIn(XssTagAttributeProtocol::getProtocol,"")
                .eq(XssTagAttributeProtocol::getDeleted,false)
                .list().stream().collect(Collectors.toMap(XssTagAttributeProtocol::getTag, a -> {
                            if (!keyTemp.equals(a.getTag())) {
                                keyTemp = a.getTag();
                                valueTemp = new HashMap<>();
                            }
                            if(!innerKeyTemp.equals(a.getAttributeKey())){
                                innerKeyTemp = a.getAttributeKey();
                                innerValueTemp = new ArrayList<>();
                            }
                            innerValueTemp.addAll(Arrays.asList(a.getProtocol().split(",")));
                            valueTemp.put(a.getAttributeKey(),innerValueTemp);
                            return valueTemp;
                        },
                        (key1, key2) -> key1));
    }

    private void checkBeforeSave(XssTagAttributeProtocol info){
        if(StringUtil.isEmpty(info.getStatus())){
            throw new BusinessException(ResultCode.NOT_VALID,"状态不能为空！");
        }
        if(StringUtil.isEmpty(info.getTag())){
            throw new BusinessException(ResultCode.NOT_VALID,"标签不能为空！");
        }
        if(StringUtil.isEmpty(info.getAttributeKey())&&!StringUtil.isEmpty(info.getAttributeValue())){
            throw new BusinessException(ResultCode.NOT_VALID,"属性名为空,属性值不能为空！");
        }
        if (StringUtil.isEmpty(info.getAttributeKey())&&!StringUtil.isEmpty(info.getProtocol())){
            throw new BusinessException(ResultCode.NOT_VALID,"属性名为空,协议不能为空！");
        }
        if(!StringUtil.isEmpty(info.getAttributeKey())&&!StringUtil.isEmpty(info.getAttributeValue())&&!StringUtil.isEmpty(info.getProtocol())){
            throw new BusinessException(ResultCode.NOT_VALID,"属性名，属性值，协议不能同时非空");
        }
    }
}
