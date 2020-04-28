package com.fykj.scaffold.support.utils;

import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.annotation.DictTrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import utils.StringUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据字典转换工具类
 * Created by xuew
 * on 2019/3/27.
 */
@Slf4j
public class DictTransUtil {

    /**
     * 翻译单个实体类字典值
     *
     * @param source
     * @param <T>
     */
    public static <T> T trans(T source) {
        if (source == null) {
            return null;
        }
        IDictService dictService = SpringContextUtil.getBean(IDictService.class);
        //获取所有属性并翻译字典
        Class targetClass = source.getClass();
        //排除没有注解@DictTrans的字段
        List<Field> formatterFields = getFileds(targetClass);
        //翻译
        doFormatter(formatterFields.stream(), source, targetClass, dictService.findAllDict());
        return source;
    }

    /**
     * 翻译当前集合类中需要翻译的字典值
     *
     * @param sources 待翻译的集合对象
     */
    public static <T> List<T> trans(List<T> sources) {
        if (sources == null || sources.isEmpty()) {
            return sources;
        }
        IDictService dictService = SpringContextUtil.getBean(IDictService.class);
        //获取所有属性并翻译字典
        Class targetClass = sources.get(0).getClass();
        //排除没有注解@DictTrans的字段
        List<Field> formatterFields = getFileds(targetClass);
        //翻译
        sources.parallelStream().forEach(target -> doFormatter(formatterFields.stream(), target, targetClass,
                dictService.findAllDict()));
        return sources;
    }

    private static List<Field> getFileds(Class targetClass) {
        Field[] declaredFields = targetClass.getDeclaredFields();
        targetClass.getDeclaredFields();
        //排除没有注解@DictTrans的字段
        return Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(DictTrans.class))
                //循环列表（并行操作）
                .collect(Collectors.toList());
    }

    /**
     * 对目标类需要翻译的字段进行翻译
     *
     * @param stream
     * @param target      目标对象
     * @param targetClass 目标对象类
     */
    private static <T> void doFormatter(Stream<Field> stream, Object target, Class<T> targetClass, Map<String, String> dictMap) {
        stream.filter(field -> {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(targetClass, field.getName());
            Object invoke = null;
            try {
                invoke = propertyDescriptor.getReadMethod().invoke(target, new Object[]{});
            } catch (IllegalAccessException e) {
                log.warn("待翻译的字段的get是无法访问的", e);
            } catch (InvocationTargetException e) {
                log.warn("调用待翻译的字段的get方法时报错", e);
            } catch (Exception e) {
                log.warn("确保属性有get,set方法", e);
            }
            return invoke != null;
        }).forEach(field -> {
            DictTrans annotation = field.getAnnotation(DictTrans.class);
            String formatterField = annotation.transTo();
            //当注解中不指定其他字段时，默认翻译到加注解的属性上
            if (StringUtil.isEmpty(formatterField)) {
                formatterField = field.getName();
            }
            try {
                PropertyDescriptor orginPropertyDescriptor = BeanUtils.getPropertyDescriptor(targetClass, field.getName());
                Object value = orginPropertyDescriptor.getReadMethod().invoke(target, new Object[]{});
                String dictName = dictMap.get(String.valueOf(value));
                PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(targetClass, formatterField);
                if (dictName == null) {
                    propertyDescriptor.getWriteMethod().invoke(target, "");
                } else {
                    propertyDescriptor.getWriteMethod().invoke(target, dictName);
                }
            } catch (IllegalAccessException e) {
                log.warn("待翻译的字段的set是无法访问的", e);
            } catch (InvocationTargetException e) {
                log.warn("调用待翻译的字段的set方法时报错", e);
            } catch (Exception e) {
                log.warn("调用待翻译的字段的set方法时报错,推测类型不匹配", e);
            }
        });

    }
}
