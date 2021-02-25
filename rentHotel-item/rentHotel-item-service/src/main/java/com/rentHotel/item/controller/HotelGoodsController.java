package com.rentHotel.item.controller;


import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.pojo.Hotelgoods;
import com.rentHotel.item.service.HotelGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("goods")
public class HotelGoodsController {

    @Autowired
    private HotelGoodsServiceImpl hotelGoodsService;


    /**显示所有商品；分页；模糊查询等
     *
     * @param key  模糊查询参数
     * @param page  当前页码
     * @param rows  显示多少条数据在一页
     * @param sortBy  通过什么排序
     * @param desc   升序还是降序（true、false）
     * @return 发现一个问题：就是前端如果传递过安利的字段不对的话；就会导致报错；比如sortBy=一个不是当前表的字段；就会报错。
     *    api/item/goods/showAllGoods?key=H&page=1&rows=8&sortBy=goodsprice&desc=true
     */
    @GetMapping("showAllGoods")
    public ResponseEntity<PageResult<Hotelgoods>> showAllGoods(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "8")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<Hotelgoods> hotelgoods = this.hotelGoodsService.showAllGoods(key,page,rows,sortBy,desc);

        //判断是否能从数据库拿到[集合]数据
        if (CollectionUtils.isEmpty(hotelgoods.getItems())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotelgoods);
    }

}
