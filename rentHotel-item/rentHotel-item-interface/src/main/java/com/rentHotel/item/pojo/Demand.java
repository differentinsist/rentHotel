package com.rentHotel.item.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//需求表的实体类
@Table(name = "demand")
public class Demand {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Long id;

    private Integer userid;

    private String dname;

    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private String demand;

    private Integer status;  //控制是否显示等。状态、默认为1代表显示

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", userid=" + userid +
                ", dname='" + dname + '\'' +
                ", createtime=" + createtime +
                ", demand='" + demand + '\'' +
                ", status=" + status +
                '}';
    }
}
