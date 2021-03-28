package com.rentHotel.item.mapper;

import com.renthotel.person.pojo.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SavePictureUrlMapper extends Mapper<Person> {


    //保存头像的URL到数据库中
    @Update("update person set personpicture = #{image} where name = #{username}")
    public abstract void savePersonHeadPortrait(@Param("image")String image, @Param("username") String username);
}
