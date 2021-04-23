package com.rentHotel.item.controller;

import com.rentHotel.item.pojo.RoomOrdersHistory;
import com.rentHotel.item.service.RoomOrdersToRedisAndDatabaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("ordermessage")
public class RoomOrdersToRedisAndDatabaseController {

    @Autowired
    private RoomOrdersToRedisAndDatabaseService roomOrdersService;


    /**实现点击下单按钮后保存房间信息到redis中，key使用用户ID+房间号，这两个唯一且不改动，方便取出来
     * 然后同时保存room信息和个人信息到数据库，作为历史记录展示。
     *
     * 【改变思路了】，我直接把房间号存入redis中就行了，string类型，快捷也合适，只要redis中找不到此房间号就是到时了，就可以出租。
     *  同时把对应的房间按钮属性btnstatus改为false；然后显示倒计时
     * @param  id  用户person表的id(不会变的，用户名改了也不变)
     * @param name 用户名
     * @param roomid  房间编号
     * @param roomprice  房间价格
     * @param roomtime  房间时长
     *       createtime 下单时间 不用传递，保存到数据库同时就使用默认时间就是但是下单时间
     * @return
     *
     *
     *   http://localhost:12261/api/item/ordermessage/saveRoomOrder?id=用户id
     *          &name=用户名&roomid=房间编号roomprice=房间价格&roomtime=房间时长&createtime=下单时间。
     *
     *       redis中要存什么？   存：（key，value）=（用户ID+房间号，值）值不是过期时间、可以是房间时长
     *         考 虑放在string里面还是放在list集合里面？？集合里面可以设置单个成员的过期时间吗？
     */
    @GetMapping("saveRoomOrder")
    public ResponseEntity<Void> saveRoomOrdersToRedisAndDatabase(
            @Valid RoomOrdersHistory roomOrdersHistory,
            @RequestParam("id")Integer id
        ){
        //这么多参数一个个校验null吗？还是写个对象？但是对象的属性也全都不能为null啊，这是要求
        System.out.println("Controller层看前端参数："+roomOrdersHistory);
        System.out.println("Controller层看前端参数id："+id);
        if(id == null){
            return ResponseEntity.badRequest().build();//对吧？
        }
        this.roomOrdersService.saveOrdersToDatabaseAndRedis(roomOrdersHistory, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();  //Void的返回值，状态码201
    }


    /**
     * 通过key去redis中找有无此key，没有就说明房间时长到期了，也即是退房了。  同时查出过期时间
     * @param redisKeyString
     * @return
     */
    @GetMapping("findMessageRedis")
    public ResponseEntity<String> findRoomMessageFromRedis(
            @RequestParam("redisKeyString")String redisKeyString
    ){
        if(StringUtils.isBlank(redisKeyString)){
            return ResponseEntity.badRequest().build();
        }
        String queryResult = this.roomOrdersService.findRoomMessageRedis(redisKeyString);

        System.out.println("redis中查如果cha不到这个key返回的是什么"+queryResult);
        //有必要做校验吗？  redis中查不到返回的是什么
//        if(StringUtils.isBlank(queryResult)){
        if(queryResult == "666"){
            System.out.println("redis中查不到这个key返回的是什么:"+queryResult);
            return ResponseEntity.ok(queryResult);  // data=666
        }
        return ResponseEntity.ok(queryResult);  //返回结果是怎样的结构，有状态码有结果吧？？
//        redis中通过键找，找不到的话返回的是null(对于String类型来说的)，而且返回状态码一样是201
    }




    /**找出所有的已被正在使用的房间；也就是redis中能查找到的；还没过期的；所有的。
     *
     * @return
     */
    @RequestMapping("queryAllRoomFromRedis")
    public ResponseEntity<Void> queryAllRoomidFromRedis(@RequestParam("id")Integer id){
        this.roomOrdersService.queryAllRoomidFromRedis();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }






    //布尔值的返回值写法：  怎么返回Boolean值，试试不使用ResponseEntity试试看
//    public ResponseEntity<Boolean> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
//        Boolean boo = this.orderService.updateStatus(id, status);
//        if (boo == null) {
//            // 返回400
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        // 返回204
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
