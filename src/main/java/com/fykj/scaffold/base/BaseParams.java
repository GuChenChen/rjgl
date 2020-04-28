package com.fykj.scaffold.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 查询条件字段封装类
 *
 * @author zhangzhi
 */
@Data
@ApiModel(value = "分页查询条件数据模型")
public class BaseParams implements Serializable {
    private static final long serialVersionUID = -3276425051906843805L;

    @ApiModelProperty(value = "当前页码（从1开始）", position = 9997)
    private Integer currentPage = 1;

    @ApiModelProperty(value = "每页条数", position = 9998)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序参数列表", position = 9999)
    private List<Order> orders = new ArrayList<>();

    /**
     * 获取分页查询对象
     *
     * @param <T> 查询目标对象
     * @return 分页参数对象
     */
    @JsonIgnore
    public <T> IPage<T> getPage() {
        return new Page<>(this.currentPage, this.pageSize);
    }

    @JsonIgnore
    public static <T> IPage<T> defaultPage() {
        return new Page<>(1, 10);
    }

    /**
     * 获取排序参数列表 带默认值
     *
     * @return 排序参数列表
     */
    public List<Order> getOrders() {
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.singletonList(new Order());
        }
        return orders;
    }

    /**
     * 排序数据模型
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order implements Serializable {

        private static final long serialVersionUID = -6997915173508844480L;

        /**
         * 排序方向-倒序
         */
        public static final String SORT_DESC = "desc";
        /**
         * 排序方向-正序
         */
        public static final String SORT_ASC = "asc";

        @ApiModelProperty(value = "排序字段名称", position = 9999)
        private String column = Cons.SORT_COLUMN_DEFAULT;

        @ApiModelProperty(value = "正序（asc）/倒序（desc）", allowableValues = "asc, desc", position = 9999)
        private String sort = Cons.SORT_ORDER_DEFAULT;

        /**
         * 驼峰法命名转下划线处理
         *
         * @return 下划线格式数据库字段名称
         */
        public String getColumn() {
            return StringUtil.camelToUnderline(column);
        }

    }

}
