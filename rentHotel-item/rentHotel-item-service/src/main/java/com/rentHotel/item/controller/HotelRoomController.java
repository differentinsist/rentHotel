package com.rentHotel.item.controller;

import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.pojo.Hotelroom;
import com.rentHotel.item.pojo.RoomOrdersHistory;
import com.rentHotel.item.service.HotelRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("room")
public class HotelRoomController {

    @Autowired
    private HotelRoomServiceImpl hotelRoomService;


    /**分页查询+排序显示所有房间信息
     *
     * @param page  当前页数？
     * @param rows  一页显示多少条数据
     * @param sortBy  通过什么排序
     * @param desc    降序还是升序
     * @return
     *  api/item/room/showAllRoomMessage?page=1&rows=5&sortBy=roomprice&desc=true(这里你必须写true或false而不是直接写升序或降序)
     */
    @GetMapping("showAllRoomMessage")
    public ResponseEntity<PageResult> showAllRoomMessage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        //这里还要判断null吧；以后再写
        PageResult<Hotelroom> hotelrooms = this.hotelRoomService.showAllRoomMessage(page,rows,sortBy,desc);
        //判断是否查到集合数据：
        if (CollectionUtils.isEmpty(hotelrooms.getItems())){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotelrooms);
    }


    /**根据UserID查找用户的开房历史记录，为什么不根据用户名，因为用户名可以改动，但是ID是自增的那个；不会改动
     *
     * @param userid  用户person表的id，唯一不变。
     * @return
     */
    @GetMapping("queryRoomHistoryByUserId")
    public ResponseEntity<List<RoomOrdersHistory>> queryAllHistoryByUserId(
            @RequestParam("userid")Integer userid
    ){

        List<RoomOrdersHistory> roomOrdersHistoryList =  this.hotelRoomService.queryAllHistoryByUserId(userid);



        return ResponseEntity.ok(roomOrdersHistoryList);
    }
}
