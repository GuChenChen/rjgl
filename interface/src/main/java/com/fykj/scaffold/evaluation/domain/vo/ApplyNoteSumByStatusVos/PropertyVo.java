package com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;


/**
* @Description 详细信息
* @Author yangx
* @Date 2020/4/8 8:56
**/
@Data
public class PropertyVo<T> {

    private String propertyName;

    private T value;

    public PropertyVo(String propertyName,T value){
        this.propertyName = propertyName;
        this.value = value;
    }
}
