package com.rentHotel.item.bo;

import com.rentHotel.item.pojo.Happytimeimage;

import java.util.List;

//bo里面的了就相当于扩展类，封装类这样子；就是字段不够，需要添加的字段，同时这字段不属于数据库，比如查询的条数之类的
public class HappytimeimageBo {

    private Integer havecount;  //依赖封装查询的条数；记录

    private List<Happytimeimage> happytimeimageList;

    public Integer getHavecount() {
        return havecount;
    }

    public void setHavecount(Integer havecount) {
        this.havecount = havecount;
    }

    public List<Happytimeimage> getHappytimeimageList() {
        return happytimeimageList;
    }

    public void setHappytimeimageList(List<Happytimeimage> happytimeimageList) {
        this.happytimeimageList = happytimeimageList;
    }
}
