package com.renthotel.common.utils;

import org.apache.commons.lang3.StringUtils;

/** 【这里可能会有问题，因为我把Long改为了Integer】
 * 从jwt解析得到的数据是Object类型，转换为具体类型可能出现空指针，
 * 这个工具类进行了一些转换
 */
public class ObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Integer toLong(Object obj) {
        if (obj == null) {
            //return 0L;  Long类型的时候就是这样加L
            return 0;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Integer.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Integer.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Integer.valueOf(obj.toString());
        } else {
//            return 0L;
            return 0;
        }
    }

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }
}