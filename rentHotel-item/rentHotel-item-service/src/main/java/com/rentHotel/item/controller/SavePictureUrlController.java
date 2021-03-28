package com.rentHotel.item.controller;

import com.rentHotel.item.service.SavePictureUrlServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class SavePictureUrlController {

    @Autowired
    private SavePictureUrlServiceImpl savePictureUrlService;


    /**保存头像的URL到数据库中（在图片上传到FastDFS之后，会Feign远程调用此接口）
     *
     * @param image
     * @return 还有一个问题；如果是必须传递的参数；我还要写request=true吗？不用吧；应该是默认不写就是必传递；因为不必传递才写=false
     */
    @PostMapping("saveImageUrl")
    public ResponseEntity<Void> savePersonHeadPortrait(
            @RequestParam("image")String image,
            @RequestParam("username")String username){
        if (StringUtils.isBlank(image) | StringUtils.isBlank(username)){
            System.out.println("没有传递进来图片在虚拟机中FastDFS服务器的路径");
            return null;
        }
        this.savePictureUrlService.savePersonHeadPortrait(image,username);
        System.out.println("图片的URL应该是成功保存到数据库了,是通过Upload微服务远程Feign技术调用此模块实现");
        return ResponseEntity.status(HttpStatus.CREATED).build();//意思是创建成功了对吧201；
    }
}
