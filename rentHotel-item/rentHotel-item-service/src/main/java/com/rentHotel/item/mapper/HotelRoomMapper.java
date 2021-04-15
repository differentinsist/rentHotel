package com.rentHotel.item.mapper;

import com.rentHotel.item.pojo.Hotelroom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface HotelRoomMapper extends Mapper<Hotelroom> {

    //改变mysql中的btnstatus的状态值   返回值是受影响行数，要不要返回值都行
    @Update("UPDATE hotelroom SET btnstatus = #{btnstatus} WHERE roomid = #{roomid} ")
    abstract Integer updateBtnStatus(@Param("btnstatus")Boolean btnstatus, @Param("roomid")String roomid);
    //抽象方法要public开头嘛
}
