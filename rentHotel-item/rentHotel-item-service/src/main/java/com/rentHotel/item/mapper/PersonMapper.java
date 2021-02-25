package com.rentHotel.item.mapper;

import com.rentHotel.item.pojo.Person;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

//
public interface PersonMapper extends Mapper<Person> {

    @Select("select * from person WHERE pid = #{pid}")
    public Person findPersonByPid(String pid);

}
