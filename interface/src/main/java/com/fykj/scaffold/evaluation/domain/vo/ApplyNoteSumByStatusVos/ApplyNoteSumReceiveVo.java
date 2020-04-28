package com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
* @Description 测试申请统计（按状态）
* @Author yangx
* @Date 2020/4/8 8:56
**/
@Data
public class ApplyNoteSumReceiveVo {

    private String status;

    private String month;

    private Integer count;

    private BigDecimal money;
}
