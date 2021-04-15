package com.rentHotel.item.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


//房间下单表；下单的记录都会保存在这里；作为历史记录展示。
@Table(name = "roomhistory")
public class RoomOrdersHistory {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Integer id; //当前表自增id


    @NotNull
    private Integer userid;
    @NotNull
    private String username;
    @NotNull
    private String roomid; //房间号
    @NotNull
    private BigDecimal roomprice; //房间价格信息
    @NotNull
    private Boolean buttonstatus; //用来装房间的状态值,控制下单按钮的显示或隐藏,但不是他直接控制，而是房间表，这个是历史记录表。
    @NotNull
    private String roomtime; //房间时长
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm", timezone = "GMT+8")
    private Date createtime; //下单时间
    private Integer status; //状态默认为1；控制历史记录是否显示

    public  RoomOrdersHistory(){};

    public RoomOrdersHistory(
            @NotNull Integer userid,
            @NotNull String username,
            @NotNull String roomid,
            @NotNull BigDecimal roomprice,
            @NotNull Boolean buttonstatus,
            @NotNull String roomtime
    ) {
        this.userid = userid;
        this.username = username;
        this.roomid = roomid;
        this.roomprice = roomprice;
        this.buttonstatus = buttonstatus;
        this.roomtime = roomtime;
    }

    public Integer getId() {
        return id;
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

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public BigDecimal getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(BigDecimal roomprice) {
        this.roomprice = roomprice;
    }

    public Boolean getButtonstatus() {
        return buttonstatus;
    }

    public void setButtonstatus(Boolean buttonstatus) {
        this.buttonstatus = buttonstatus;
    }

    public String getRoomtime() {
        return roomtime;
    }

    public void setRoomtime(String roomtime) {
        this.roomtime = roomtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
        return "RoomOrdersHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", roomid='" + roomid + '\'' +
                ", roomprice=" + roomprice +
                ", buttonstatus=" + buttonstatus +
                ", roomtime='" + roomtime + '\'' +
                ", createtime=" + createtime +
                ", status=" + status +
                '}';
    }
}
