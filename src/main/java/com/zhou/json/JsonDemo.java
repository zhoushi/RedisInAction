package com.zhou.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */
public class JsonDemo {

    public static void main(String[] args){

        Person person = new Person();
        person.setUsername("zhou");
        person.setSex("男");
        person.setAge(18);

        List<Person> list = new ArrayList<>();
        list.add(person);

        Map<String,Object> map = new HashMap<>();
        map.put("name","zhoushixian");
        map.put("age",18);
        map.put("sex","男");
        String s1 = GsonUtils.objectToJson(map);

        String s = GsonUtils.objectToJson(list);
        System.out.println(s);
        System.out.println(s1);
    }
}

