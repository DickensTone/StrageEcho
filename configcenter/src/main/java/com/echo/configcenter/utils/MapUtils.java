package com.echo.configcenter.utils;

import com.alibaba.nacos.shaded.com.google.gson.Gson;

import java.util.Map;

public class MapUtils {
    public static Gson gsonStatic = new Gson();
    public static <K, V> String toJson(Map<K, V> map){

        return gsonStatic.toJson(map);
    }
    public static<K, V> Map<K, V> parseJson(String json){

        Map<K, V> map = gsonStatic.fromJson(json, Map.class);
        return map;
    }
}
