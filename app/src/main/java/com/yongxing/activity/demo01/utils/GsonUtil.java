package com.yongxing.activity.demo01.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/** 
 * Gson工具类
 */
public class GsonUtil {

    private static Gson gson = null;
    //没加载的话加载一次
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }
    /**
     * 对象转换成json字符串 
     * @param obj  
     * @return  
     */  
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }  
  

    /** 
     * json字符串转成对象 
     * @param str   
     * @param type  
     * @return  
     */  
    public static <T> T fromJson(String str, Class<T> type) {
        return gson.fromJson(str, type);
    }
    /**
     * JSON转成list集合
     *
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonStr, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json转成map的
     *
     * @return
     */
    public static <T> Map<String, T> GsonToMap(String gsonStr) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
    /**
     * JSON转成含有map的list集合
     *
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMap(String gsonStr) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonStr,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }
}
