package com.zhou.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/24.
 */
public class Person implements Serializable {

    private String username;

    private Integer age;

    private String sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
