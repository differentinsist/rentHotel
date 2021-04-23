//package com.rentHotel.test;
//
//
//import com.rentHotel.RentHotelItemApplication;
//import com.rentHotel.item.lister.RedisKeyExpirationListener;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * 测试类；测试redis中的key过期了看是否可以收到通知。
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RentHotelItemApplication.class)
//public class RedisKeyExpiredTest {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private RedisKeyExpirationListener redisKeyExpirationListener;
//
//
//    //不会写；失败了啊
//    @Test
//    public void testKeyExpired(){
//        //存数据；并设置失效时间；看是否可以收到过期通知                    10秒？
//        this.redisTemplate.opsForValue().set("1000","value==值",10, TimeUnit.SECONDS);
//
////        redisKeyExpirationListener.onMessage();
//    }
//}
