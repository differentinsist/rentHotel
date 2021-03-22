package com.renthotel.common.pojo;

/**
 * 载荷对象
 */
public class UserInfo {

    //private Long id;  //我应该改为Integer对吧？？？？？好像我数据库没有id；只有序号
    private Integer id;  //我应该改为Integer对吧？？？？？好像我数据库没有id；只有序号
    private String username;    //是name还是username来着？？

    public UserInfo() {
    }
    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}