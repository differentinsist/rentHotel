package com.rentHotel.item.service;

import com.rentHotel.item.mapper.SavePictureUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePictureUrlServiceImpl {

    @Autowired
    private SavePictureUrlMapper savePictureUrlMapper;

    public void savePersonHeadPortrait(String image, String username) {
        this.savePictureUrlMapper.savePersonHeadPortrait(image,username);
    }
}
