package com.rentHotel.item.controller;

import com.rentHotel.item.pojo.Happytimeimage;
import com.rentHotel.item.service.HappyTimeImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/happytime")
public class HappyTimeImageController {

    @Autowired
    private HappyTimeImageServiceImpl happyTimeImageService;

    /**查询所有图片（现在没有进行分类；后面还有分类；有趣的/斗图的/等等）
     *
     * @return
     */
    @GetMapping("findAllImages")
    public ResponseEntity<List<Happytimeimage>> queryAllHappyImages(
            @RequestParam(value = "imagetype", required = false)String imagetype,
            @RequestParam(value = "sortbycreatedtime", defaultValue = "false")Boolean sortbycreatedtime
            ){
        //会有了类型问题吗？数据库是char java是Character  然后前端却是String
//        String toChar = imagetype;
        List<Happytimeimage> happytimeimageList = this.happyTimeImageService.queryAllHappyImages(imagetype,sortbycreatedtime);
        if (CollectionUtils.isEmpty(happytimeimageList)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(happytimeimageList);
    }

}
