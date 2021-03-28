package com.rentHotel.upload.controller;

import com.rentHotel.upload.service.UploadFileServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class UploadFileController {

    @Autowired
    private UploadFileServiceImpl  uploadFileService;


    /**上传头像（现在只是保存到linux的FastDFS中还没有保存到数据库）
     *保存到数据库是顺便，而且是使用Feign远程调用吗？还是直接在这个模块写dao层，远程
     * 调用会不会出问题，因为我这个上传模块是绕过网关的，而Item模块是要经过网关才能访问？不是的把
     * @param file
     * @return
     */
    @PostMapping("headPortrait")
    public ResponseEntity<String> uploadHeadPortrait(
            @RequestParam("file")MultipartFile file,
            @RequestParam("name")String name){
        String imageUrl = this.uploadFileService.uploadHeadPortrait(file,name);
        if (StringUtils.isBlank(imageUrl)){
            System.out.println("图片上传到FastDFS了吗"+imageUrl);
            return ResponseEntity.badRequest().build();
        }
        System.out.println("图片上传到FastDFS了吗"+imageUrl);//上传头像前端那里不用写发送请求;直接给请求路径就可以了
        return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
    }
}
