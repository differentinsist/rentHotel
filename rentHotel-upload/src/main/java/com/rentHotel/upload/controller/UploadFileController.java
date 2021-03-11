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


    /**上传头像
     *
     * @param file
     * @return
     */
    @PostMapping("headPortrait")
    public ResponseEntity<String> uploadHeadPortrait(@RequestParam("file")MultipartFile file){
        String imageUrl = this.uploadFileService.uploadHeadPortrait(file);
        if (StringUtils.isBlank(imageUrl)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
    }
}
