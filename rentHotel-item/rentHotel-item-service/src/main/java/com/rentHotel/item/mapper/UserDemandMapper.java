package com.rentHotel.item.mapper;

import com.rentHotel.item.pojo.Demand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDemandMapper extends Mapper<Demand> {

    //查询所有需求；按时间排序(使用#会让字符串有两个双引号的问题；导致实际的sql执行错误，那我传递String类型、传递false/true;但是怎么变为desc/asc)
    //@Select("SELECT * FROM demand WHERE `status` = #{status} ORDER BY createtime #{desc} ")
//    @Select("SELECT * FROM demand WHERE `status` = 1 ORDER BY createtime ${desc} ")
    @Select("SELECT * FROM demand WHERE `status` = 1  ORDER BY createtime ASC  LIMIT 0, 3")
    public abstract List<Demand> queryAllUserDemand(String desc);


    //保存用户提出的需求，status默认为1
//    @Insert("INSERT INTO demand(dname,demand,status)VALUES(#{dname},#{demand},#{status})") 注意这里有问题,可能会导致
    //数据库的内容多个双引号
    @Insert("INSERT INTO demand(dname,demand,status)VALUES(#{dname},#{demand},${status})")
    void saveUserDemand(@Param("dname")String dname, @Param("demand")String demand,@Param("status")Integer status );
}
