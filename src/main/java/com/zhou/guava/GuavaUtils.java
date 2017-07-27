package com.zhou.guava;


import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/27.
 */
public class GuavaUtils {

    public static ArrayList newArrayList()throws Exception{
        return new ArrayList();
    }

    public static ArrayList newArrayLists(){
        try {
            return new ArrayList();
        }catch (Exception e){
            throw e;
        }

    }
}
