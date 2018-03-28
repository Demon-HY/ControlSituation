package com.control.situation.utils.conversion;

import java.lang.reflect.Array;

/**
 * 数组类型工具类
 * <p/>
 * Created by Demon on 2017/7/22 0022.
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * 数组为空
     *
     * @param array
     * @return boolean
     */
    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 数组不为空
     *
     * @param array
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 在数组中查找位置
     *
     * @param array
     * @param find 查找的元素
     * @param start 查找的开始位置
     * @return int
     */
    public static int indexOf(Object[] array, Object find, int start) {
        if (array == null) {
            return -1;
        }
        if (start < 0) {
            start = 0;
        }
        if (find == null) {
            for (int i = start; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < array.length; i++) {
                if (find.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 是否包含
     *
     * @param array
     * @param find
     * @return boolean
     */
    public static boolean contains(Object[] array, Object find) {
        return indexOf(array, find, 0) != -1;
    }

    /**
     * 合并两个数组
     *
     * @param source
     * @param concat
     * @return <T>[]
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] addAll(Object[] source, Object[] concat) {
        if (source == null) {
            return clone(concat);
        } else if (concat == null) {
            return clone(source);
        }
        T[] joined = (T[]) Array.newInstance(source.getClass().getComponentType(), source.length + concat.length);
        System.arraycopy(source, 0, joined, 0, source.length);
        System.arraycopy(concat, 0, joined, source.length, concat.length);

        return joined;
    }

    /**
     * 克隆一个数组
     *
     * @param array
     * @return <T>[]
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] clone(Object[] array) {
        if (array == null) {
            return null;
        }
        return (T[]) array.clone();
    }
}
