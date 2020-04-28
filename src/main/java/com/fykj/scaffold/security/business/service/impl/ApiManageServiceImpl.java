package com.fykj.scaffold.security.business.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.dto.ApiManageDto;
import com.fykj.scaffold.security.business.domain.entity.ApiManage;
import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;
import com.fykj.scaffold.security.business.domain.params.ApiManageParams;
import com.fykj.scaffold.security.business.mapper.ApiManageMapper;
import com.fykj.scaffold.security.business.service.IApiManageService;
import com.fykj.scaffold.security.business.service.IApiParamManageService;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.httpRequest.HttpRequestUtil;
import com.fykj.scaffold.support.utils.BeanUtil;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.fykj.scaffold.support.conns.Cons.*;
import static com.fykj.scaffold.support.utils.ApiMd5EncryptUtil.encode;

/**
 * @author yangx
 */
@Service
@Slf4j
public class ApiManageServiceImpl extends BaseServiceImpl<ApiManageMapper, ApiManage> implements IApiManageService {

    @Autowired
    private IApiParamManageService apiParamManageService;

    @Autowired
    private IDictService dictService;

    @Override
    public String getParamsOfHttpRequestNeed(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Map<String,String[]> requestParams = new HashMap<String,String[]>(request.getParameterMap());
        String urlCode = requestParams.get("code")[0];
        ApiManage apiManage = detailByUrlCode(urlCode);
        for (String key : requestParams.keySet()) {
            if ("code".equals(key)) {
                requestParams.remove(key);
                break;
            }
        }
        String[] format = {FORMAT};
        String[] pid = {PID};
        String[] _sig = {getMd5Secret(requestParams)};
        requestParams.put("format",format);
        requestParams.put("pid",pid);
        requestParams.put("_sig",_sig);
        return HttpRequestUtil.callThirdPartyRequest(requestParams,apiManage.getMethod(),apiManage.getUrl(),apiManage.getParamType());
    }

    public String getMd5Secret(Map<String,String[]> requestParams) throws NoSuchAlgorithmException {
        List<StringBuffer> list = new LinkedList<>();
        if (requestParams != null && !requestParams.isEmpty()) {
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                for (String s : entry.getValue()) {
                    if(!StringUtils.isEmpty(s)){
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(entry.getKey()).append("=").append(s);
                        list.add(buffer);
                    }
                }
            }
        }
        Collections.sort(list, new Comparator<StringBuffer>() {
            @Override
            public int compare(StringBuffer o1, StringBuffer o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        StringBuffer paramForMd5 = new StringBuffer();
        for(int i = 0; i<list.size(); i++){
            if(StringUtils.isEmpty(paramForMd5.toString())){
                paramForMd5.append(list.get(i));
            }else {
                paramForMd5.append("&"+list.get(i));
            }
        }
        paramForMd5.append("&format="+FORMAT+"&pid="+PID);
        return encode(encode(AUTHCODE+paramForMd5.toString())+AUTHCODE);
    }
    @Override
    public ApiManage detailByUrlCode(String urlCode) {
        ApiManage apiManage = lambdaQuery().eq(ApiManage::getCode,urlCode).one();
        return apiManage;
    }

    @Override
    public IPage<ApiManageDto> findByPage(ApiManageParams params) {
        IPage<ApiManage> result = super.page(params);
        return result.convert(this::convert);
    }

    @Override
    public boolean statusChangeByIds(List<String> idList) {
        List<ApiManage> listEntity = baseMapper.selectBatchIds(idList);
        boolean status = listEntity.get(0).getStatus();
        if(listEntity.size()>1){
            for(ApiManage am:listEntity){
                if(am.getStatus()!=status){
                    throw new BusinessException(ResultCode.FAIL, "批量更改的数据状态不统一，请核验统一后进行操作");
                }
            }
        }
        for(String s:idList){
            baseMapper.updateStatusById(s,status?false:true);
        }
        return true;
    }

    private ApiManageDto convert (ApiManage entity){
        ApiManageDto dto = new ApiManageDto();
        BeanUtil.copyProperties(entity,dto);
        dto.setParamNumber(apiParamManageService.getParamNumByUrlId(entity.getId()));
        dto.setMethodText(dictService.getNameByCode(entity.getMethod()));
        dto.setParamTypeText(dictService.getNameByCode(entity.getParamType()));
        dto.setResponseTypeText(dictService.getNameByCode(entity.getResponseType()));
        return dto;
    }

    @Override
    public ApiManageDto getApiInfoAndParamInfo(String id) {
        ApiManage apiManage = super.getById(id);
        ApiManageDto apiManageDto = convert(apiManage);
        List<ApiParamManage> list = apiParamManageService.getParamByUrlId(id);
        apiManageDto.setApiParamManageList(new ArrayList<>(list));
        return apiManageDto;
    }

    @Override
    public boolean saveApiInfoAndParams(ApiManageDto apiManageDto) {
        ApiManage apiManage = new ApiManage();
        BeanUtil.copyProperties(apiManageDto,apiManage);
        boolean resultOfSaveApi = super.saveOrUpdate(apiManage);
        List<ApiParamManage> apiParamManageList = apiManageDto.getApiParamManageList();
        String urlId;
        if(StringUtil.isNotEmpty(apiManageDto.getId())){
            urlId = apiManageDto.getId();
        }else {
            urlId = lambdaQuery().eq(ApiManage::getCode,apiManageDto.getCode()).one().getId();
        }
        String urlCode = apiManageDto.getCode();
        for(ApiParamManage apm:apiParamManageList){
            apm.setUrlId(urlId);
            apm.setUrlCode(urlCode);
        }
        if(!resultOfSaveApi){
            throw new BusinessException(ResultCode.FAIL, "插入接口信息失败");
        }
        if(!apiParamManageList.isEmpty()){
            boolean resultOfSaveParam = apiParamManageService.saveParamInfo(apiParamManageList);
            if(!resultOfSaveParam){
                throw new BusinessException(ResultCode.FAIL, "插入接口参数信息失败");
            }
        }
        return true;
    }

    @Override
    public boolean validate(String code) {
        List<ApiManage> apiManagesList = baseMapper.selectList(null);
        List<String> codeList = new ArrayList<>();
        for(ApiManage am:apiManagesList){
            codeList.add(am.getCode());
        }
        for(String s:codeList){
            if(s.equals(code)){
                throw new BusinessException(ResultCode.FAIL, "code重复，请重新输入！");
            }
        }
        return true;
    }

    @Override
    public boolean removeBatch(List<String> idList) {
        boolean resultOfRemoveApi = super.removeByIds(idList);
        boolean resultOfRemoveParam = apiParamManageService.removeByApiId(idList);
        if(!resultOfRemoveApi){
            throw new BusinessException(ResultCode.FAIL, "删除接口信息失败");
        }
        if(!resultOfRemoveParam){
            throw new BusinessException(ResultCode.FAIL, "删除接口参数信息失败");
        }
        return true;
    }
}
