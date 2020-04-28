package com.fykj.scaffold.support.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import utils.StreamUtil;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * BeanUtil
 *
 * @author zhangzhi
 */
public class BeanUtil extends BeanUtils {

    public static void copyPropertiesExcludeNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, merge(ignoreProperties, getNullPropertyNames(source)));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(it -> Objects.isNull(src.getPropertyValue(it)))
                .toArray(String[]::new);
    }

    /**
     * 合并两个字符串数组
     *
     * @param arr1 第一个字符串数组
     * @param arr2 第二个字符串数组
     * @return 合并结果
     */
    private static String[] merge(String[] arr1, String[] arr2) {

        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }

        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }

        return Stream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .filter(StreamUtil.distinctByKey(it -> it))
                .toArray(String[]::new);
    }

}
