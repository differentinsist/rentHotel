package com.rentHotel.item.lister;

import com.rentHotel.item.mapper.HotelRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**实现redis过期key就触发通知需要两步：1、创建一个类继承KeyExpirationEventMessageListener
 *                                2、注入RedisMessageListenerContainer（可以通过配置类或者XML来配置）
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

     // 继承父类的构造方法
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    @Override
    public void  onMessage(Message message, byte[] pattern){
        //1、针对redis数据失效时间，做自己的业务处理


        //2、通过message.toString()可以获取失效的key   是String类型的哦
        String expiredKey = message.toString();
        //打印失效的key看看。
        System.out.println("过期的key是代表全部吗[是全部];不用指定？:" + expiredKey);

//返回的是过期房间的id；我就暂时根据这个roomid把按钮的状态值改为1；这样在hotelMall页面刷新就可以正常重置显示[下单按钮]了;但是不合理
        //注意思路是我接受到过期key的通知，就去改变Mysql中btnstatus字段的状态值；(前提是项目要运行不停止才行)
        //因为我发现我睡觉停掉项目后;然后某个房间也是在晚上过期;发送通知了;但是项目没有运行;也就没有改变状态值;但是redis中也是没有这个key了
        this.hotelRoomMapper.updateBtnStatus(true,expiredKey);//把按钮值改为1（就是显示按钮）

    }
}
