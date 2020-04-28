package com.fykj.scaffold.support.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import utils.ClassUtil;
import utils.StringUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 查询条件组装构建工具
 *
 * @author zhangzhi
 */
public class QueryWrapperBuilder {

    private QueryWrapperBuilder() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 遍历Params中的字段，依次组装出查询条件
     *
     * @param params 查询条件字段封装{@link BaseParams}
     * @param <T>    查询的目标对象
     * @return {@link QueryWrapper}
     */
    public static <T> QueryWrapper<T> build(BaseParams params) {
        QueryWrapper<T> result = new QueryWrapper<>();
        if (params == null) {
            handleOrder(result, new BaseParams.Order());
            return result;
        }

        List<Field> fields = ClassUtil.getFields(params.getClass());
        fields.stream().forEach(it -> {
            MatchType matchType = it.getAnnotation(MatchType.class);
            Object value = ClassUtil.getFieldValue(params, it);
            if (matchType != null && StringUtil.isNotEmpty(value)) {
                String[] fieldNames = getFieldName(matchType, it);
                buildFieldArray(matchType.value(), result, value, fieldNames);
            }
        });

        List<BaseParams.Order> orders = params.getOrders();
        orders.forEach(it -> handleOrder(result, it));
        return result;
    }

    /**
     * 处理排序参数
     *
     * @param wrapper 包装器对象{@link QueryWrapper}
     * @param order   排序参数对象{@link BaseParams.Order}
     */
    private static void handleOrder(QueryWrapper<?> wrapper, BaseParams.Order order) {
        switch (order.getSort()) {
            case BaseParams.Order.SORT_ASC:
                wrapper.orderByAsc(order.getColumn());
                break;
            case BaseParams.Order.SORT_DESC:
                wrapper.orderByDesc(order.getColumn());
                break;
            default:
                break;
        }
    }

    /**
     * 获取字段名称数组
     * -- 如果注解{@link MatchType} 中有指定字段名称，返回注解{@link MatchType}中指定的字段名称数组
     * -- 否则 返回当前字段的名称
     *
     * @param matchType 字段注解{@link MatchType}对象
     * @param field     字段对象
     * @return 返回查询匹配的字段名称数组
     */
    private static String[] getFieldName(MatchType matchType, Field field) {
        String[] result = matchType.fieldName();
        if (result.length == 0) {
            return new String[]{field.getName()};
        }
        return result;
    }

    /**
     * 遍历字段名称数组，组装查询条件
     *
     * @param type       查询类型
     * @param wrapper    查询包装器{@link QueryWrapper}
     * @param value      字段值
     * @param fieldNames 字段名称数组
     * @param <T>        查询目标对象
     */
    private static <T> void buildFieldArray(QueryType type, QueryWrapper<T> wrapper, Object value, String[] fieldNames) {
        if (fieldNames.length > 1) {
            wrapper.and(it -> Arrays.stream(fieldNames).forEach(field -> {
                it.or();
                buildField(type, it, value, StringUtil.camelToUnderline(field));
            }));

        } else {
            buildField(type, wrapper, value, StringUtil.camelToUnderline(fieldNames[0]));
        }

    }

    /**
     * 组装单个字段查询条件
     *
     * @param type      查询类型
     * @param wrapper   查询包装器{@link QueryWrapper}
     * @param value     字段值
     * @param fieldName 字段名称
     * @param <T>       查询目标对象
     */
    private static <T> void buildField(QueryType type, QueryWrapper<T> wrapper, Object value, String fieldName) {
        switch (type) {
            case EQ:
                wrapper.eq(fieldName, value);
                break;
            case NE:
                wrapper.ne(fieldName, value);
                break;
            case LIKE:
                wrapper.like(fieldName, value);
                break;
            case NOT_LIKE:
                wrapper.notLike(fieldName, value);
                break;
            case IN:
                wrapper.in(fieldName, ((List) value).toArray());
                break;
            case NOT_IN:
                wrapper.notIn(fieldName, ((List) value).toArray());
                break;
            case DATE_START:
                wrapper.ge(fieldName, value);
                break;
            case DATE_END:
                wrapper.lt(fieldName, getEndDate(value));
                break;
            case TIME_START:
                wrapper.ge(fieldName, value);
                break;
            case TIME_END:
                wrapper.le(fieldName, value);
                break;
            case GE:
                wrapper.ge(fieldName, value);
                break;
            case LE:
                wrapper.le(fieldName, value);
                break;
            case LIKE_RIGHT:
                wrapper.likeRight(fieldName, value);
                break;
            default:
                break;
        }
    }

    /**
     * 获取结束日期对象（用于日期区间查询时结束日期边界生成）
     * -- 输入日期往后加一天的凌晨零点
     *
     * @param value 指定日期
     * @return 结束日期边界
     */
    private static Object getEndDate(Object value) {
        if (value instanceof String) {
            value = LocalDate.parse((String) value).plusDays(1);
        }
        return value;
    }

}
