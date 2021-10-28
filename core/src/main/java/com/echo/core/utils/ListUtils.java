package com.echo.core.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    //TODO to deal the boundary
    public static  List<Integer> getArrayListFromStringArray(String[] array){
        List<Integer> list = new ArrayList<>();
        for (String s : array) {
            list.add(Integer.parseInt(s));
        }

        return list;
    }
}
