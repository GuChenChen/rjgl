package com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* @Description 测试申请统计月份信息（按状态）
* @Author yangx
* @Date 2020/4/8 8:56
**/
@Data
public class MonthSumVo {

    private String month;

    private List<PropertyVo> propertyList;

    public MonthSumVo(String month, List<PropertyVo> propertyList){
        this.month = month;
        this.propertyList = propertyList;
    }
}
