package com.rentHotel.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "hotelroom")
public class Hotelroom {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Integer rid;
    private String roomid;
    private BigDecimal roomprice; //金钱价格的数据类型在数据库中可以使用decimal(对应BigDecimal)或bigint(对应BigInteger)
    private String roompicture;  //房间照片的URI路径
    private String roomtime;  //房间时长

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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

    public String getRoompicture() {
        return roompicture;
    }

    public void setRoompicture(String roompicture) {
        this.roompicture = roompicture;
    }

    public String getRoomtime() {
        return roomtime;
    }

    public void setRoomtime(String roomtime) {
        this.roomtime = roomtime;
    }
}
