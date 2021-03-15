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

    public List<Happytimeimage> queryAllHappyImages() {

        return this.happyTimeImageMapper.selectAll();
    }
}
