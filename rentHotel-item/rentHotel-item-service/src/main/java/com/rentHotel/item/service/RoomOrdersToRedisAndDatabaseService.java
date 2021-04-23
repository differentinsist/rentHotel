package com.rentHotel.item.service;

import com.rentHotel.item.mapper.HotelRoomMapper;
import com.rentHotel.item.mapper.SaveRoomOrdersHistoryToDatabaseMapper;
import com.rentHotel.item.pojo.RoomOrdersHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RoomOrdersToRedisAndDatabaseService {

    @Autowired
    private SaveRoomOrdersHistoryToDatabaseMapper saveRoomOrders;
    @Autowired    //spring提供的模板，操作redis数据库
    private StringRedisTemplate redisTemplate;
    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    //应该还有在此方法添加一个删除redis中房间信息的功能；当然抽取出俩也行。
    //用户点击房间的下单按钮；确定后就保存到redis中，（下面两个方法可以合并到这里；这样就不用发这么多请求了）
    public void saveOrdersToDatabaseAndRedis(RoomOrdersHistory roomOrdersHistory, Integer id) {
        //String str = id + "_" + roomid;  //(id是用户peson表的id,唯一不变)合并在一起作为键保存？
        //(id是用户peson表的id,唯一不变)不是roomhistory表的id，合并在一起作为键保存？
//        String str = id + "_" + roomOrdersHistory.getRoomid();
//        System.out.println(str);

        //保存房间信息到redis中，只存用户id+房间号
        //ListOperations<String, String> opsForList = redisTemplate.opsForList();
        //还没有设置过期时间？？
        //Long aLong = opsForList.leftPush(str, roomOrdersHistory.getRoomtime()); //保存到redis成功返回1是吧；是的


//        改变了思路，直接把房间号作为key使用string类型保存到redis中，房间号是唯一的【这个是没有返回值的,怎么知道是否成功】

        //保存并设置存活时间，字符串截取出数字  截取"1.5小时这种会差别很大的"
        Long outTime = tirmNumberFromString(roomOrdersHistory.getRoomtime()) * 60 ;//得出来的是小时 * 60 = 分
        System.out.println("看看是截取"+roomOrdersHistory.getRoomtime()+"成:"+outTime+"有小数点的截取一点会差别大");
        this.redisTemplate.opsForValue()
                .set(roomOrdersHistory.getRoomid(),roomOrdersHistory.getRoomtime(),outTime, TimeUnit.MINUTES);

        //自定义几个测试看看是否收到过期通知
//        this.redisTemplate.opsForValue().set("100","100的value",10,TimeUnit.SECONDS);
//        this.redisTemplate.opsForValue().set("101","101的value",15,TimeUnit.SECONDS);
//        this.redisTemplate.opsForValue().set("102","102的value",10,TimeUnit.SECONDS);
//        this.redisTemplate.opsForValue().set("103","103的value",15,TimeUnit.SECONDS);
//        this.redisTemplate.opsForValue().set("104","103的value",20,TimeUnit.SECONDS);


        //在房间号保存到redis中后，同时去mysql数据库改变btnstatus的状态值
        Boolean status = !roomOrdersHistory.getButtonstatus();
        Integer sqlResult = this.hotelRoomMapper.updateBtnStatus(status,roomOrdersHistory.getRoomid());
        if(sqlResult == 1){
            System.out.println("sqlResult=1就是修改状态值成功"+sqlResult);
        }else{
            System.out.println("等于0就是修改状态值失败了"+sqlResult);
        }

        //本来我想通过list数据类型的；但是不会还是不行来着？成员不能单独设置过期时间；只能整体设置过期时间。
        //其实就是只能指定key的失效时间；不能指定Value的


        //我直接通过构造方法来赋值，而不是set方法，这样好吗？   突然发现构成方法括号里面传递参数还需要按照顺序的,不对应就报错

//        redisTemplate.boundListOps("roomredis").leftPush(roomOrdersHistory.getRoomid());
        RoomOrdersHistory roomObj = new RoomOrdersHistory(
                roomOrdersHistory.getUserid(),
                roomOrdersHistory.getUsername(),
                roomOrdersHistory.getRoomid(),
                roomOrdersHistory.getRoomprice(),
                roomOrdersHistory.getButtonstatus(),
                roomOrdersHistory.getRoomtime()
        );
        //保存被下单的房间对象到roomhistory表，作为历史记录。
        this.saveRoomOrders.insertSelective(roomObj);
    }



    //工具房间号查找redis中有没有这个key，点击下单按钮的时候就会触发，如果redis中有；就代表被人住了，那我还应该查出过期时间,
    // 显示多久退房可以使用   【同时查出存活时间】
    public String findRoomMessageRedis(String redisKeyString) {
        //从redis中找有无此key，没有就代表时长到期了，退房了。
        String resultStr = this.redisTemplate.opsForValue().get(redisKeyString);
        System.out.println("看看redis中当前房间过期时间(小时):"+resultStr);//取出来的是值，也即是时长;如果没有返回的是null，不会报错

        if(resultStr == null){
//            return "从redis中没有找到这个key,说明过期了或者之前没有保存成功";
            return "666";
        }

        // 根据key获取过期时间 (我是想在点击[显示时间]按钮弹出多久可用的对话框,实现不了实时倒计时？？？？只能通过对话框方式了)
        Long expire = redisTemplate.getExpire(redisKeyString);
        String expireTime = expire + "";
        return expireTime;
    }

    //找出所有的key；从redis中；这些key就代表已经被人住的房间。  【这个好像没使用到】
    public void queryAllRoomidFromRedis() {
        //其实就是只能指定key的失效时间；不能指定Value的
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("能找到吗=="+keys); //对于String类型，结果是一个集合[111,222,333] 不知道是类型吗

        //成员应该都是String类型(是的)
        Set<String> set = keys;
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println("遍历==="+str);
            Boolean res = str instanceof String;
            System.out.print(" "+"看看布尔值"+res);
        }

    }




//    //    判断key是否存在
//    public boolean hasKey(String key){
//        return redisTemplate.hasKey(key);
//    }

    //【我数据库设置的房间时常必须1小时起步、且没有小数点，这就是弊端】
    //从String类型的时间中截取出数字来;我测试过了;不太行,有小数点的就直接去掉了,不太好,比如"2.0小时"就会变为20小时,0.2也会变成2。
    public static Long tirmNumberFromString(String str){
        str = str.trim();
        String str2 = "";
        if(str != null && !"".equals(str)){
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2 += str.charAt(i);
                }
            }
        }
        //string类型转换为long类型会丢失0吗？
        Long time = Long.parseLong(str2);
        return time;
    }

}
