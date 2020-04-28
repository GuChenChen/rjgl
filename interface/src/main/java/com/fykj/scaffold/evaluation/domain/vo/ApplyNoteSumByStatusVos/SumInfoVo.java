package com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos;

import lombok.Data;

import java.util.List;

/**
* @Description 测试申请统计主信息（按状态）
* @Author yangx
* @Date 2020/4/8 8:56
**/
@Data
public class SumInfoVo {

   private String status;

   private List<MonthSumVo> monthList;
}
