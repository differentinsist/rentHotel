package com.rentHotel.item.api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//feign的接口
public interface SavePictureApi {

    //保存头像的URL到数据库的接口
    @PostMapping("saveImageUrl")
    public Void savePersonHeadPortrait(@RequestParam("image")String image, @RequestParam("username")String username);
}
