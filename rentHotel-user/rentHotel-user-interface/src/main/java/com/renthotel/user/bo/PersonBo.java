package com.renthotel.user.bo;

import com.renthotel.user.pojo.Person;

//这个类是暂时的类，实现token和用户信息一起返回而已，后面要删除的
public class PersonBo {

    private String token;//token信息

    private Person person;//用户对象信息，用于装用户名和基本信息

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
