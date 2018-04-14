package com.control.situation.utils.conversion;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class ListUtils {

    /**
     * ArrayList 去重
     * @param list
     * @return
     */
    public static List<T> arrayListRemovalRepet(List<T> list){
        List<T> newList = new ArrayList<>();     //创建新集合
        for (Object obj : list) {          //遍历老集合
            T t = (T) obj;
            if (!newList.contains(t)) {      //如果新集合中不包含旧集合中的元素
                newList.add(t);       //将元素添加
            }
        }
        return newList;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        String[] test = {"a","b","c","d","e","f"};
//
//        for (int i = 0; i < 10000; i++) {
//            list.add(test[i]);
//        }
//
//        arrayListRemovalRepet(list);
//    }
}
