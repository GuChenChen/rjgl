package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.dto.FieldValueDto;
import com.fykj.scaffold.cms.domain.dto.UserFieldValueDto;
import com.fykj.scaffold.cms.domain.entity.AdviceField;
import com.fykj.scaffold.cms.domain.entity.UserFieldValue;
import com.fykj.scaffold.cms.domain.vo.UserFieldValueVo;
import com.fykj.scaffold.cms.mapper.UserFieldValueMapper;
import com.fykj.scaffold.cms.service.IAdviceFieldService;
import com.fykj.scaffold.cms.service.IUserFieldValueService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.support.utils.UUIDUtils;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import result.ResultCode;
import utils.LocalDateTimeUtil;
import utils.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户字段值服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserFieldValueServiceImpl extends BaseServiceImpl<UserFieldValueMapper, UserFieldValue> implements IUserFieldValueService {


    @Autowired
    private IAdviceFieldService adviceFieldService;

    @Override
    public boolean saveUserFieldValue(UserFieldValueDto userFieldValueDto) {
        //获取用户输入的内容
        List<FieldValueDto> fieldValueDtoList = userFieldValueDto.getFieldValueList();
        if (CollectionUtils.isEmpty(fieldValueDtoList)) {
            throw new BusinessException(ResultCode.FAIL,"请填写完成后再提交");
        }
        Map<String, FieldValueDto> map = fieldValueDtoList.stream().collect(Collectors.toMap(FieldValueDto::getFieldId, a -> a,(k1,k2)->k1));

        //获取咨询建议字段
        List<AdviceField> adviceFields = adviceFieldService.findByAdviceId(userFieldValueDto.getAdviceId());

        List<UserFieldValue> list = new ArrayList<>();
        String dataId = UUIDUtils.generateUuid();
        for (AdviceField field : adviceFields) {
            FieldValueDto dto = map.get(field.getId());
            String fieldValue = dto == null?"":dto.getValue();
            //是否必填校验
            if (field.getRequired()) {
                checkRequire(field.getName(),fieldValue);
            }
            //设置用户提交的内容
            UserFieldValue userFieldValue = new UserFieldValue();
            userFieldValue.setFieldId(field.getId());
            userFieldValue.setAdviceId(field.getAdviceId());
            userFieldValue.setUserId(userFieldValueDto.getOpenid());
            userFieldValue.setValue(fieldValue);
            userFieldValue.setDataId(dataId);
            list.add(userFieldValue);
        }
        return saveBatch(list);
    }

    private void checkRequire(String fieldName, String fieldValue){
        if (StringUtil.isEmpty(fieldValue) || StringUtil.isEmpty(fieldValue.trim())) {
            throw new BusinessException(ResultCode.FAIL,fieldName + "必填");
        }
    }

    @Override
    public UserFieldValueVo getDataByAdviceId(String adviceId) {
        UserFieldValueVo vo = new UserFieldValueVo();
        //获取表头
        List<IdTextVo> titleList = adviceFieldService.getByAdviceId(adviceId);

        List<UserFieldValue> list = lambdaQuery().eq(UserFieldValue::getAdviceId,adviceId).list();
        // 以 dataId 和 fieldId 为单位进行分组
        Map<String, Map<String, List<UserFieldValue>>> maps =
                list.stream().collect(Collectors.groupingBy(UserFieldValue::getDataId, Collectors.groupingBy(UserFieldValue::getFieldId)));
        List<List<IdTextVo>> fieldList = vo.getFieldList();
        for (Map<String , List<UserFieldValue>> fieldMap : maps.values()) {
            String commitDate = "";
            List<IdTextVo> dataList = new ArrayList<>();
            for (IdTextVo idTextVo : titleList) {
                List<UserFieldValue> values = fieldMap.get(idTextVo.getId());
                List<IdTextVo> idTextVos = values.stream()
                        .map(it -> new IdTextVo(it.getFieldId(), it.getValue()))
                        .collect(Collectors.toList());
                if (StringUtil.isEmpty(commitDate)) {
                    commitDate = LocalDateTimeUtil.formatDateTime(values.get(0).getCreateDate());
                }
                dataList.addAll(idTextVos);
            }
            IdTextVo titleDateValue = new IdTextVo();
            titleDateValue.setText(commitDate);
            dataList.add(titleDateValue);
            fieldList.add(dataList);
        }
        IdTextVo titleDate = new IdTextVo();
        titleDate.setText("提交时间");
        titleList.add(titleDate);
        vo.setDataList(titleList);
        return vo;
    }
}
