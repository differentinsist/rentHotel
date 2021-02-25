package com.rentHotel.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "hotelgoods")
public class Hotelgoods {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Integer gid; //id主键他们是用Long类型的？？？？
    private String goodsid; //商品的id
    private Character goodsbrand; //商品的品牌
    private String goodsname; //商品的名称
    private BigDecimal goodsprice; //商品的价格；数据库金钱类型可以是decimal或bigint
    private Integer goodsstock; //商品的库存
    private String goodsdiscription; //商品的描述
    private String goodspicture; //商品图片的URI路径


    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public Character getGoodsbrand() {
        return goodsbrand;
    }

    public void setGoodsbrand(Character goodsbrand) {
        this.goodsbrand = goodsbrand;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public BigDecimal getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodsstock() {
        return goodsstock;
    }

    public void setGoodsstock(Integer goodsstock) {
        this.goodsstock = goodsstock;
    }

    public String getGoodsdiscription() {
        return goodsdiscription;
    }

    public void setGoodsdiscription(String goodsdiscription) {
        this.goodsdiscription = goodsdiscription;
    }

    public String getGoodspicture() {
        return goodspicture;
    }

    public void setGoodspicture(String goodspicture) {
        this.goodspicture = goodspicture;
    }
}
