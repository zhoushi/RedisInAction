package com.zhou.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class JsonObject {

    public static void main(String... args){

        JSONObject req = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Person person = new Person();

        person.setUsername("zhou");
        person.setAge(18);
        person.setSex("男");
        req.put("data",person);
        jsonArray.add(0,person);

        System.out.println(jsonArray.toJSONString());
        List<NameValuePair>  nvps = new ArrayList<>();

        nvps.add(new BasicNameValuePair("req_name",req.toJSONString()));



        System.out.println(req.toJSONString());
    }
}
