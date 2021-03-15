package com.rentHotel.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "happytimeimage")
public class Happytimeimage {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Integer id;
    private String picture;//图片的路径
    private Date createtime; //图片创建的时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
