package com.zhou.guava;

/**
 * Created by Administrator on 2017/7/27.
 */
public class StringUtils {

    public static String upToLow(String s)throws Exception{
        return s.toUpperCase();
    }

    public static String upTo(String s) {
        if (org.springframework.util.StringUtils.isEmpty(s)){
            try {
                throw new Exception("zz");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s.toUpperCase();
    }
}
