package com.renthotel.user.mapper;

import com.renthotel.user.pojo.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface UserMapper extends Mapper<Person> {


    //修改用户的基本数据，不单限制与用户名，反正用户的id自增且不变的。用户名可变，但是唯一
//    @Update("UPDATE person SET name = \"user5\",phone = \"123457676\",address=\"北海市\"  WHERE id =20 ;")
    //实现动态修改某个字段的内容；字段也是动态的、都是String类型，    看看会原样输出吗
//    @Update("UPDATE person SET ${fieldName} = ${fieldValue} WHERE id = #{id}")

    /**解释一下为什么要这样写语句，因为按照常规的#{}或$都会有多出双引号，因为我每次值修改用户名或地址或电话号码，且刚好表中这三个字段都是
     * String类型，且每次值修改一个，这样就要动态变化字段名，值就好说，名就难搞，就算用$也会字段名也会多处双引号,所以使用<script></script>
     * 这个标签，其实复杂的sql应该使用XML文件的，可以尝试注解和XML混用，
     * @param userid
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Update("<script>"
            + "update "
            + "person "
            + "SET "
            + "${fieldName} = #{fieldValue} "
            + "WHERE id = #{id}"
            + "</script>")
    Integer alterPersonNameById(
            @Param("id") Integer userid,
            @Param("fieldName") String fieldName,
            @Param("fieldValue") String fieldValue
    );


//    //新增用户(有传递birthday参数)
//    @Insert("INSERT INTO person(name,password,idcard,birthday,phone)" +
//            " VALUES(#{name}, #{password}, #{idcard}, #{birthday}, #{phone})")
//    int insertIntoUserToPerson1(
//            @Param("name") String name,
//            @Param("password") String password,
//            @Param("idcard") String idcard,
//            @Param("birthday") Date birthday,
//            @Param("phone") String phone
//    );
//
//    //新增用户(无birthday参数)
//    @Insert("INSERT INTO person(name,password,idcard,birthday,phone)" +
//            " VALUES(#{name}, #{password}, #{idcard}, #{phone})")
//    int insertIntoUserToPerson2(
//            @Param("name") String name,
//            @Param("password") String password,
//            @Param("idcard") String idcard,
//            @Param("phone") String phone
//    );
}
