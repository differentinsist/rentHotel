package com.rentHotel.item.service;

import com.rentHotel.item.mapper.HappyTimeImageMapper;
import com.rentHotel.item.pojo.Happytimeimage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HappyTimeImageServiceImpl {

    @Autowired
    private HappyTimeImageMapper happyTimeImageMapper;

    public List<Happytimeimage> queryAllHappyImages(String imagetype,Boolean sortbytime) {
        String sort = sortbytime ? "asc" : "desc";
        System.out.println("--------=======sort===--"+sort);
        System.out.println("--------=====sortbytime=====--"+sortbytime);
//        Boolean sss = sortbytime ? "desc" : "asc";
        return this.happyTimeImageMapper.selectImagesByTypeSortByTime(imagetype,sort);
    }
}
