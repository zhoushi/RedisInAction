package com.zhou.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */
public class GsonUtils {
    public static Gson getGson()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }

    /**
     * 实体类转换成json字符串
     *
     * @param object
     * @return
     */
    public static String objectToJson(Object object)
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls()
                .create();
        String json = gson.toJson(object);
        return json;
    }

    public static <T> T stringToObject(String json, Class<T> clazz)
    {
        Gson gson = new Gson();
        T t = gson.fromJson(json, clazz);
        return t;
    }

    public static <Entry> List<Entry> getCollectionType(String json, Type type)
    {
        Gson gson = new Gson();
        List<Entry> list = gson.fromJson(json, type);
        return list;
    }

    public static <T> Map<String, T> jsonToMap(String json, Type type)
    {
        Gson gson = new Gson();
        Map<String, T> map = gson.fromJson(json, type);
        return map;
    }
}
